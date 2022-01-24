import { AuthenticationService } from './services/authentication.service';
import { Component } from '@angular/core';
import { PortfolioServiceService } from './services/portfolio-service.service';
import { ActivatedRoute } from '@angular/router';
import { Coin } from './interfaces/coin';

import { SearchService } from './services/search.service';

import { Router } from '@angular/router';
import { timeout } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})

export class AppComponent {
  loggedId: boolean = false;

  logInData: { email: string; password: string } = {
    email: '',
    password: '',
  };

  signUpData: {
    username: string;
    fullname: string;
    email: string;
    password: string;
    passwordRepeat: string;
  } = {
      username: '',
      fullname: '',
      email: '',
      password: '',
      passwordRepeat: '',
    };

  resetData = () => {
    this.logInData.email = '';
    this.logInData.password = '';

    this.signUpData.username = '';
    this.signUpData.fullname = '';
    this.signUpData.email = '';
    this.signUpData.password = '';
    this.signUpData.passwordRepeat = '';
  };

  logIn = () =>
    this.authService.logIn(this.logInData.email, this.logInData.password);
  signUp = () => {
    if (this.signUpData.password === this.signUpData.passwordRepeat) {
      this.authService.signUp(
        this.signUpData.username,
        this.signUpData.fullname,
        this.signUpData.email,
        this.signUpData.password
      );
      window.location.reload();
    }
  };
  logOut = () => this.authService.logOut();

  public userID: any
  
  public constructor(
    public authService: AuthenticationService,
    public service: PortfolioServiceService,
    public searchService: SearchService,
    private router: Router
  ) {
    this.loggedId = authService.isLoggedIn();
    this.userID = this.authService.curentUserId

    authService.userIdObs.subscribe((data) => {
      this.loggedId = data !== null;
      if (this.loggedId) {
        $('.modal-backdrop').remove();
        this.resetData();
      }
    });
  }

  ngOnInit() { 
  }

  portfolio = { pname: "", pkey: "" }
  public error = false;


  createPortfolio() {
    this.error = false;

    if ((<HTMLInputElement>document.getElementById("name")).value == "") {
      this.error = true;
      return
    }

    this.service.addPortfolio((<HTMLInputElement>document.getElementById("name")).value, parseInt(this.authService.curentUserId!)).subscribe((response) => { });
    window.location.reload()

  }

  joinPortfolio() {
    this.error = false;

    if ((<HTMLInputElement>document.getElementById("publicKey")).value == "") {
      this.error = true;
      return
    }

    this.service.joinPortfolio((<HTMLInputElement>document.getElementById("publicKey")).value, parseInt(this.authService.curentUserId!)).subscribe((response) => { });
    window.location.reload()
  }


  public coin_data: Coin[] = []

  onEnter() {
    this.searchService.getData( (<HTMLInputElement>document.getElementById("searchBar")).value ).subscribe(data => this.coin_data = data)
  }

  clean() {
    setTimeout( () => this.coin_data = [], 500 )
  }

  href(id: number) {
    window.location.href = "/coins/"+id;
  }
}
