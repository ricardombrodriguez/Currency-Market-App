import { Component, OnInit } from '@angular/core';
import { PortfolioServiceService } from './../../services/portfolio-service.service';

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})
export class PortfolioComponent implements OnInit {

  constructor(public portfolioService: PortfolioServiceService) { }

  ngOnInit(): void {}

  getData = (parameters: object) => {
    return this.portfolioService.getPage(parameters)
  }

}
