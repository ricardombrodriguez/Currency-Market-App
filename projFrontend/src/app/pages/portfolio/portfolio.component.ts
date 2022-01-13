import { Component, Input, OnInit } from '@angular/core';
import { PortfolioServiceService } from './../../services/portfolio-service.service';
import { Portfolio } from './../../interfaces/portfolio';
import { Router } from '@angular/router';

import { AuthenticationService } from './../../services/authentication.service';

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})
export class PortfolioComponent implements OnInit {

  constructor(public portfolioService: PortfolioServiceService, private router: Router, public authService: AuthenticationService) { }

  public portfolio!: Portfolio;


  ngOnInit(): void {

    const url_array = this.router.url.split("/");
    const id = +url_array[url_array.length - 1];
    this.portfolioService.getPortfolio(id).subscribe((portfolio) => {
      this.portfolio = portfolio;
    });

  }

  deletePortfolio(): void {
    console.log("delete portfolio!")
    this.portfolioService.deletePortfolio(this.portfolio, parseInt(this.authService.curentUserId!)).subscribe();
    window.location.reload();
    this.router.navigateByUrl("/");
  }

  //getData = (parameters: Object) => this.portfolioService.getPortfolioDetails(parameters, this.portfolio.id)

  columns: DataTables.ColumnSettings[] = [
    { title: '#', data: 'id' },
    { title: 'Currency', render: (a, b, row) => `<img src="${row.logoUrl}"> ${row.name}`, orderable: false },
    { title: 'Quantity',  data: 'quantity' },
    { title: 'Volume',  data: 'volume' },
    { render: (a, b, row) => `<a href="/markets/${row.id}"><button type="button" class="btn btn-primary btn-sm">Details</button></a>`, orderable: false },
  ]

}
