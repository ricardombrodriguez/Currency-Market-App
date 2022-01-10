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

  addPortfolio(name: string): Observable<any>{

    // console.log('post rest service called with name ' + name);
    // return this.http.post<any>(environment.API_URL + '/portfolio', name);


    console.log(">> "+ name)
    
    return this.http.post(environment.API_URL + '/portfolio', name) 
  }


}
