import { Page } from './../components/data-table/page';
import { Coin } from './../interfaces/coin';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SearchService {
  
  constructor(private http: HttpClient) { }

  getData(name: string): Observable<Coin[]> {
    return this.http.get<Coin[]>(environment.API_URL + '/currency/search', { params: { name } })
  }
  
}
