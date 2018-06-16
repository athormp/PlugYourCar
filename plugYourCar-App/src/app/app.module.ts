import { RegistroService } from './../pages/registro/registro.service';
import { AuthService } from './../pages/login/auth.service';
import { TokenStorage } from './../pages/login/token.storage';
import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';
import { PlugYourCarApp } from './app.component';
import { HeaderComponent } from '../pages/header/header';
import { LoginPage } from '../pages/login/login';
import { LocalizacionPage } from '../pages/localizacion/localizacion';
import { GestionCargaPage } from '../pages/gestionCarga/gestionCarga';
import { RegistroPage } from '../pages/registro/registro';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    PlugYourCarApp,
    HeaderComponent,
    LoginPage,
    LocalizacionPage,
    GestionCargaPage,
    RegistroPage
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    IonicModule.forRoot(PlugYourCarApp)
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    PlugYourCarApp,
    HeaderComponent,
    LoginPage,
    LocalizacionPage,
    GestionCargaPage,
    RegistroPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    RegistroService,
    AuthService,
    TokenStorage,
    {provide: ErrorHandler, useClass: IonicErrorHandler}
  ]
})
export class AppModule {}
