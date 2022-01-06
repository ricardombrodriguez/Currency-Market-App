import { Observable } from 'rxjs';
import { Page } from './../components/data-table/page';
import { Market } from './../interfaces/market';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MarketServiceService {

  constructor(private http: HttpClient) { }

  getPage(parameters: Object): Observable<Page<Market>> {
    return this.http.get<Page<Market>>(environment.API_URL + '/market', parameters)
  }

  getPageByCoin(id: number) : Market[] {
    return []
  }

}
