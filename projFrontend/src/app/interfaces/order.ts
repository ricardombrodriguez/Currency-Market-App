import { Market } from './market';
import { Portfolio } from 'src/app/interfaces/portfolio';
export interface Order {
  id: number,
  quantity: number,
  order_value: number,
  createdAt: string,
  portfolio: Portfolio
  market: Market
}