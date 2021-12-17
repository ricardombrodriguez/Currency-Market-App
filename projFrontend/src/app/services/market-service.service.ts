import { Market } from './../interfaces/market';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MarketServiceService {

  constructor(private http: HttpClient) { }

  getPage() : Market[] {
    return []
  }

  getPageByCoin(id: number) : Market[] {
    return []
  }

}
