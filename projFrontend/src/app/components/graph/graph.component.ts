import { Component, Input, OnInit } from '@angular/core';
import { ChartDataset } from 'chart.js';

@Component({
  selector: 'app-graph',
  templateUrl: './graph.component.html',
  styleUrls: ['./graph.component.css']
})

export class GraphComponent implements OnInit {

  @Input() data: number[] = []
  @Input() labels: string[] = []

  lineChartData: ChartDataset[] = [];
  
  lineChartOptions = {
    responsive: true,
    legend: { display: false }
  };

  constructor() { }

  ngOnInit(): void {
    this.lineChartData = [
      { 
        data: this.data,
        borderColor: '#007bff',
        borderWidth: 4,
        pointBackgroundColor: '#007bff',
        pointBorderColor: '#007bff',
        pointHoverBackgroundColor: '#007bff',
        pointHoverBorderColor: '#007bff',
      },
    ];
  }

}
