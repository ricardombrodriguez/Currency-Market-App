import { MarketServiceService } from './../../services/market-service.service';
import { Component, OnInit } from '@angular/core';
import { Chart } from 'chart.js';


@Component({
  selector: 'app-market',
  templateUrl: './market.component.html',
  styleUrls: ['./market.component.css']
})
export class MarketComponent implements OnInit {

  data: number[] = []
  labels: string[] = []

  constructor(private marketService: MarketServiceService) { }

  ngOnInit(): void {
    this.marketService.startTickerUpdates('BTC-EUR', message => { 
      this.data.push(parseInt(JSON.parse(message.body).lastTradeRate)); 
      this.labels.push((new Date()).toLocaleString());

      Chart.getChart('marketChart')!.update()
    })
  }

}
