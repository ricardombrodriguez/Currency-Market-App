package yes.finance.rabbit_mq;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import yes.finance.model.Currency;
import yes.finance.model.Market;
import yes.finance.model.Order;
import yes.finance.model.Ticker;
import yes.finance.model.Transaction;
import yes.finance.repository.CurrencyRepository;
import yes.finance.repository.MarketRepository;
import yes.finance.repository.OrderRepository;
import yes.finance.repository.TickerRepository;
import yes.finance.repository.TransactionRepository;

@Component
public class DatabasePersistance implements ApplicationListener<MessageEvent> {

    @Autowired
    private MarketRepository marketRepository;

    @Autowired
    private TickerRepository tickerRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TransactionRepository transactionRepository;

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

                if (market == null) return;

                Float lastTradeRate = Float.parseFloat(data.getString("lastTradeRate"));
                Float bidRate = Float.parseFloat(data.getString("bidRate"));
                Float askRate = Float.parseFloat(data.getString("askRate"));
                Ticker ticker = new Ticker(market, lastTradeRate, bidRate, askRate);
                tickerRepository.save(ticker);

                List<Order> buyOrders = orderRepository.findSellOrderComplements(0, market.getId(), ticker.getMin_seller_value());
                List<Order> sellOrders = orderRepository.findBuyOrderComplements(0, market.getId(), ticker.getMax_buyer_value());

                for (Order order: buyOrders) {
                    Order complementOrder = new Order(-order.getQuantity(), ticker.getMin_seller_value(), null, order.getMarket());
                    Transaction t = new Transaction(complementOrder, order);

                    orderRepository.save(complementOrder);
                    transactionRepository.save(t);
                }

                for (Order order: sellOrders) {
                    Order complementOrder = new Order(-order.getQuantity(), ticker.getMax_buyer_value(), null, order.getMarket());
                    Transaction t = new Transaction(order, complementOrder);

                    orderRepository.save(complementOrder);
                    transactionRepository.save(t);
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
