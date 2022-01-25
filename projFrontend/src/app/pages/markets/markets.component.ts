import { MarketServiceService } from './../../services/market-service.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-markets',
  templateUrl: './markets.component.html',
  styleUrls: ['./markets.component.css']
})
export class MarketsComponent implements OnInit {

  columns: DataTables.ColumnSettings[] = [
    { data: 'id' },
    { title: 'Market', render: (a, b, row) => `<a href="/coins/${row.originCurrency.id}"><button type="button" class="btn btn-outline-primary btn-sm">${row.originCurrency.name}</button></a><i class="fas fa-arrows-alt-h" style="padding-right:5px; padding-left:5px;"></i><a href="/coins/${row.destinyCurrency.id}"><button type="button" class="btn btn-outline-primary btn-sm">${row.destinyCurrency.name}</button></a>` , orderable: false},
    { title: 'Price', render: (a, b, row) => `${row.price} ${row.destinyCurrency.symbol}` },
    { title: 'Minute Change (%)', render: (a, b, row) => `${row.minuteChange}%` , orderable: false},
    { title: 'Hour Change (%)', render: (a, b, row) => `${row.hourChange}%` , orderable: false},
    { render: (a, b, row) => <boolean>row.originCurrency.online ? `<a href="/markets/${row.id}"><button type="button" class="btn btn-primary btn-sm">Details</button></a>` : `<button type="button" class="btn btn-danger btn-sm">Offline</button>` , orderable: false },
  ]

  getData = (parameters: object) => this.marketService.getPage(parameters)

  constructor(public marketService: MarketServiceService) { }

  ngOnInit(): void { }

}
