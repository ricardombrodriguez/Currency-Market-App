import { CoinsServiceService } from './../../services/coins-service.service';
import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Coin } from 'src/app/interfaces/coin';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-coin',
  templateUrl: './coin.component.html',
  styleUrls: ['./coin.component.css']
})
export class CoinComponent implements OnInit {

  public id: number = 0;
  public observable: Observable<Coin> | null = null;
  @Input() public name: string = "";
  @Input() public symbol: string = "";
  @Input() public logo: string = "";


  columns: DataTables.ColumnSettings[] = [
    { title: '#', data: 'id' },
    { title: 'Market', data: 'originCurrency', render: (a, b, row) => `<a href="/coins/${row.originCurrency.id}">${row.originCurrency.name}</a>-<a href="/coins/${row.destinyCurrency.id}">${row.destinyCurrency.name}</a>` },
    { title: 'Price', data: 'price', render: d => `${d}$` },
    { title: '% 1m', data: 'minuteChange', render: d => `${d}%` },
    { title: '% 1h', data: 'hourChange', render: d => `${d}%` }, 
    { title: '', render: (a, b, row) => `<a href="/markets/${row.id}"><button type="button" class="btn btn-primary btn-sm">Details</button></a>`, orderable: false },
  ]

  constructor(public service: CoinsServiceService, private router: Router) {  };

  getData = (parameters: object) => this.service.getPage(parameters)

  ngOnInit(): void {

    const url_array = this.router.url.split("/");
    this.id = +url_array[url_array.length - 1];
    this.observable = this.service.getCurrency(this.id);
    this.observable.subscribe( (value) => {
      this.name = value.name;
      this.symbol = value.symbol;
      this.logo = value.logoUrl;
    });
  };

}
