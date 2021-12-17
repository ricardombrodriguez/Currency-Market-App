import { Coin } from './../interfaces/coin';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CoinServiceService {

  constructor(private http: HttpClient) { }

  getAll(): Coin[] {
    return []
  }
}
