import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-market',
  templateUrl: './market.component.html',
  styleUrls: ['./market.component.css']
})
export class MarketComponent implements OnInit {

  data = [ 15339, 21345, 18483, 24003, 23489, 24092, 12034 ]
  labels = [ 'Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday' ]

  constructor() { }

  ngOnInit(): void {
  }

}
