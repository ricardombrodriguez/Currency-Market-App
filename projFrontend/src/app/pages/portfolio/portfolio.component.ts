import { Component, Input, OnInit } from '@angular/core';
import { PortfolioServiceService } from './../../services/portfolio-service.service';
import {Portfolio} from './../../interfaces/portfolio';
import { Router } from '@angular/router';

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})
export class PortfolioComponent implements OnInit {

  constructor(public portfolioService: PortfolioServiceService, private router: Router) { }

  public portfolio!: Portfolio;

  ngOnInit(): void { 

    const url_array = this.router.url.split("/");
    const id = +url_array[url_array.length - 1];
    this.portfolioService.getPortfolio(id).subscribe( (portfolio) => {
      this.portfolio = portfolio;
    });


  }
}
