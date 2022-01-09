import { Ticker } from './ticker';
import { Coin } from './coin';
export interface Market {
  id: number
  price: number
  dailyGrowth: number

  originCurrency: Coin
  destinyCurrency: Coin

  tickers: Ticker[]
}
