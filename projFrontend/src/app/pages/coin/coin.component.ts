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

  public id: number = 0;
  public observable: Observable<Coin> | null = null;
  @Input() public name: string = "";
  @Input() public symbol: string = "";
  @Input() public logo: string = "";
  @Input() public online!: boolean;


  columns: DataTables.ColumnSettings[] = [
    { data: 'id' },
    { title: 'Market', render: (a, b, row) => `<a href="/coins/${row.originCurrency.id}"><button type="button" class="btn btn-primary btn-sm">${row.originCurrency.name}</button></a><i class="fas fa-arrows-alt-h" style="padding-right:5px; padding-left:5px;"></i><a href="/coins/${row.destinyCurrency.id}"><button type="button" class="btn btn-primary btn-sm">${row.destinyCurrency.name}</button></a>` },
    { title: 'Price', render: (a, b, row) => `${row.price}$` },
    { title: 'Minute Change (%)', render: (a, b, row) => `${row.minuteChange}%` },
    { title: 'Hour Change (%)', render: (a, b, row) => `${row.hourChange}%` }, 
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
      this.online = <boolean>value.online;
    });
  };

}
