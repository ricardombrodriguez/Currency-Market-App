package yes.finance.rabbit_mq;

import org.apache.tomcat.util.digester.SystemPropertySource;
import org.hibernate.mapping.Map;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import yes.finance.model.Currency;
import yes.finance.model.Market;
import yes.finance.model.Order;
import yes.finance.model.Portfolio;
import yes.finance.model.Ticker;
import yes.finance.model.Extension;
import yes.finance.repository.CurrencyRepository;
import yes.finance.repository.MarketRepository;
import yes.finance.repository.OrderRepository;
import yes.finance.repository.TickerRepository;
import yes.finance.services.OrderService;
import yes.finance.repository.ExtensionRepository;

@Component
public class DatabasePersistance implements ApplicationListener<MessageEvent> {

    @Autowired
    private MarketRepository marketRepository;

    @Autowired
    private TickerRepository tickerRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private ExtensionRepository extensionRepository;

    @Autowired
    private OrderService orderService;

    private ArrayList<String> currencies = new ArrayList<String>();

    private HashMap<String, ArrayList<String>> markets = new HashMap<>();

    public class CustomComparator implements Comparator<Ticker> {
        @Override
        public int compare(Ticker t1, Ticker t2) {
            return t1.getCreatedAt().compareTo(t2.getCreatedAt());
        }
    }

    public static Float roundFloat(Float number) {
        DecimalFormat df = new DecimalFormat("0.00");
        return Float.parseFloat(df.format(number).replace(",", "."));
    }

    @Override
    public void onApplicationEvent(MessageEvent event) {
        String input = event.getMessage();
        MQChannels channel = event.getChannel();
        JSONObject data = new JSONObject(input);

        switch (channel) {

            case Tickers:

                String symbol = data.getString("symbol");
                Market market = marketRepository.findBySymbol(symbol);

                if (market == null)
                    return;

                Float lastTradeRate = Float.parseFloat(data.getString("lastTradeRate"));
                Float bidRate = Float.parseFloat(data.getString("bidRate"));
                Float askRate = Float.parseFloat(data.getString("askRate"));
                Ticker ticker = new Ticker(market, lastTradeRate, bidRate, askRate);
                tickerRepository.save(ticker);

                Gson gson = new Gson();
                String json = gson.toJson(ticker);

                for (Extension e : extensionRepository.findAll()) {

                    List<Portfolio> extensionPortfolios = extensionRepository.findExtensionPortfolios(e);

                    URL url;

                    try {
                        url = new URL(e.getPath());
                        HttpURLConnection http = (HttpURLConnection) url.openConnection();
                        http.setRequestMethod("POST");
                        http.setDoOutput(true);
                        http.setRequestProperty("Accept", "application/json");
                        http.setRequestProperty("Content-Type", "application/json");

                        byte[] out = json.getBytes(StandardCharsets.UTF_8);

                        OutputStream stream = http.getOutputStream();
                        stream.write(out);

                        BufferedReader buffer;
                        HashMap<String, Object> dict = new HashMap<>();
                        if (http.getResponseCode() == 200) {
                            buffer = new BufferedReader(new InputStreamReader(http.getInputStream()));
                            String response;
                            while ((response = buffer.readLine()) != null) {
                                dict = new Gson().fromJson(response.toString(), HashMap.class);
                            }
                        }

                        Double quantity = dict.get("operation").equals("BUY") ? (Double) dict.get("quantity")
                                : (Double) dict.get("quantity") * -1;
                        Double value = (Double) dict.get("quantity") * lastTradeRate;

                        for (Portfolio p : extensionPortfolios) {

                            Order order = new Order(quantity.floatValue(), value.floatValue(), p, market);
                            orderService.saveOrder(order);

                            System.out.println(order);
                        }

                        http.disconnect();

                    } catch (MalformedURLException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                }

                // change %:

                List<Ticker> tickers = tickerRepository.findBymarket(market.getId());

                if (!tickers.isEmpty()) {

                    Collections.sort(tickers, new CustomComparator());
                    market.setPrice(ticker.getPrev_value());

                    java.sql.Timestamp last_minute = ticker.getCreatedAt();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(last_minute);
                    cal.add(Calendar.MINUTE, -1);
                    last_minute.setTime(cal.getTime().getTime());

                    java.sql.Timestamp last_hour = ticker.getCreatedAt();
                    Calendar cal2 = Calendar.getInstance();
                    cal2.setTime(last_minute);
                    cal2.add(Calendar.HOUR, -1);
                    last_hour.setTime(cal2.getTime().getTime());

                    Float value_last_minute = 0f;
                    Float value_last_hour = 0f;

                    for (Ticker t : tickers) {

                        if (t.getCreatedAt().after(last_minute)) {
                            value_last_minute = t.getPrev_value();
                            break;
                        }
                    }

                    for (Ticker t : tickers) {

                        if (t.getCreatedAt().after(last_minute)) {
                            value_last_hour = t.getPrev_value();
                            break;
                        }
                    }

                    Float value_now = ticker.getPrev_value();
                    Float last_minute_change = roundFloat(((value_last_minute - value_now) / value_last_minute) * 100);
                    Float last_hour_change = roundFloat(((value_last_hour - value_now) / value_last_hour) * 100);

                    market.setMinuteChange(last_minute_change);
                    market.setHourChange(last_hour_change);
                    marketRepository.save(market);

                }

                break;

            case Currencies:
                String name = data.getString("name");
                String csymbol = data.getString("symbol");
                String logoUrl = data.has("logoUrl") ? data.getString("logoUrl") : null;
                boolean online = data.getString("status").equals("ONLINE");
                Currency currency = new Currency(name, csymbol, logoUrl, online);
                currencyRepository.save(currency);

                currencies.add(csymbol);
                if (markets.containsKey(csymbol))
                    for (String inp : markets.get(csymbol))
                        this.onApplicationEvent(new MessageEvent(this, MQChannels.Markets, inp));

                break;

            case Markets:

                String quoteCurrencySymbol = data.getString("quoteCurrencySymbol");
                String baseCurrencySymbol = data.getString("baseCurrencySymbol");
                Instant createdAt = Instant.parse(data.getString("createdAt"));
                // Timestamp createdAt =
                // Timestamp.from(Instant.parse(data.getString("createdAt")));

                if (!currencies.contains(baseCurrencySymbol)) {
                    if (!markets.containsKey(baseCurrencySymbol))
                        markets.put(baseCurrencySymbol, new ArrayList<>());
                    markets.get(baseCurrencySymbol).add(input);
                    return;
                }

                if (!currencies.contains(quoteCurrencySymbol)) {
                    if (!markets.containsKey(quoteCurrencySymbol))
                        markets.put(quoteCurrencySymbol, new ArrayList<>());
                    markets.get(quoteCurrencySymbol).add(input);
                    return;
                }

                String msymbol = data.getString("symbol");
                Currency baseCurrency = currencyRepository.findBySymbol(baseCurrencySymbol);
                Currency quoteCurrency = currencyRepository.findBySymbol(quoteCurrencySymbol);

                Market m = new Market(msymbol, baseCurrency, quoteCurrency, createdAt);
                marketRepository.save(m);
                break;

            default:
                break;

        }

    }

}
