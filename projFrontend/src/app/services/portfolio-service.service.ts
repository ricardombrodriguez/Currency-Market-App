import { Portfolio } from './../interfaces/portfolio';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

import { PortfolioComponent } from './../pages/portfolio/portfolio.component';

@Injectable({
  providedIn: 'root',
})
export class PortfolioServiceService {
  private portfolio!: Portfolio;
  private portfolioComponent!: PortfolioComponent;

  constructor(private http: HttpClient) { }

  getPortfolios(id: number): Observable<Portfolio[]> {
    return this.http.get<Portfolio[]>(environment.API_URL + '/portfolio', { params: { id } });
  }

  getPortfolio(id: number): Observable<Portfolio> {
    return this.http.get<Portfolio>(environment.API_URL + '/portfolio/' + id);
  }

  getPage(parameters: Object): Observable<Portfolio> {
    var pathArray = window.location.pathname.split('/');
    return this.http.get<Portfolio>(
      environment.API_URL + '/portfolio/' + pathArray[pathArray.length - 1],
      parameters
    );
  }

  addPortfolio(name: string, userId: number): Observable<any> {
    let params = new HttpParams();
    params = params.append('name', name);
    params = params.append('id', userId);
    return this.http.post(environment.API_URL + '/portfolio', {}, { params });
  }

  deletePortfolio(portfolio: Portfolio) {
    console.log("delete portfolio "+ portfolio.id +" from service ")
    return this.http.delete(environment.API_URL + '/portfolio/'+ portfolio.id);
  }
  
}
