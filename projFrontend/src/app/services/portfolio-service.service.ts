import { WebsocketService } from './websocket.service';
import { TransactionDetails } from './../interfaces/transaction-details';
import { Portfolio } from './../interfaces/portfolio';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Extension } from '../interfaces/extension';
import { Page } from '../components/data-table/page';
import { Wallet } from '../interfaces/wallet';

@Injectable({
  providedIn: 'root',
})
export class PortfolioServiceService {

  private portfolio!: Portfolio;

  constructor(private http: HttpClient, private websocketService: WebsocketService) { }

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

  deletePortfolio(portfolio: Portfolio, user_id: number) {
    console.log("portfolio id: " + portfolio.id)
    console.log(portfolio)
    console.log("deleted")
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

  getAllExtensions(): Observable<Extension[]> {
    return this.http.get<Extension[]>(environment.API_URL + '/extension/');
  }

  getAllExtensionsList(): Observable<Extension[]> {
    return this.http.get<Extension[]>(environment.API_URL + '/extensions/');
  }

  getPortfolioExtensions(portfolio: Portfolio) {
    console.log("portfolio id::::::: " + portfolio.id)
    return this.http.get<Extension[]>(environment.API_URL + '/extension/portfolio/' + portfolio.id);
  }

  getPortfolioDetails(parameters: Object, portfolio_id: number): Observable<Page<Wallet>> {
    return this.http.get<Page<Wallet>>(environment.API_URL + '/portfolio/' + portfolio_id + '/details', <Object>{ params: parameters });
  }

  getPortfolioTransactions(parameters: Object, portfolio_id: number): Observable<Page<TransactionDetails>> {
    return this.http.get<Page<TransactionDetails>>(environment.API_URL + '/portfolio/' + portfolio_id + '/transactions', <Object>{ params: parameters });
  }

  startUpdates(portfolio: number, callback: (data: any) => void): void {
    this.websocketService.startUpdates('/portfolio/' + portfolio, callback)
  }

}