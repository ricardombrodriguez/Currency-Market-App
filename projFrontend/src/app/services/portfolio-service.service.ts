import { Portfolio } from './../interfaces/portfolio';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { finalize, Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { provideRoutes } from '@angular/router';
import { identifierName } from '@angular/compiler';
import { Extension } from '../interfaces/extension';

@Injectable({
  providedIn: 'root',
})
export class PortfolioServiceService {
  private portfolio!: Portfolio;

  constructor(private http: HttpClient) { }

  getPortfolios(id: number): Observable<Portfolio[]> {
    return this.http.get<Portfolio[]>(environment.API_URL + '/portfolio', { params: { id } });
  }

  getPortfolio(id: number): Observable<Portfolio> {
    return this.http.get<Portfolio>(environment.API_URL + '/portfolio/' + id);
  }

  getPage(parameters: Object): Observable<Portfolio> {
    var pathArray = window.location.pathname.split('/');
    console.log('cá estamos');
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

  deletePortfolio(portfolio: Portfolio, user_id: number) {
    console.log("portfolio id: " + portfolio.id)
    return this.http.delete(environment.API_URL + '/portfolio/' + portfolio.id, { params: { user_id } });
  }

  joinPortfolio(publicKey: string, userId: number): Observable<any> {
    let params = new HttpParams();
    params = params.append('publicKey', publicKey);
    params = params.append('userId', userId);
    return this.http.post(environment.API_URL + '/portfolio/join', {}, { params });
  }

  getPortfolioUsers(publicKey: string): Observable<any> {
    let params = new HttpParams();
    params = params.append('publicKey', publicKey);
    return this.http.post(environment.API_URL + '/portfolio/users', {}, { params });
  }

  addExtension(portfolio: Portfolio, extension: Extension): Observable<any> {
    let params = new HttpParams();
    params = params.append('id', portfolio.id);
    params = params.append('path', extension.path);
    return this.http.post(environment.API_URL + '/portfolio/extension', {}, { params });
  }

  deleteExtension(portfolio: Portfolio, extension: Extension): Observable<any> {
    return this.http.delete(environment.API_URL + '/portfolio/extension', { params: { 'id': portfolio.id, 'path': extension.path } });
  }

}