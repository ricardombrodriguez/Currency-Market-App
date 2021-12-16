import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-market',
  templateUrl: './market.component.html',
  styleUrls: ['./market.component.css']
})
export class MarketComponent implements OnInit {
  public lineChartData = [
    { 
      data: [ 15339, 21345, 18483, 24003, 23489, 24092, 12034 ],
      borderColor: '#007bff',
      borderWidth: 4,
      pointBackgroundColor: '#007bff',
      pointBorderColor: '#007bff',
      pointHoverBackgroundColor: '#007bff',
      pointHoverBorderColor: '#007bff',
    },
  ];
  public lineChartLabels = [ 'Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday' ];
  public lineChartOptions = {
    responsive: true,
    legend: { display: false }
  };
  public lineChartLegend = false;
  public lineChartPlugins = [];

  constructor() { }

  ngOnInit(): void {
  }

}
