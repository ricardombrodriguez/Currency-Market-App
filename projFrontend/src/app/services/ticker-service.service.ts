import { Page } from './../components/data-table/page';
import { Ticker } from './../interfaces/ticker';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TickerServiceService {
  
  constructor(private http: HttpClient) { }

  getPage(parameters: Object): Observable<Page<Ticker>> {
    return this.http.get<Page<Ticker>>(environment.API_URL + '/ticker', parameters)
  }
}
