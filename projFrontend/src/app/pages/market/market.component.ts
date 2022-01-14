import { AuthenticationService } from 'src/app/services/authentication.service';
import { Portfolio } from 'src/app/interfaces/portfolio';
import { PortfolioServiceService } from 'src/app/services/portfolio-service.service';
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

  marketId: number = this.route.snapshot.params['id']
  marketSymbol: string = ""
  active: boolean = false
  originCurrency: Coin | null = null
  destinyCurrency: Coin | null = null
  price: number = 0
  hourChange: number = 0
  minuteChange: number = 0

  columns: DataTables.ColumnSettings[] = [
    { title: '#', data: 'id' },
    { title: 'Amount', data: 'quantity', render: d => Math.abs(d) },
    { title: 'Price', data: 'order_value' },
  ]

  portfolios: Portfolio[] = []
  orderPortfolio: number | null = null
  orderProps: { amount: number, price: number } = { amount: 0, price: 0 }

  getSellOrdersData = (parameters: Object) => this.orderService.getSellOrdersPage(this.marketId, parameters)
  getBuyOrdersData = (parameters: Object) => this.orderService.getBuyOrdersPage(this.marketId, parameters)

  placeOrder = () => {
    if (this.orderPortfolio) this.orderService.createOrder(this.marketId, this.orderPortfolio, this.orderProps.amount * (document.querySelector('#pills-buy-tab.active') ? 1 : -1), this.orderProps.price)
  }

  constructor(
    private marketService: MarketServiceService, 
    private orderService: OrderServiceService, 
    private portfolioService: PortfolioServiceService, 
    private authService: AuthenticationService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {

    if (this.authService.curentUserId != null) {
     this.portfolioService.getPortfolios(parseInt(this.authService.curentUserId)).subscribe(data => this.portfolios = data)
    }
    
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

        this.orderService.startOrderUpdates(this.marketSymbol, message => {
          let order = <Order>JSON.parse(message.body)
          let tableId = order.quantity > 0 ? 'buyOrders' : 'sellOrders'
          $('#' + tableId).DataTable().row.add({id: order.id, quantity: order.quantity, order_value: order.order_value}).draw()
        })
      }
      
    })
    
  }
}
