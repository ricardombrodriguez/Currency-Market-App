import { Component, Input, OnInit } from '@angular/core';
import { PortfolioServiceService } from './../../services/portfolio-service.service';
import { Portfolio } from './../../interfaces/portfolio';
import { ActivatedRoute, Router } from '@angular/router';

import { AuthenticationService } from './../../services/authentication.service';
import { User } from 'src/app/interfaces/user';
import { Extension } from 'src/app/interfaces/extension';
import { Order } from 'src/app/interfaces/order';
import { mergeMap } from 'rxjs/operators';

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})
export class PortfolioComponent implements OnInit {

  public portfolio!: Portfolio;
  public users!: User[];
  public extensions!: Extension[];
  public allExtensions!: Extension[];
  public name: string = ""
  public orders!: Order[];
  public public_key: string = ""
  public id: number = 0;


  constructor(public portfolioService: PortfolioServiceService, private router: Router, private activatedRoute: ActivatedRoute, public authService: AuthenticationService) { }

  ngOnInit(): void {

    this.getPortfolioInfo();
    this.getAllExtensions();

    console.log(">> this portfolio: " + this.portfolio)
    console.log(">>> all extensions: " + this.allExtensions)

    // this.portfolioService.getPortfolio(id).pipe(
    //   mergeMap((portfolio) => this.portfolioService.getAllExtensions())
    // ).subscribe((allExtensions) => {
    //   this.portfolio = this.portfolio;
    //   this.allExtensions = allExtensions;
    // });



    // this.name = portfolio.name;
    // this.extensions = portfolio.extensions;
    // this.orders = portfolio.orders;
    // this.public_key = portfolio.public_key;
    // this.id = portfolio.id

    // this.extensions = extensions;
  }


  getPortfolioInfo(): void {

    const url_array = this.router.url.split("/");
    const id = +url_array[url_array.length - 1];

    this.portfolioService.getPortfolio(id).subscribe((portfolio) => {
      this.portfolio = portfolio;
      this.name = portfolio.name;
      this.extensions = portfolio.extensions;
      this.orders = portfolio.orders;
      this.public_key = portfolio.public_key;
      this.id = portfolio.id

      console.log(portfolio)
    });
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
    if (this.portfolio === undefined) { return }
    this.portfolioService.addExtension(this.portfolio, extension).subscribe((extension) => {
    })
    window.location.reload();
  }

  deleteExtension(extension: Extension): void {
    if (this.portfolio === undefined) { return }
    this.portfolioService.deleteExtension(this.portfolio, extension).subscribe((extension) => {
    })
    window.location.reload();
  }


  // getPortfolioExtensions(): void {
  //   if (this.portfolio === undefined) { return }
  //   this.portfolioService.getPortfolioExtensions(this.portfolio).subscribe((extensions) => {
  //     this.extensions = extensions;
  //     console.log("extensions list (of portfolio)")
  //     console.log(extensions)
  //   })
  // }

  getAllExtensions(): void {
    this.portfolioService.getAllExtensions().subscribe((extensions) => {
      this.allExtensions = extensions;
      console.log("all extensionsssssssssssssssssss")
      console.log(this.allExtensions)
    })
  }

  isInstalled(extension: Extension) {
    let installed = false;
    this.extensions.forEach(ext => {
      if (ext.id === extension.id) {
        installed = true;
      }
    });
    return installed;
  }

  getData = (parameters: Object) => this.portfolioService.getPortfolioDetails(parameters, this.portfolio.id)

  columns: DataTables.ColumnSettings[] = [
    { title: '#', data: 'id' },
    { title: 'Currency', render: (a, b, row) => `<img style="height: 20px;" src="${row.logo_url}"> ${row.name}`, orderable: false },
    { title: 'Quantity', data: 'quantity' },
    { title: 'Volume', data: 'volume' },
    { render: (a, b, row) => `<a href="/coins/${row.id}"><button type="button" class="btn btn-primary btn-sm">Details</button></a>`, orderable: false },
  ]

}
