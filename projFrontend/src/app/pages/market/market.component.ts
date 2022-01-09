import { Order } from './../../interfaces/order';
import { Page } from './../../components/data-table/page';
import { Observable } from 'rxjs';
import { OrderServiceService } from './../../services/order-service.service';
import { MarketServiceService } from './../../services/market-service.service';
import { Component, OnInit } from '@angular/core';
import { Chart } from 'chart.js';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-market',
  templateUrl: './market.component.html',
  styleUrls: ['./market.component.css']
})
export class MarketComponent implements OnInit {

  data: number[] = []
  labels: string[] = []

  marketId: number = this.route.snapshot.params['id']
  marketSymbol: string = ""
  active: boolean = false

  columns: DataTables.ColumnSettings[] = [
    { title: '#', data: 'id' },
    { title: 'Amount', data: '' },
    { title: 'Price', data: '' },
  ]

  getSellOrdersData = (parameters: Object) => this.orderService.getSellOrdersPage(this.marketId, parameters)
  getBuyOrdersData = (parameters: Object) => this.orderService.getBuyOrdersPage(this.marketId, parameters)

  constructor(private marketService: MarketServiceService, private orderService: OrderServiceService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.marketService.getMarket(this.marketId).subscribe(data => {
      this.marketSymbol = data.originCurrency.symbol + '-' + data.destinyCurrency.symbol
      this.active = data.originCurrency.online && data.destinyCurrency.online

      this.data = data.tickers.map(t => t.prev_value)
      this.labels = data.tickers.map(t => ((new Date(t.createdAt)).toLocaleString()))

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
