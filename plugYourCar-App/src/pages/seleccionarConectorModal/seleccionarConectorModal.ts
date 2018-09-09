import { Platform, ViewController, NavParams, LoadingController, NavController } from 'ionic-angular';
import { Component } from '@angular/core';
import { GestionCargaService } from '../gestionCarga/gestionCarga.service';
import { GestionReservaPage } from '../gestionReserva/gestionReserva';

@Component({
  selector: 'seleccionarConectorModal',
  templateUrl: 'seleccionarConectorModal.html'
})

// clase responsable de generar la vista modal cuando el usuario intenta iniciar una carga sobre alguno de los equipos de suministro.
// solo se presenta para los casos en que el número de conectores de un equipo de suministro es mayor que 1
export class SeleccionarConectorModal {

  conectoresLibres: any[];
  tipoOperacionCarga: boolean;
  idConector: string;
  errorMessage: string;

  constructor(
    public platform: Platform,
    public params: NavParams,
    public viewCtrl: ViewController,
    private gestionCargaService: GestionCargaService,
    private loadingCtrl: LoadingController,
    private navCtrl: NavController
  ) {
    this.conectoresLibres = this.params.get('conectoresLibres');
    this.tipoOperacionCarga = this.params.get('tipoOperacionCarga');
  }

  cancelar() {
    this.viewCtrl.dismiss();
  }
  aceptar() {
    console.log(this.idConector);
    if (this.idConector === undefined) {
      this.errorMessage = "Debe seleccionar un conector para poder iniciar el proceso de carga";
    } else {
      if (this.tipoOperacionCarga) {
        this.gestionCargaService.iniciarCarga(this.idConector, false, null).subscribe(
          data => {
            const loader = this.loadingCtrl.create({
              content: "Autorizando conexión por parte del operador de carga",
              duration: 5000
            });
            loader.present();
            this.viewCtrl.dismiss();
          },
          error => {
            console.log(error);
            if (error.status === 401) {
              this.viewCtrl.dismiss();
            }
            if (error.error.errorCode === '1002') {
              console.log(error.error.errorCode);
              this.errorMessage = error.error.errorMessage;
            }
          }
        );
      } else {
          this.navCtrl.setRoot(GestionReservaPage);
      }
    }
  }
}