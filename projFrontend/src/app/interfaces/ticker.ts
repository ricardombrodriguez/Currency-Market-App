import { Timestamp } from 'rxjs';
import { Market } from './market';

export interface Ticker {

  id: number
  prev_value: number
  max_buyer_value: number
  min_seller_value: number
  created_at: Timestamp<any>

  market: Market
}
