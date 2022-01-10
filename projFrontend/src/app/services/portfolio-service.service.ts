import { Portfolio } from './../interfaces/portfolio';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PortfolioServiceService {

  constructor(private http: HttpClient) { }

  getPortfolios(): Observable<Portfolio[]> {
    return this.http.get<Portfolio[]>(environment.API_URL + '/portfolio')
  }

  getPage(parameters: Object): Observable<Portfolio> {
    var pathArray = window.location.pathname.split('/');
    console.log("cá estamos")
    return this.http.get<Portfolio>(environment.API_URL + '/portfolio/'+ pathArray[pathArray.length - 1], parameters);
  }

}
