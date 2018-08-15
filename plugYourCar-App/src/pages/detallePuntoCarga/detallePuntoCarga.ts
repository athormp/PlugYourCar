import { DetallePuntoCargaPosicion } from '../../model/detallePuntoCargaPosicion';
import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

@Component({
  selector: 'detallePuntoCarga',
  templateUrl: 'detallePuntoCarga.html'
})

// Este componente es responsable de recoger los parámetros pasados por el componente Localizacion: punto de carga, posición actual del usuario para
// poder pasar la localización actual del usuario al servicio de Google Maps y la distancia de la posición actual del usuario al punto seleccionado

export class DetallePuntoCargaPage {

  detallePuntoCarga: DetallePuntoCargaPosicion;

  constructor(public navCtrl: NavController, public navParams: NavParams) {
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

}