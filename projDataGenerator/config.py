from os import environ

CONFIG = \
{
  "bittrex_ws": {
    "url": "https://socket-v3.bittrex.com/signalr",
    "events": {
      "tickers": "on_tickers"
    }
  },

  "bittrex_rest": {
    "url": "https://api.bittrex.com/v3/",
    "loaded": ["currencies", "markets"]
  },

  "rabbitmq": {
    "host": environ.get("FINANCE_RABBITMQ_HOST"),
    "port": 5672
  }

}
