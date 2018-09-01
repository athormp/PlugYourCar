import { GestionCargaService } from './gestionCarga.service';
import { Component } from '@angular/core';
import { NavController, NavParams, LoadingController, ToastController } from 'ionic-angular';

@Component({
  selector: 'gestionCarga',
  templateUrl: 'gestionCarga.html'
})
export class GestionCargaPage {

  carga: any;
  mensajeResultado: string;

  constructor(public navCtrl: NavController, private navParams: NavParams, private gestionCargaService: GestionCargaService,
    private loadingCtrl: LoadingController, private toastCtrl: ToastController) {
    this.carga = navParams.get('data');
  }

  finalizarCarga(idCarga) {
    this.gestionCargaService.finalizarCarga(idCarga).subscribe(
      data => {
        console.log(data);
        this.carga = data;
        const loader = this.loadingCtrl.create({
          content: "Desconectando y descontando saldo. Libere la plaza por favor.",
          duration: 5000
        });
        loader.present();
      },
      error => {
        console.log(error);
        if (error.error.errorCode === "1002" || error.error.errorCode === "1006") {
          console.log(error.error.errorMessage);
          this.mensajeResultado = error.error.errorMessage;
          let toast = this.toastCtrl.create({
            message: this.mensajeResultado,
            duration: 4000,
            position: 'bottom',
          });         
          toast.present();
        }
      }
    );
  }
}