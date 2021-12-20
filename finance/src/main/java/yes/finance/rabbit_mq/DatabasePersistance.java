package yes.finance.rabbit_mq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import yes.finance.SpringContext;
import yes.finance.model.Currency;
import yes.finance.model.Market;
import yes.finance.model.Ticker;
import yes.finance.repository.CurrencyRepository;
import yes.finance.repository.MarketRepository;
import yes.finance.repository.TickerRepository;

public class DatabasePersistance implements Notificable {

    private List<String> currencies = new ArrayList<>();
    private Map<String, List<String>> markets = new HashMap<>();

    public DatabasePersistance() {
        MessageQueue.getInstance().subscribe(this);
    }

    @Override
    public void notification(String input, MQChannels channel) {
        
        JSONObject data = new JSONObject(input);

        switch (channel) {
            case Tickers:
                Ticker ticker = new Ticker();
                SpringContext.getBean(TickerRepository.class).save(ticker);
                break;

            case Currencies:
                String name = data.getString("name");
                String symbol = data.getString("symbol");
                String logoUrl = data.has("logoUrl") ? data.getString("logoUrl") : null;
                boolean online = data.getString("status").equals("ONLINE");
                Currency currency = new Currency(name, symbol, logoUrl, online);
                SpringContext.getBean(CurrencyRepository.class).save(currency);

                currencies.add(symbol);
                if (markets.containsKey(symbol))
                    for (String mkt : markets.get(symbol))
                        this.notification(mkt, MQChannels.Markets);
                break;

            case Markets:
                String originCurrencySymbol = data.getString("baseCurrencySymbol");
                String destinyCurrencySymbol = data.getString("quoteCurrencySymbol");

                for (String curr : new String[] { originCurrencySymbol, destinyCurrencySymbol }) {
                    if (!currencies.contains(curr)) {
                        if (!markets.containsKey(curr))
                            markets.put(curr, new ArrayList<>());
                        markets.get(curr).add(input);
                        return;
                    }
                }

                Currency originCurrency = SpringContext.getBean(CurrencyRepository.class).findBySymbol(originCurrencySymbol);
                Currency destinyCurrency = SpringContext.getBean(CurrencyRepository.class).findBySymbol(destinyCurrencySymbol);

                Market market = new Market(data.getString("symbol"), originCurrency, destinyCurrency);
                SpringContext.getBean(MarketRepository.class).save(market);
                break;

            default:
                break;

        }

    }
    
}
