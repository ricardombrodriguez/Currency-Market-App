import { Market } from './market';
import { Portfolio } from 'src/app/interfaces/portfolio';
export interface Order {
  quantity: number,
  order_value: number,
  createdAt: string,
  portfolio: Portfolio
  market: Market
}