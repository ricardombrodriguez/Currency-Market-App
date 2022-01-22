import { SearchService } from './../../services/search.service';
import { ColumnInterface } from './../../components/data-table/column-interface';
import { Component, OnInit } from '@angular/core';
import { Coin } from './../../interfaces/coin';
import { ActivatedRoute } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  public searchText: string = "";
  public coins_data!: Coin[];
  coinsEmitter$ = new BehaviorSubject<Coin[]>(this.coins_data);

  constructor(private route: ActivatedRoute,public searchService: SearchService, private changeDetector : ChangeDetectorRef) { 
  }

  ngOnInit(): void {}

  search(searchText:string): void {
    // this.searchText = this.route.snapshot.params['search'];
    console.log("initialize search of '"+ searchText +"'...")

    // coins
    this.searchService.getData(searchText).subscribe((coin) => {
      this.coinsEmitter$.next(coin);
      this.coins_data = coin;
      this.changeDetector.detectChanges();
      console.log("COINS DATA 1", this.coins_data )
    })

    console.log("COINS DATA 2", this.coins_data)


  }

}
