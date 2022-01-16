import { Component, Input, OnInit } from '@angular/core';
import { PortfolioServiceService } from './../../services/portfolio-service.service';
import { Portfolio } from './../../interfaces/portfolio';
import { Router } from '@angular/router';

import { AuthenticationService } from './../../services/authentication.service';
import { User } from 'src/app/interfaces/user';
import { Extension } from 'src/app/interfaces/extension';

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})
export class PortfolioComponent implements OnInit {

  constructor(public portfolioService: PortfolioServiceService, private router: Router, public authService: AuthenticationService) { }

  public portfolio!: Portfolio;
  public users!: User[];
  public extensions!: Extension[];


  ngOnInit(): void {

    const url_array = this.router.url.split("/");
    const id = +url_array[url_array.length - 1];
    this.portfolioService.getPortfolio(id).subscribe((portfolio) => {
      this.portfolio = portfolio;
    });

    // this.portfolioService.getPortfolioUsers(this.portfolio.public_key).subscribe((users) => {
    //   console.log("port service....")
    //   this.users = users;
    // })

  }

  getPortfolioUsers(): void {

    this.portfolioService.getPortfolioUsers(this.portfolio.public_key).subscribe((users) => {
      console.log("port service....")
      this.users = users;
    })

  }


  deletePortfolio(): void {
    this.portfolioService.deletePortfolio(this.portfolio, parseInt(this.authService.curentUserId!)).subscribe();
    window.location.reload();
    this.router.navigateByUrl("/");
  }

  // addextension e deleteextension são funções ativadas com o (click)

  addExtension(extension: Extension): void {
    this.portfolioService.addExtension(this.portfolio, extension).subscribe((extension) => {
      // ou podemos atualizar lista de extensions
      console.log("extension added....")
      console.log(extension)
      this.extensions.push(extension);
    })
  }



  deleteExtension(extension: Extension): void {
    this.portfolioService.deleteExtension(this.portfolio, extension).subscribe((extension) => {
      console.log("extension deleted....")
    })
  }

  //getData = (parameters: Object) => this.portfolioService.getPortfolioDetails(parameters, this.portfolio.id)

  columns: DataTables.ColumnSettings[] = [
    { title: '#', data: 'id' },
    { title: 'Currency', render: (a, b, row) => `<img src="${row.logoUrl}"> ${row.name}`, orderable: false },
    { title: 'Quantity', data: 'quantity' },
    { title: 'Volume', data: 'volume' },
    { render: (a, b, row) => `<a href="/markets/${row.id}"><button type="button" class="btn btn-primary btn-sm">Details</button></a>`, orderable: false },
  ]

}
