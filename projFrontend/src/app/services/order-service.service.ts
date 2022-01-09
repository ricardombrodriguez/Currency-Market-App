import { environment } from './../../environments/environment';
import { Observable } from 'rxjs';
import { Order } from './../interfaces/order';
import { Page } from './../components/data-table/page';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class OrderServiceService {

  constructor(private http: HttpClient) { }

  getSellOrdersPage(marketId: number, parameters: object): Observable<Page<Order>> {
    return this.http.get<Page<Order>>(environment.API_URL + '/market/' + marketId + '/orders/sell', parameters)
  }

  getBuyOrdersPage(marketId: number, parameters: object): Observable<Page<Order>> {
    return this.http.get<Page<Order>>(environment.API_URL + '/market/' + marketId + '/orders/buy', parameters)
  }

  createOrder(marketId: number, portfolioId: number, quantity: number): void {
    this.http.post(environment.API_URL + '/orders/', {params: {
      market: { id: marketId },
      portfolio: { id: portfolioId },
      quantity
    }})
  }

}
