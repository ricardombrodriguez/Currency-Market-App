import { Timestamp } from 'rxjs';

export interface Ticker {

  id: number
  prev_value: number
  max_buyer_value: number
  min_seller_value: number
  createdAt: string

}
