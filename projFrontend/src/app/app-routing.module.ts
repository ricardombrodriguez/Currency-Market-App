import { DevelopersComponent } from './pages/developers/developers.component';
import { YourextensionComponent } from './pages/yourextension/yourextension.component';
import { YourextensionsComponent } from './pages/yourextensions/yourextensions.component';
import { PortfolioComponent } from './pages/portfolio/portfolio.component';
import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { CoinsComponent } from './pages/coins/coins.component';
import { CoinComponent } from './pages/coin/coin.component';
import { MarketsComponent } from './pages/markets/markets.component';
import { MarketComponent } from './pages/market/market.component';
import { TickerComponent } from './pages/ticker/ticker.component';

const routes: Routes = [
  { path: '', component: DashboardComponent },

  { path: 'coins', children: [
    { path: '', component: CoinsComponent },
    { path: ':id', component: CoinComponent }
  ] },

  { path: 'markets', children: [
    { path: '', component: MarketsComponent },
    { path: ':id', component: MarketComponent }
  ] },

  { path: 'ticker', children: [
    { path: '', component: TickerComponent },
    { path: ':id', component: TickerComponent }
  ] },

  { path: 'portfolio', children: [
    { path: '', component: PortfolioComponent },
    { path: ':id', component: PortfolioComponent }
  ] },

  { path: 'developers', children: [
    { path: '', component: DevelopersComponent },
    { path: 'yourextensions', children: [
      { path: '', component: YourextensionsComponent },
      { path: ':id', component: YourextensionComponent }
    ] }
  ] }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
