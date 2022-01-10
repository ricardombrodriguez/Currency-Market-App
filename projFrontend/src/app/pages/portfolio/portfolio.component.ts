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

  public data!: Portfolio;

  ngOnInit(): void { }



}
