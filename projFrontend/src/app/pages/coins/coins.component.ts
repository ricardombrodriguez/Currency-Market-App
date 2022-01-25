import { Coin } from './../../interfaces/coin';
import { Page } from './../../components/data-table/page';
import { Observable } from 'rxjs';
import { CoinServiceService } from './../../services/coin-service.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-coins',
  templateUrl: './coins.component.html',
  styleUrls: ['./coins.component.css']
}) 
export class CoinsComponent implements OnInit {

  columns: DataTables.ColumnSettings[] = [
    { data: 'id' }, 
    { render: (a, b, row) => <boolean>row.online ? `<div style="background-color: green; height:8px;width: 8px; border-radius:50%; display:flex;"></div>` :  `<div style="background-color: red; height:8px;width: 8px; border-radius:50%; display:flex;"></div>`, orderable: false },
    { title: 'Symbol', data: 'symbol' },
    { title: 'Name', data: 'name' },
    { render: (a, b, row) => `<a href="/coins/${row.id}"><button type="button" class="btn btn-primary btn-sm">Details</button><a>`, orderable: false },
  ]
  static nome: any;

  getData = (parameters: object) => {
    return this.coinService.getPage(parameters)
  }

  constructor(public coinService: CoinServiceService) { }

  ngOnInit(): void {}

}
