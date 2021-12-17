import { PageableService } from './../../components/data-table/pageable-service';
import { CoinServiceService } from './../../services/coin-service.service';
import { ColumnInterface } from './../../components/data-table/column-interface';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-coins',
  templateUrl: './coins.component.html',
  styleUrls: ['./coins.component.css']
})
export class CoinsComponent implements OnInit {

  columns: ColumnInterface[] = [
    { name: '#' },
    { name: 'Symbol' },
    { name: 'Name' },
    { name: '', sort: false },
  ]

  constructor(public service: CoinServiceService) { }

  ngOnInit(): void {
  }

}
