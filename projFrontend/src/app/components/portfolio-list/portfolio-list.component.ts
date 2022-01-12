import { Component, Input, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Portfolio } from 'src/app/interfaces/portfolio';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { PortfolioServiceService } from 'src/app/services/portfolio-service.service';

@Component({
  selector: 'app-portfolio-list',
  templateUrl: './portfolio-list.component.html',
  styleUrls: ['./portfolio-list.component.css']
})
export class PortfolioListComponent implements OnInit {

  @Input() public portfolios: Portfolio[] = []
  public observable: Observable<Portfolio[]>;
  public userID: any

  constructor(public portfolioService: PortfolioServiceService, public authService: AuthenticationService) {

    this.userID = this.authService.curentUserId
    this.observable = this.portfolioService.getPortfolios(this.userID)
    console.log("user id:")
    console.log(this.userID)

  }

  ngOnInit(): void {


    this.observable.subscribe((portfolios) => {
      this.portfolios = portfolios;
    });

  }

  getData = (parameters: object) => {
    return this.portfolioService.getPage(parameters)
  }

}