import { Component } from '@angular/core';
import { TokenStorage } from '../login/token.storage';
import { LoadingController } from 'ionic-angular';

@Component({
  selector: 'app-header',
  templateUrl: './header.html'
})
export class HeaderComponent {

  constructor(private token: TokenStorage, public loadingCtrl: LoadingController) {
  }
  
  mostrarIconos() {
    return this.token.loggedIn(); 
  }

  logOut() {
    const loader = this.loadingCtrl.create({
      content: "Please wait...",
      duration: 3000
    });
    loader.present();
    console.log("Usuario saliendo");
    return this.token.logOut(); 
  }
}