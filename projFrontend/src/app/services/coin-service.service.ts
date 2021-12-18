import { Page } from './../components/data-table/page';
import { Coin } from './../interfaces/coin';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CoinServiceService {

  constructor(private http: HttpClient) { }

  getPage(parameters: Object): Observable<Page<Coin>> {
    return this.http.get<Page<Coin>>('http://localhost:8080/currency', parameters)
  }
}
