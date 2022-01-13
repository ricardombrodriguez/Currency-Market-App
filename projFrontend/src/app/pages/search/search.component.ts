import { Coin } from './../../interfaces/coin';
import { Page } from './../../components/data-table/page';
import { Observable } from 'rxjs';
import { CoinServiceService } from './../../services/coin-service.service';
import { Component, OnInit } from '@angular/core';

import { Market } from './../../interfaces/market';
import { ColumnInterface } from './../../components/data-table/column-interface';
import { MarketServiceService } from './../../services/market-service.service';

@Component({
  selector: 'app-coins',
  templateUrl: './coins.component.html',
  styleUrls: ['./coins.component.css']
})
export class CoinsComponent implements OnInit {

  columns1: DataTables.ColumnSettings[] = [
    { title: '#', data: 'id' }, 
    { title: 'Symbol', data: 'symbol' },
    { title: 'Name', data: 'name' },
    { render: (a, b, row) => `<a href="/coins/${row.id}">Details<a>`, orderable: false },
  ]

  getData1 = (parameters: object) => {
    return this.coinService.getPage(parameters)
  }
  constructor(public coinService: CoinServiceService) { }


  ngOnInit(): void {}

}


export class MarketsComponent implements OnInit {

  columns2: DataTables.ColumnSettings[] = [
    { title: '#', data: 'id' },
    { title: 'Market', render: (a, b, row) => `<a href="/coins/${row.originCurrency.id}">${row.originCurrency.name}</a>-<a href="/coins/${row.destinyCurrency.id}">${row.destinyCurrency.name}</a>` },
    { title: 'Price', render: (a, b, row) => `${row.price} ${row.destinyCurrency.symbol}` },
    { title: '% 1m', render: (a, b, row) => `${row.minuteChange}%` },
    { title: '% 1h', render: (a, b, row) => `${row.hourChange}%` }, 
    { render: (a, b, row) => `<a href="/markets/${row.id}">Details</a>`, orderable: false },
  ]

  getData2 = (parameters: object) => this.marketService.getPage(parameters)
  
  constructor(public marketService: MarketServiceService) { }

  ngOnInit(): void {
  }
}
