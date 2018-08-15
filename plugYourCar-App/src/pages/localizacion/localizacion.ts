import { DetallePuntoCargaPosicion } from './../../model/detallePuntoCargaPosicion';
import { Constants } from '../../constants/constants';
import { Component, NgZone } from '@angular/core';
import { Geolocation, GeolocationOptions } from '@ionic-native/geolocation';
import {
  GoogleMaps,
  GoogleMap,
  GoogleMapsEvent,
  LatLng,
  CameraPosition,
  MarkerOptions,
  Marker,
} from '@ionic-native/google-maps';
import { LocalizacionService } from './localizacion.service';
import { Posicion } from '../../model/posicion.model';
import { NavController, NavParams, Events, LoadingController } from 'ionic-angular';
import { DetallePuntoCargaPage } from '../detallePuntoCarga/detallePuntoCarga';
import { LoginPage } from '../login/login';

declare var google;

@Component({
  selector: 'localizacion',
  templateUrl: 'localizacion.html'
})
export class LocalizacionPage {

  mapa: GoogleMap;
  geocoder: any;
  opciones: GeolocationOptions;
  posicionActual: any = {};
  autocompletado: any;
  lugares: any;
  GoogleAutocomplete: any;
  marcadores: any[];
  opcionesBusqueda: any;
  posicion: Posicion;
  radios = [
    { id: 25, name: '25km' },
    { id: 50, name: '50km' },
    { id: 75, name: '75km' },
    { id: 100, name: '100km' },
    { id: 150, name: '150km' }
  ];
  radio: any;
  puntoCargaSeleccionado: any;

  constructor(
    public navCtrl: NavController, public navParams: NavParams, private geolocation: Geolocation, private zona: NgZone, private localizacionService: LocalizacionService, 
    private events: Events, public loadingCtrl: LoadingController
  ) {
    events.subscribe('http:forbidden', error => {
      const loader = this.loadingCtrl.create({
        content: "Se requiere estar autenticado para poder realizar esta acción, redirigiendo a página de Login",
        duration: 3000
      });
      loader.present();
      navCtrl.push(LoginPage, {errorMessage: error});
    });
    this.lugares = [];
    this.GoogleAutocomplete = new google.maps.places.AutocompleteService();
    this.geocoder = new google.maps.Geocoder;
    this.autocompletado = {
      input: ''
    };
    this.opcionesBusqueda = {
      componentRestrictions: { country: "es" }
    };
    this.posicion = new Posicion();
    this.radio = this.radios[0];
  }

  getCurrentPosition() {
    this.geolocation.getCurrentPosition()
      .then(position => {
        console.log("Ok posicion actual");
        this.posicionActual = {
          latitude: position.coords.latitude,
          longitude: position.coords.longitude
        }
        this.posicion.latitud = position.coords.latitude;
        this.posicion.latitud = position.coords.longitude;
        this.posicion.radio = this.radio;
        this.refrescarLocalizacionesProximas();
        this.loadMap();
      })
      .catch(error => {
        console.log(error);
      })
  }

  loadMap() {

    // crear mapa
    this.mapa = GoogleMaps.create('map');

    // asignar posición de la cámara
    let posicion: CameraPosition<LatLng> = {
      target: new LatLng(this.posicionActual.latitude, this.posicionActual.longitude),
      zoom: 12,
      tilt: 30
    };

    this.mapa.one(GoogleMapsEvent.MAP_READY).then(() => {
      console.log('Mapa preparado');
      console.log(this.posicionActual.latitude, this.posicionActual.longitude);

      // mover la cámara a la posición actual
      this.mapa.moveCamera(posicion);
    });
  }

  ionViewDidLoad() {
    this.getCurrentPosition();
  }

