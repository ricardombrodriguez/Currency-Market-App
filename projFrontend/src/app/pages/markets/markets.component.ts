import { Observable } from 'rxjs';
import { Page } from './../../components/data-table/page';
import { Market } from './../../interfaces/market';
import { ColumnInterface } from './../../components/data-table/column-interface';
import { MarketServiceService } from './../../services/market-service.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-markets',
  templateUrl: './markets.component.html',
  styleUrls: ['./markets.component.css']
})
export class MarketsComponent implements OnInit {

  columns: DataTables.ColumnSettings[] = [
    { title: '#', data: 'id' },
    { title: 'Market', render: (a, b, row) => `<a href="/coins/${row.originCurrency.id}">${row.originCurrency.name}</a>-<a href="/coins/${row.destinyCurrency.id}">${row.destinyCurrency.name}</a>` },
    { title: 'Price', render: (a, b, row) => `${row.price}$` },
    { title: '% 1m', render: (a, b, row) => `${row.minuteChange}%` },
    { title: '% 1h', render: (a, b, row) => `${row.hourChange}%` }, 
    { render: (a, b, row) => `<a href="/markets/${row.id}">Details</a>`, orderable: false },
  ]

  getData = (parameters: object) => this.marketService.getPage(parameters)
  
  constructor(public marketService: MarketServiceService) { }

  ngOnInit(): void {
  }

}
