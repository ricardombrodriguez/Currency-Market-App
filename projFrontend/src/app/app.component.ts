import { AuthenticationService } from './services/authentication.service';
import { Component } from '@angular/core';
import { PortfolioServiceService } from './services/portfolio-service.service';


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
    if (this.signUpData.password === this.signUpData.passwordRepeat)
      this.authService.signUp(
        this.signUpData.username,
        this.signUpData.fullname,
        this.signUpData.email,
        this.signUpData.password
      );
  };
  logOut = () => this.authService.logOut();

  public constructor(
    public authService: AuthenticationService,
    public service: PortfolioServiceService
  ) {
    this.loggedId = authService.isLoggedIn();

    authService.userIdObs.subscribe((data) => {
      this.loggedId = data !== null;
      if (this.loggedId) {
        $('.modal-backdrop').remove();
        this.resetData();
      }
    });
  }

  ngOnInit() {}

  portfolio = {pname: ""}
  public error = false;

  createPortfolio() {
    this.error = false;
    
    if ( (<HTMLInputElement>document.getElementById("name")).value == "" ) {
      this.error = true;
      return
    } 
    
    this.service.addPortfolio((<HTMLInputElement>document.getElementById("name")).value, parseInt(this.authService.curentUserId!)).subscribe((response) => {});
    window.location.reload()

  }
}
