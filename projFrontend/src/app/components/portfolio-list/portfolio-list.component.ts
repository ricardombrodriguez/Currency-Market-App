import { Component, Input, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Portfolio } from 'src/app/interfaces/portfolio';
import { PortfolioServiceService } from 'src/app/services/portfolio-service.service';

@Component({
  selector: 'app-portfolio-list',
  templateUrl: './portfolio-list.component.html',
  styleUrls: ['./portfolio-list.component.css']
})
export class PortfolioListComponent implements OnInit {

  @Input() public portfolios: Portfolio[] = []
  public observable: Observable<Portfolio[]>;

  constructor(public portfolioService: PortfolioServiceService) { 

    this.observable = this.portfolioService.getPortfolios()

  }

  ngOnInit(): void {

    this.observable.subscribe( (portfolios) => {
      this.portfolios = portfolios;
    });
    
  }

}