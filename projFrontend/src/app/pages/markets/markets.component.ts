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
    { name: '#', data: 'id' },
    { name: 'Market', render: (a, b, row) => `<a href="/coins/${row.origin_currency.id}">${row.origin_currency.name}</a>-<a href="/coins/${row.destiny_currency.id}">${row.destiny_currency.name}</a>` },
    { name: 'Price', render: (a, b, row) => `` },
    { name: '', render: (a, b, row) => `<a href="/markets/${row.id}">Details</a>`, orderable: false },
  ]
  
  constructor(public service: MarketServiceService) { }

  ngOnInit(): void {
  }

}
