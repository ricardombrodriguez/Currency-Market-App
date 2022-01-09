import { Page } from './../components/data-table/page';
import { Coin } from './../interfaces/coin';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CoinServiceService {

  constructor(private http: HttpClient) { }

  getPage(parameters: Object): Observable<Page<Coin>> {
    return this.http.get<Page<Coin>>(environment.API_URL + '/currency', <Object>{ params: parameters })
  }
}
