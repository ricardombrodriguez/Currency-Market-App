import { TickerServiceService } from './../../services/ticker-service.service';
import { ColumnInterface } from './../../components/data-table/column-interface';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-ticker',
  templateUrl: './ticker.component.html',
  styleUrls: ['./ticker.component.css']
})
export class TickerComponent implements OnInit {

  columns: DataTables.ColumnSettings[] = [
    { name: '#', data: 'id' },
    { name: 'Last Value', render: (a, b, row) => `${row.prev_value}`},
    { name: 'BidRate', render: (a, b, row) => `${row.max_buyer_value}` },
    { name: 'AskRate', render: (a, b, row) => `${row.min_seller_value}`, orderable: false },
    { name: 'Market', render: (a, b, row) => `${row.market.origin_currency.name}-${row.market.destiny_currency.name}`, orderable: false }
  ]
  
  constructor(public service: TickerServiceService) { }

  ngOnInit(): void {
  }

}
