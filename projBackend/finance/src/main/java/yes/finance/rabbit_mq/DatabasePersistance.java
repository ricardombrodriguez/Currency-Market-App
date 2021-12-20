package yes.finance.rabbit_mq;

import org.json.JSONObject;


import java.util.HashMap;
import java.util.ArrayList;

import yes.finance.SpringContext;
import yes.finance.model.Currency;
import yes.finance.model.Market;
import yes.finance.model.Ticker;
import yes.finance.repository.CurrencyRepository;
import yes.finance.repository.MarketRepository;
import yes.finance.repository.TickerRepository;

public class DatabasePersistance implements Notificable {

    private ArrayList<String> currencies = new ArrayList<String>();
    private HashMap<String, ArrayList<String>> markets = new HashMap<>();

    public DatabasePersistance() {
        MessageQueue.getInstance().subscribe(this);
    }

    @Override
    public void notification(String input, MQChannels channel) {
        
        JSONObject data = new JSONObject(input);

        switch (channel) {

            case Tickers:

                String symbol = data.getString("symbol");
                Market market = SpringContext.getBean(MarketRepository.class).findBySymbol(symbol);

                if (market == null) return;

                Float lastTradeRate =  Float.parseFloat( data.getString("lastTradeRate") );
                Float bidRate = Float.parseFloat( data.getString("bidRate") );
                Float askRate =  Float.parseFloat( data.getString("askRate") );
                Ticker ticker = new Ticker(lastTradeRate, bidRate, askRate);
                SpringContext.getBean(TickerRepository.class).save(ticker);
                break;

            case Currencies:
                System.out.println("CONAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                String name = data.getString("name");
                String csymbol = data.getString("symbol");
                String logoUrl = data.has("logoUrl") ? data.getString("logoUrl") : null;
                boolean online = data.getString("status").equals("ONLINE");
                Currency currency = new Currency(name, csymbol, logoUrl, online);                
                SpringContext.getBean(CurrencyRepository.class).save(currency);

                currencies.add(csymbol);
                if (markets.containsKey(csymbol)) {
                    for (String inp : markets.get(csymbol)) {
                        this.notification(inp, MQChannels.Markets);
                    }
                }

                break;

            case Markets:    

                String quoteCurrencySymbol = data.getString("quoteCurrencySymbol");
                String baseCurrencySymbol = data.getString("baseCurrencySymbol");

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
                Currency baseCurrency = SpringContext.getBean(CurrencyRepository.class).findBySymbol(baseCurrencySymbol);
                Currency quoteCurrency = SpringContext.getBean(CurrencyRepository.class).findBySymbol(quoteCurrencySymbol);

                Market m = new Market(msymbol,baseCurrency,quoteCurrency);
                SpringContext.getBean(MarketRepository.class).save(m);
                break;

            default:
                break;

        }

    }
    
}
