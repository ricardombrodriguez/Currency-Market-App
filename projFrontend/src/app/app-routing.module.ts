import { PortfolioComponent } from './pages/portfolio/portfolio.component';
import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { CoinsComponent } from './pages/coins/coins.component';
import { CoinComponent } from './pages/coin/coin.component';
import { MarketsComponent } from './pages/markets/markets.component';
import { MarketComponent } from './pages/market/market.component';

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

  { path: 'portfolio/:id', component: PortfolioComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
