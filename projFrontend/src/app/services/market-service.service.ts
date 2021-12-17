import { Market } from './../interfaces/market';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MarketServiceService {

  constructor(private http: HttpClient) { }

  getAll() : Market[] {
    return []
  }

  getByCoin(id: number) : Market[] {
    return []
  }

}
