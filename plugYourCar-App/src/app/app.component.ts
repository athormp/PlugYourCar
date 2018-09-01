import { Component, ViewChild } from '@angular/core';
import { Nav, Platform } from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { LoginPage } from '../pages/login/login';
import { LocalizacionPage } from '../pages/localizacion/localizacion';
import  { HistoricoCargasPage } from '../pages/historicoCargas/historicoCargas'

@Component({
  templateUrl: 'app.html'
})
export class PlugYourCarApp {
  @ViewChild(Nav) nav: Nav;

  // declaramos una nueva variable para controlar el texto mostrado
  text: string = '';

  rootPage: any = LocalizacionPage;

  pages: Array<{icon: string, title: string, component: any}>;

  constructor(public platform: Platform, public statusBar: StatusBar, public splashScreen: SplashScreen) {
    this.initializeApp();

    // líneas del menú con el título, el componente al que se llama y el icono que debe aparecer a la izquierda 
    this.pages = [
      { title: 'Login', component: LoginPage, icon: 'person' },
      { title: 'Localización', component: LocalizacionPage, icon: 'compass' },
      { title: 'Gestión Carga', component: HistoricoCargasPage, icon: 'battery-charging'}
    ];

  }

  initializeApp() {
    this.platform.ready().then(() => {
      // la plataforma está preparada y los plugins disponibles
      this.statusBar.styleDefault();
      this.splashScreen.hide();
    });
  }

  openPage(page) {
    // redirigir a la página a la que remite el menú
    this.nav.setRoot(page.component);
  }

  rightMenuClick(text) {
    this.text = text;
  }
}

