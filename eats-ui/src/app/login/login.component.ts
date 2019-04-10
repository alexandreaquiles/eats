import { Component } from '@angular/core';

import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {

  loginInfo: any = {};

  constructor(private authenticationService: AuthenticationService) {
  }

  efetuaLogin() {
    this.authenticationService.efetuaLogin(this.loginInfo)
      .subscribe(token => alert(JSON.stringify(token)));
  }
}
