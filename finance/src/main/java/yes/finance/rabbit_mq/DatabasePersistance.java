package yes.finance.rabbit_mq;

import org.json.JSONObject;

import yes.finance.SpringContext;
import yes.finance.model.Currency;
import yes.finance.model.Market;
import yes.finance.model.Ticker;
import yes.finance.repository.CurrencyRepository;
import yes.finance.repository.MarketRepository;
import yes.finance.repository.TickerRepository;

public class DatabasePersistance implements Notificable {

    public DatabasePersistance() {
        System.out.println("BSUGFUYFVSUIVFIUVFIUQVFBUVUEIFGBUWGBFGEBUIEGBFUEGFEWGFBUOGB");
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
                break;

            case Markets:
                Market market = new Market();
                SpringContext.getBean(MarketRepository.class).save(market);
                break;

            default:
                break;

        }

    }
    
}
