import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { CoinsComponent } from './pages/coins/coins.component';
import { MarketsComponent } from './pages/markets/markets.component';
import { CoinComponent } from './pages/coin/coin.component';
import { MarketComponent } from './pages/market/market.component';
import { PortfolioComponent } from './pages/portfolio/portfolio.component';
import { NgChartsModule } from 'ng2-charts';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    CoinsComponent,
    MarketsComponent,
    CoinComponent,
    MarketComponent,
    PortfolioComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgChartsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
