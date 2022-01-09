import { CoinsServiceService } from './../../services/coins-service.service';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Coin } from 'src/app/interfaces/coin';
import { map, Observable } from 'rxjs';
import { param } from 'jquery';
import { ThrowStmt } from '@angular/compiler';

@Component({
  selector: 'app-coin',
  templateUrl: './coin.component.html',
  styleUrls: ['./coin.component.css']
})
export class CoinComponent implements OnInit {

  public id: string = "";
  public observable: Observable<Coin> | null = null;
  @Input() public name: string = "";
  @Input() public symbol: string = "";
  @Input() public logo: string = "";


  columns: DataTables.ColumnSettings[] = [
    { name: '#', data: 'id' },
    { name: 'Market', render: (a, b, row) => `<a href="/coins/${row.origin_currency.id}">${row.origin_currency.name}</a>-<a href="/coins/${row.destiny_currency.id}">${row.destiny_currency.name}</a>` },
    { name: 'Price', render: (a, b, row) => `${row.price}$` },
    { name: '% 1m', render: (a, b, row) => `${row.minuteChange}%` },
    { name: '% 1h', render: (a, b, row) => `${row.hourChange}%` }, 
    { name: '', render: (a, b, row) => `<a href="/markets/${row.id}">Details</a>`, orderable: false },
  ]

  constructor(public service: CoinsServiceService, private router: Router) {


  };

  ngOnInit(): void {
    this.id = this.router.url[this.router.url.length - 1];
    this.observable = this.service.getCurrency(+this.id);
    this.observable.subscribe( (value) => {
      this.name = value.name;
      this.symbol = value.symbol;
      this.logo = value.logoUrl;
      console.log("cenas ", this.name, this.symbol, this.logo);
  
    });
  };

}
