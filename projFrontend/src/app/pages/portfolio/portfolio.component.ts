import { Component, OnInit } from '@angular/core';
import { PortfolioServiceService } from './../../services/portfolio-service.service';
import {Portfolio} from './../../interfaces/portfolio';

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})
export class PortfolioComponent implements OnInit {

  constructor(public portfolioService: PortfolioServiceService) { }

  public portfolio!: Portfolio;

  ngOnInit(): void { 

    this.portfolio = this.portfolioService!.getPortfolio()
    console.log("portfolio")
    console.log(this.portfolio)

  }
}
