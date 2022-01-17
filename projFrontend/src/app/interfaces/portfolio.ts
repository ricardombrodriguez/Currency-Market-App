import { Extension } from './extension';
import { Order } from './order';
export interface Portfolio {
  id: number,
  name: string,
  extensions: Extension[],
  orders: Order[],
  public_key: string
}