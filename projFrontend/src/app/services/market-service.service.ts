import { WebsocketService } from './websocket.service';
import { Observable } from 'rxjs';
import { Page } from './../components/data-table/page';
import { Market } from './../interfaces/market';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { environment } from '../../environments/environment';
import { Ticker } from '../interfaces/ticker';

@Injectable({
  providedIn: 'root'
})
export class MarketServiceService {

  constructor(private http: HttpClient, private websocketService: WebsocketService) { }

  getPage(parameters: Object): Observable<Page<Market>> {
    return this.http.get<Page<Market>>(environment.API_URL + '/market', parameters)
  }

  getPageByCoin(id: number) : Market[] {
    return []
  }
  
  startTickerUpdates(market: string, callback: (data: any) => void): void {
    this.websocketService.startUpdates('/market/' + market, callback)
  }

  getMarket(id: number): Observable<Market> {
    return this.http.get<Market>(environment.API_URL + '/market/' + id)
  }


}
