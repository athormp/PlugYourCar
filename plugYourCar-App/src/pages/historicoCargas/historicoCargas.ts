import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { GestionCargaService } from '../gestionCarga/gestionCarga.service';
import { GestionCargaPage } from '../gestionCarga/gestionCarga';

@Component({
  selector: 'historicoCargas',
  templateUrl: 'historicoCargas.html'
})
export class HistoricoCargasPage {

  cargas: any[];

  constructor(public navCtrl: NavController, public navParams: NavParams, private gestionCargaService: GestionCargaService) {
    this.gestionCargaService.obtenerCargas().subscribe(
      data => {
        this.cargas = data;
        console.log(this.cargas);
      },
      error => {
        console.log(error);
      }
    );
  }

  mostrarDetalle(idCarga) {
    this.gestionCargaService.obtenerDetalleCarga(idCarga).subscribe(
      data => {
        console.log(data);
        this.navCtrl.push(GestionCargaPage, { data });
      },
      error => {
        console.log(error);
      }
    );
  }
}