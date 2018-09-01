import { Geolocation } from '@ionic-native/geolocation';
import { RegistroService } from '../pages/registro/registro.service';
import { AuthService } from '../pages/login/auth.service';
import { LocalizacionService } from '../pages/localizacion/localizacion.service';
import { GestionCargaService } from '../pages/gestionCarga/gestionCarga.service';
import { TokenStorage } from '../pages/login/token.storage';
import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';
import { PlugYourCarApp } from './app.component';
import { HeaderComponent } from '../pages/header/header';
import { LoginPage } from '../pages/login/login';
import { LocalizacionPage } from '../pages/localizacion/localizacion';
import { HistoricoCargasPage } from '../pages/historicoCargas/historicoCargas';
import { GestionCargaPage } from '../pages/gestionCarga/gestionCarga';
import { RegistroPage } from '../pages/registro/registro';
import { DetallePuntoCargaPage } from '../pages/detallePuntoCarga/detallePuntoCarga';
import { SeleccionarConectorModal } from '../pages/seleccionarConectorModal/seleccionarConectorModal';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { GoogleMaps } from '@ionic-native/google-maps';
import { Interceptor } from '../pages/login/auth.interceptor';
import { RoundProgressModule } from 'angular-svg-round-progressbar';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core' 

@NgModule({
  declarations: [
    PlugYourCarApp,
    HeaderComponent,
    LoginPage,
    LocalizacionPage,
    GestionCargaPage,
    HistoricoCargasPage,
    RegistroPage,
    DetallePuntoCargaPage,
    SeleccionarConectorModal
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    IonicModule.forRoot(PlugYourCarApp),
    RoundProgressModule
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    PlugYourCarApp,
    HeaderComponent,
    LoginPage,
    LocalizacionPage,
    GestionCargaPage,
    HistoricoCargasPage,
    RegistroPage,
    DetallePuntoCargaPage,
    SeleccionarConectorModal
  ],
  providers: [
    StatusBar,
    SplashScreen,
    RegistroService,
    AuthService,
    LocalizacionService,
    GestionCargaService,
    TokenStorage,
    Geolocation,
    GoogleMaps,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    {provide: HTTP_INTERCEPTORS, useClass: Interceptor, multi: true}
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
})
export class AppModule {}
