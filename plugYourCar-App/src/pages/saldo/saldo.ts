import { Saldo } from '../../model/saldo.model';
import { Component } from '@angular/core';
import { NavController, NavParams, LoadingController } from 'ionic-angular';
import { SaldoService } from './saldo.service';

@Component({
    selector: 'saldo',
    templateUrl: 'saldo.html'
})
export class SaldoPage {

    saldo: Saldo;
    cantidades = [
        { id: 10, name: '10€' },
        { id: 20, name: '20€' },
        { id: 50, name: '50€' },
        { id: 75, name: '75€' },
        { id: 100, name: '100€' },
        { id: 150, name: '150€' }
    ];
    mensajeResultado: string;

    constructor(
        public navCtrl: NavController, public navParams: NavParams, public loadingCtrl: LoadingController, private saldoService: SaldoService
    ) {
        this.saldo = this.navParams.get('saldo');
    }

    cargarSaldo(cantidad) {
        let saldoDTO = new Saldo();
        saldoDTO.id = this.saldo.id;
        saldoDTO.cantidad = cantidad.id;
        console.log("Petición de carga de saldo: " + cantidad.id + "€");
        this.saldoService.cargarSaldo(saldoDTO).subscribe(
            data => {
              const loader = this.loadingCtrl.create({
                content: "Autorizando recarga, conectando con entidad bancaria",
                duration: 5000
              });
              loader.present();
              this.saldo.cantidad = this.saldo.cantidad + cantidad.id;
            },
            error => {
              console.log(error)
              if (error.error.errorCode === "1002") {
                console.log(error.error.errorMessage);
                this.mensajeResultado = error.error.errorMessage;
              }
            }); 
    }
}