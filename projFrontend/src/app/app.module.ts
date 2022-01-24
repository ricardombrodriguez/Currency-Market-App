import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { CoinsComponent } from './pages/coins/coins.component';
import { MarketsComponent } from './pages/markets/markets.component';
import { CoinComponent } from './pages/coin/coin.component';
import { TickerComponent } from './pages/ticker/ticker.component';
import { MarketComponent } from './pages/market/market.component';
import { PortfolioComponent } from './pages/portfolio/portfolio.component';

import { PortfolioListComponent } from './components/portfolio-list/portfolio-list.component';
import { NgChartsModule } from 'ng2-charts';
import { DataTablesModule } from 'angular-datatables';
import { DataTableComponent } from './components/data-table/data-table.component';
import { GraphComponent } from './components/graph/graph.component';
import { FormsModule } from '@angular/forms';
import { YourextensionsComponent } from './pages/yourextensions/yourextensions.component';
import { YourextensionComponent } from './pages/yourextension/yourextension.component';
import { DevelopersComponent } from './pages/developers/developers.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    CoinsComponent,
    MarketsComponent,
    CoinComponent,
    TickerComponent,
    MarketComponent,
    PortfolioComponent,
    PortfolioListComponent,
    DataTableComponent,
    GraphComponent,
    YourextensionsComponent,
    YourextensionComponent,
    DevelopersComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgChartsModule,
    HttpClientModule,
    DataTablesModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
