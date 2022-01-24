export interface TransactionDetails {
  created_at: string, 
  qt: number, 
  val: number, 
  is_seller: boolean | null, 
  sell_curr_id: number, 
  sell_curr_logo: string, 
  sell_curr_name: string, 
  buy_curr_id: number, 
  buy_curr_logo: string, 
  buy_curr_name: string
}