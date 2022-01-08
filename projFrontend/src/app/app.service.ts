import { Portfolio } from './interfaces/portfolio';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(private http: HttpClient) { }

  addPortfolio(name: string): Observable<Portfolio> {
    return this.http.post<Portfolio>(environment.API_URL + '/portfolio', name)
  }

}
