import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { HeaderComponent } from '../header/header';
import { AuthService } from './auth.service';
import { TokenStorage } from './token.storage';
import { RegistroPage } from '../registro/registro';
import { LocalizacionPage } from '../localizacion/localizacion';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { LoadingController } from 'ionic-angular';

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

  constructor(public navCtrl: NavController, private authService: AuthService, private token: TokenStorage, public loadingCtrl: LoadingController) {
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
        this.mensajeResultado = "Login correcto, redirigiendo";
        const loader = this.loadingCtrl.create({
          content: "Please wait...",
          duration: 3000
        });
        loader.present();
        setTimeout(() => {
          this.navCtrl.setRoot(LocalizacionPage);
        },
          3000);
      },
      error => {
        console.log(error.error.error);
        this.errorResponse = true;
        this.mensajeResultado = "Usuario o password incorrectos";
      }
    );
  }
}
