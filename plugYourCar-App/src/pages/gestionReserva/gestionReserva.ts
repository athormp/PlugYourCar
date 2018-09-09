import { WeekViewComponent } from 'ionic2-calendar/weekview';
import { DayViewComponent } from 'ionic2-calendar/dayview';
import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { registerLocaleData } from '@angular/common';
import localeEs from '@angular/common/locales/es';
import * as moment from 'moment';
import { MonthViewComponent } from 'ionic2-calendar/monthview';
import { GestionCargaService } from '../gestionCarga/gestionCarga.service';
import { FechasReserva } from '../../model/fechasReserva';
import { HistoricoCargasPage } from '../historicoCargas/historicoCargas';
registerLocaleData(localeEs);

@Component({
  selector: 'gestionReserva',
  templateUrl: 'gestionReserva.html'
})
export class GestionReservaPage {

  fechasReserva: FechasReserva;
  reservaFechas = { startTime: new Date().toISOString(), endTime: new Date().toISOString() };
  reserva = { title: "RESERVADO", startTime: new Date(), endTime: new Date(), allDay: false }; 
  viewTitle: string;
  diaReserva = new Date();
  minDate : string;
  maxDate : string;
  conectorId: string;
  tipoOperacionCarga: boolean;
  calendar = {
    mode: 'week',
    currentDate: this.diaReserva,
    locale: 'es'
  };
  minuteValues= [0,15,30,45];
  eventSource = [
    {
      title: 'test',
      startTime: new Date(2018, 8, 1, 18, 15),
      endTime: new Date(2018, 8, 1, 18, 45),
      allDay: false
    },
    {
      title: 'test',
      startTime: new Date(2018, 7, 29, 19, 15),
      endTime: new Date(2018, 7, 29, 19, 45),
      allDay: false
    },
    {
      title: 'test',
      startTime: new Date(2018, 7, 28, 20, 15),
      endTime: new Date(2018, 7, 28, 20, 45),
      allDay: false
    }
  ]

  constructor(public navCtrl: NavController, private navParams: NavParams, private gestionCargaService: GestionCargaService) {
    console.log(this.eventSource);
    this.minDate = new Date().toISOString();
    this.fechasReserva = new FechasReserva();
    let date = new Date();
    let date2 = new Date();
    date.setDate(date2.getDate() + 7);
    this.maxDate = date.toISOString();
    this.conectorId = this.navParams.get('conectorId');
    this.tipoOperacionCarga = this.navParams.get('tipoOperacionCarga');
  }

  realizarReserva() {
    this.fechasReserva.fechaInicio = this.reservaFechas.startTime;
    this.fechasReserva.fechaFin = this.reservaFechas.endTime;
    this.gestionCargaService.iniciarCarga(this.conectorId, true, this.fechasReserva).subscribe(
      data => {
        console.log("Solicitando reserva para el conector " + this.conectorId + " entre las fechas " 
          + this.fechasReserva.fechaInicio + ", " + this.fechasReserva.fechaFin);
        this.navCtrl.push( HistoricoCargasPage );
      },
      error => {
        console.log(error);
      });
  }

  onViewTitleChanged(title) {
    this.viewTitle = title;
  }

  onTimeSelected(ev) {

  }

  onEventSelected(event) {

  }

  revisarSolapamientos() {
    for (var i = 0; i < this.eventSource.length; i++) {
      console.log(this.eventSource[i].startTime.toISOString() + " / " + this.reservaFechas.startTime);
      if (this.eventSource[i].startTime <= new Date(this.reservaFechas.startTime) && this.eventSource[i].endTime >= new Date(this.reservaFechas.startTime)) {
        console.log("Se cumple la comparación");
        this.reservaFechas.startTime = this.eventSource[i].endTime.toISOString();
        console.log(this.reservaFechas.startTime);
      }
    }
  }
}