import { CoinsServiceService } from './../../services/coins-service.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-coin',
  templateUrl: './coin.component.html',
  styleUrls: ['./coin.component.css']
})
export class CoinComponent implements OnInit {

  columns: DataTables.ColumnSettings[] = [
    { name: '#', data: 'id' },  
    { name: 'Currency', data: 'symbol' },
    { name: 'Price', data: 'price' },
    { name: '24h', data: 'hourChange' },
    { name: '', render: (a,b,row) => `<a href="/markets/${row.id}">Details<a>`, orderable: false },
  ]

  constructor(public service: CoinsServiceService) {}

  ngOnInit(): void {}

}
