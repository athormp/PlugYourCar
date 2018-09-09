import { GestionCargaService } from './../gestionCarga/gestionCarga.service';
import { DetallePuntoCargaPosicion } from '../../model/detallePuntoCargaPosicion';
import { Component } from '@angular/core';
import { NavController, NavParams, ModalController, LoadingController } from 'ionic-angular';
import { SeleccionarConectorModal } from '../seleccionarConectorModal/seleccionarConectorModal';
import { ConectorLibre } from '../../model/conectorLibre';
import { GestionReservaPage } from '../gestionReserva/gestionReserva';

@Component({
  selector: 'detallePuntoCarga',
  templateUrl: 'detallePuntoCarga.html'
})

// Este componente es responsable de recoger los parámetros pasados por el componente Localizacion: punto de carga, posición actual del usuario para
// poder pasar la localización actual del usuario al servicio de Google Maps y la distancia de la posición actual del usuario al punto seleccionado

export class DetallePuntoCargaPage {

  detallePuntoCarga: DetallePuntoCargaPosicion;
  errorMessage: string;

  constructor(public navCtrl: NavController, 
              public navParams: NavParams, 
              public modalCtrl: ModalController, 
              private gestionCargaService: GestionCargaService,
              private loadingCtrl: LoadingController) {
    this.detallePuntoCarga = navParams.get('data');
    console.log("Obteniendo parámetros: " + navParams.get('data'));
  }

  aplicarColorTextoConector(conector) {
    return {
      'text-grey': conector.estadoConector.id === 0,
      'text-green': conector.estadoConector.id === 1,
      'text-red': conector.estadoConector.id === 2,
      'text-orange': conector.estadoConector.id === 3
    };
  }

  iniciarCarga(equipoSuministro) {
    let conectoresLibres: Array<ConectorLibre> = [];
    let tipoOperacionCarga = true;
    console.log("Iniciando carga");
    if (equipoSuministro.conectores.length > 1) {
      conectoresLibres = this.cargarConectores(equipoSuministro);
      let modal = this.modalCtrl.create(SeleccionarConectorModal, { conectoresLibres, tipoOperacionCarga }, { cssClass: 'select-modal-s select-modal-m' });
      modal.present();
    } else {
      console.log(equipoSuministro.conectores[0].id);
      this.gestionCargaService.iniciarCarga(equipoSuministro.conectores[0].id, false, null).subscribe(
        data => {
          const loader = this.loadingCtrl.create({
            content: "Autorizando conexión por parte del operador de carga",
            duration: 5000
          });
          loader.present();
        },
        error => {
          console.log(error);
          if (error.error.errorCode === '1002') {
            console.log(error.error.errorCode);
            this.errorMessage = error.error.errorMessage;
          }
        }
      );
    }
  }

iniciarReserva(equipoSuministro) {
  let conectoresLibres: Array<ConectorLibre> = [];
  let tipoOperacionCarga = false;
  console.log("Iniciando proceso de reserva");
  if (equipoSuministro.conectores.length > 1) {
    conectoresLibres = this.cargarConectores(equipoSuministro);
    let modal = this.modalCtrl.create(SeleccionarConectorModal, { conectoresLibres, tipoOperacionCarga }, { cssClass: 'select-modal-s select-modal-m' });
    modal.present();
  } else {
    this.navCtrl.setRoot(GestionReservaPage, { conectorId: equipoSuministro.conectores[0].id, tipoOperacionCarga });
  }
}

cargarConectores(equipoSuministro) {
  let conectoresLibres: Array<ConectorLibre> = [];
  for (var i = 0; i < equipoSuministro.conectores.length; i++) {
    if (equipoSuministro.conectores[i].estadoConector.id === 1) {
      let conectorLibre = new ConectorLibre();
      conectorLibre.idEquipoSuministro = equipoSuministro.id;
      conectorLibre.id = equipoSuministro.conectores[i].id;
      conectorLibre.idReferencia = equipoSuministro.conectores[i].idReferencia;
      conectorLibre.tipo = equipoSuministro.tipoConector.descripcion;
      conectoresLibres.push(conectorLibre);
    }
  }
  return conectoresLibres;
}
}