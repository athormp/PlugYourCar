import { Component } from '@angular/core';
import { NavController, LoadingController } from 'ionic-angular';
import { AuthService } from './auth.service';
import { TokenStorage } from './token.storage';
import { RegistroPage } from '../registro/registro';
import { LocalizacionPage } from '../localizacion/localizacion';
import { FormGroup, Validators, FormControl } from '@angular/forms';

@Component({
  selector: 'login',
  templateUrl: 'login.html'
})
export class LoginPage {

  paginaEnlazada: any;
  loginForm: FormGroup;
  mensajes_validacion;
  errorResponse: boolean;
  successResponse: boolean;
  mensajeResultado: string;

  constructor(public navCtrl: NavController, private authService: AuthService, private token: TokenStorage, private loadingCtrl: LoadingController) {
    this.paginaEnlazada = RegistroPage;
    this.loginForm = new FormGroup({
      dniNie: new FormControl(
        '', Validators.required
      ),
      password: new FormControl(
        '', Validators.required
      )
    });
    this.mensajes_validacion = {
      'dniNie': [
        { type: 'required', message: 'Campo obligatorio' },
      ],
      'password': [
        { type: 'required', message: 'Campo obligatorio' }
      ]
    }
  }

  login(): void {
    console.log('Autenticando Usuario con DNI' + this.loginForm.controls.dniNie.value);
    this.errorResponse = false;
    this.successResponse = false;
    this.authService.autenticar(this.loginForm.controls.dniNie.value, this.loginForm.controls.password.value).subscribe(
      data => {
        this.token.guardarToken(data.access_token);
        this.successResponse = true;
        const loader = this.loadingCtrl.create({
          content: "Login correcto, redirigiendo",
          duration: 3000
        });
        loader.present();
        this.navCtrl.setRoot(LocalizacionPage);
      },
      error => {
        console.log(error);
        this.errorResponse = true;
        this.mensajeResultado = "Usuario o password incorrectos";
      }
    );
  }
}
