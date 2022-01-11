import { Ticker } from './ticker';
import { Coin } from './coin';
export interface Market {
  id: number
  symbol: string
  price: number
  originCurrency: Coin
  destinyCurrency: Coin
  minuteChange: number
  hourChange: number
  tickers: Ticker[]
}
