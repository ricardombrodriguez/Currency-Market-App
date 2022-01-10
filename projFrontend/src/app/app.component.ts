import { Component } from '@angular/core';
import { ActivationStart, Route, Router } from '@angular/router';

import { AppService } from './app.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = '';
  routes: Route[] = [];

  public constructor(private router:Router, public service: AppService) {
    this.routes = router.config;
  }
  
  ngOnInit() {
    this.router.events.subscribe(event => {
      if (event instanceof ActivationStart) {
        this.title = event.snapshot.data['title'];
      }
    });
  }
  

  createPortfolio() {
    this.service.addPortfolio((<HTMLInputElement>document.getElementById('name')).value).subscribe(response => {});
  }
}