  addMarcador(puntoCarga) {
    var icon: string;
    console.log("Cargando marcador para punto de carga: " + puntoCarga.id);
    if (puntoCarga.operador !== null && puntoCarga.operador.integrado) {
      if (puntoCarga.estadoOcupacion === 0) {
        icon = Constants.LIBRE;
      }
      if (puntoCarga.estadoOcupacion > 0 && puntoCarga.estadoOcupacion < 100) {
        icon = Constants.SEMIOCUPADO;
      }
      if (puntoCarga.estadoOcupacion === 100) {
        icon = Constants.OCUPADO;
      }
    }
    if (puntoCarga.operador === null || !puntoCarga.operador.integrado) {
      if (puntoCarga.tipoEstado.id === 10 || puntoCarga.tipoEstado.id === 50 || puntoCarga.tipoEstado.id === 75) {
        icon = Constants.OPERATIVO;
      } else {
        icon = Constants.NO_OPERATIVO;
      }
    }

    let opcionesMarcador: MarkerOptions = {
      position: new LatLng(puntoCarga.localizacion.latitud, puntoCarga.localizacion.longitud),
      //snippet: puntoCarga.id,
      title: puntoCarga.id + " - " + puntoCarga.localizacion.nombre + " - " + puntoCarga.localizacion.direccion + " - " + puntoCarga.localizacion.localidad,
      icon: icon
    };

    // Creamos el marcador y le añadimos un evento que permita ir al detalle del punto de carga al hacer click sobre la info
    let marcador = this.mapa.addMarker(opcionesMarcador).then((marcador: Marker) => {
      marcador.addEventListener(GoogleMapsEvent.INFO_CLICK).subscribe(e => {
        console.log(marcador);
        this.cargarDetallePuntoCarga(marcador);
      });
    });
    this.marcadores.push(marcador);
  }

  // borrar los marcadores del mapa
  borrarMarcadores() {
    this.marcadores = [];
  }

  // llamar a componente de detalle del punto de carga junto con los parámetros del punto de carga seleccionado por el usuario y la localización actual del usuario
  cargarDetallePuntoCarga(marcador) {
    let camposTitulo = marcador.getTitle().split(" - ");
    this.localizacionService.obtenerDetalleLocalizacion(camposTitulo[0]).subscribe(
      data => {
        this.puntoCargaSeleccionado = data;
        console.log(this.puntoCargaSeleccionado);
        let detallePuntoCargaPosicion: DetallePuntoCargaPosicion = {
          puntoCarga: data,
          posicion: this.posicionActual,
          distancia: '370km'
        };
        console.log("Pasando parámetros para obtener el detalle del punto de carga con id " + detallePuntoCargaPosicion.puntoCarga.id);
        this.navCtrl.push(DetallePuntoCargaPage, { data: detallePuntoCargaPosicion });
      },
      error => {
        console.log(error);
      });
  }

  // presentar los resultados de lugares en la búsqueda autocompletada
  presentarResultadosBusqueda() {
    if (this.autocompletado.input == '') {
      this.lugares = [];
      return;
    }
    console.log(this.autocompletado.input);

    // configuramos la query de autocompletado, se limita la busqueda a España
    let config = {
      types: ['geocode'],
      input: this.autocompletado.input,
      componentRestrictions: { country: 'ES' }
    }

    // obtenemos las opciones de búsqueda autocompletadas
    this.GoogleAutocomplete.getPlacePredictions(config,
      (lugares, status) => {
        this.lugares = [];
        console.log(this.lugares);
        if (lugares) {
          this.zona.run(() => {
            lugares.forEach((lugar) => {
              this.lugares.push(lugar);
            });
          });
        }
      });
  }

  // seleccionar un lugar de la búsqueda autocompletada y obtener los puntos de carga próximos a su alrededor
  seleccionarResultadoBusqueda(lugar) {
    this.borrarMarcadores();
    this.lugares = [];

    this.geocoder.geocode({ 'placeId': lugar.place_id }, (resultados, status) => {
      if (status === 'OK' && resultados[0]) {
        // asignar posición de la cámara para el lugar buscado
        let posicionBusqueda: CameraPosition<LatLng> = {
          target: resultados[0].geometry.location,
          zoom: 12,
          tilt: 30
        };
        console.log(lugar);
        // llevar la cámara a la posición de búsqueda
        this.mapa.moveCamera(posicionBusqueda);
        // obtener resutados para la localización y el radio selaccionados por el usuario
        this.posicion.latitud = resultados[0].geometry.location.lat();
        this.posicion.longitud = resultados[0].geometry.location.lng();
        this.posicion.radio = this.radio.id;
        this.autocompletado.input = lugar.description;
        console.log(this.posicion);
        this.refrescarLocalizacionesProximas();
      }
    });
  }

  refrescarLocalizacionesProximas() {
    this.localizacionService.obtenerResumenLocalizaciones(this.posicion).subscribe(
      data => {
        console.log(data);
        data.forEach(puntoCarga => {
          this.addMarcador(puntoCarga);
        });
      },
      error => {
        console.log(error)
      });
  }
}