import { Component } from '@angular/core';
import { TokenStorage } from '../login/token.storage';
import { LoadingController, NavController } from 'ionic-angular';
import { SaldoService } from '../saldo/saldo.service';
import { Saldo } from '../../model/saldo.model';
import { SaldoPage } from '../../pages/saldo/saldo';

@Component({
  selector: 'app-header',
  templateUrl: './header.html'
})
export class HeaderComponent {

  constructor(private token: TokenStorage, private saldoService: SaldoService, public loadingCtrl: LoadingController, private navCtrl: NavController) {
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

  consultarSaldo() {
    console.log("Usuario consultando saldo");
    this.saldoService.getSaldo().subscribe(
      data => {
        console.log("Datos de saldo: " + data);
        let saldo = new Saldo();
        saldo.id = data.id;
        saldo.cantidad = data.cantidad;
        console.log("Enviando detalle del saldo a la vista de saldo");
        this.navCtrl.push(SaldoPage, { saldo });
      },
      error => {
        console.log(error);
      });
  }
}