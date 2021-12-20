import { Page } from './../components/data-table/page';
import { Ticker } from './../interfaces/ticker';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CoinServiceService {

  constructor(private http: HttpClient) { }

  getPage(parameters: Object): Observable<Page<Ticker>> {
    return this.http.get<Page<Ticker>>('http://localhost:8080/ticker', parameters)
  }
}
