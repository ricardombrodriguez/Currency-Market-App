import { CoinServiceService } from './../../services/coin-service.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-coins',
  templateUrl: './coins.component.html',
  styleUrls: ['./coins.component.css']
})
export class CoinsComponent implements OnInit {

  columns: DataTables.ColumnSettings[] = [
    { name: '#', data: 'id' },
    { name: 'Symbol', data: 'symbol' },
    { name: 'Name', data: 'name' },
    { name: '', render: (row) => `<a href="/coins/${row.id}">Details<a>`, orderable: false },
  ]

  constructor(public service: CoinServiceService) { }

  ngOnInit(): void {
  }

}
