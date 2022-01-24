import { Page } from './../components/data-table/page';
import { Market } from './../interfaces/market';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { environment } from '../../environments/environment';
import { Coin } from '../interfaces/coin';

@Injectable({
  providedIn: 'root'
})
export class CoinsServiceService {

  constructor(private http: HttpClient) { }

  getPage(parameters: Object): Observable<Page<Market>> {
    var pathArray = window.location.pathname.split('/');
    return this.http.get<Page<Market>>(environment.API_URL + '/currency/'+ pathArray[pathArray.length - 1], <Object>{ params: parameters });
  }

  getCurrency(id: number): Observable<Coin> {
    return this.http.get<Coin>(environment.API_URL + '/currency/info/'+ id);
  }

}
