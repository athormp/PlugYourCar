import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { HeaderComponent } from '../header/header';
import { AuthService } from './auth.service';
import { TokenStorage } from './token.storage';

@Component({
  selector: 'login',
  templateUrl: 'login.html'
})
export class LoginPage {

  constructor(public navCtrl: NavController, private authService: AuthService, private token: TokenStorage) {
  }

  dniNie: string;
  password: string;

  login(): void {
    this.authService.autenticar(this.dniNie, this.password).subscribe(
      data => {
        this.token.guardarToken(data.token);
      }
    );
  }
}
