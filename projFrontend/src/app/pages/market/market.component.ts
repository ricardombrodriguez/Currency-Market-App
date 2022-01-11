import { Order } from './../../interfaces/order';
import { Page } from './../../components/data-table/page';
import { Observable } from 'rxjs';
import { OrderServiceService } from './../../services/order-service.service';
import { MarketServiceService } from './../../services/market-service.service';
import { Component, Input, OnInit } from '@angular/core';
import { Chart } from 'chart.js';
import { ActivatedRoute } from '@angular/router';
import { Coin } from 'src/app/interfaces/coin';


@Component({
  selector: 'app-market',
  templateUrl: './market.component.html',
  styleUrls: ['./market.component.css']
})
export class MarketComponent implements OnInit {

  data: number[] = []
  labels: string[] = []

  @Input() marketId: number = this.route.snapshot.params['id']
  @Input() marketSymbol: string = ""
  @Input() active: boolean = false
  @Input() originCurrency: Coin | null = null
  @Input() destinyCurrency: Coin | null = null
  @Input() price: number = 0
  @Input() hourChange: number = 0
  @Input() minuteChange: number = 0

  columns: DataTables.ColumnSettings[] = [
    { title: '#', data: 'id' },
    { title: 'Amount', data: '' },
    { title: 'Price', data: '' },
  ]

  getSellOrdersData = (parameters: Object) => this.orderService.getSellOrdersPage(this.marketId, parameters)
  getBuyOrdersData = (parameters: Object) => this.orderService.getBuyOrdersPage(this.marketId, parameters)

  constructor(private marketService: MarketServiceService, private orderService: OrderServiceService, private route: ActivatedRoute) { }

  ngOnInit(): void {

    this.marketService.getMarket(this.marketId).subscribe( market => {
      this.marketSymbol = market.originCurrency.symbol + '-' + market.destinyCurrency.symbol
      this.originCurrency = market.originCurrency
      this.destinyCurrency = market.destinyCurrency
      this.price = market.price
      this.hourChange = market.hourChange
      this.minuteChange = market.minuteChange
      this.active = market.originCurrency.online && market.destinyCurrency.online

      this.data = market.tickers.map(t => t.prev_value)
      this.labels = market.tickers.map(t => ((new Date(t.createdAt)).toLocaleString()))

      if (this.active) {
        this.marketService.startTickerUpdates(this.marketSymbol, message => {
          this.data.push(JSON.parse(message.body).lastTradeRate);
          this.labels.push((new Date()).toLocaleString());

          if (this.data.length > 200) {
            this.data.shift()
            this.labels.shift()
          }

          Chart.getChart('marketChart')!.update()
        })
      }
      
    })
    
  }
}
