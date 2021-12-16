import { Component } from '@angular/core';
import { ActivationStart, Route, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = '';
  routes: Route[] = [];

  public constructor(private router:Router) {
    this.routes = router.config;
  }
  
  ngOnInit() {
    this.router.events.subscribe(event => {
      if (event instanceof ActivationStart) {
        this.title = event.snapshot.data['title'];
      }
    });
  }
  
}
