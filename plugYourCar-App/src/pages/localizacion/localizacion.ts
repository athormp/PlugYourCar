import { Observable } from 'rxjs/Observable';
import { Component, NgZone } from '@angular/core';
import { Geolocation, GeolocationOptions, Geoposition } from '@ionic-native/geolocation';
import {
  GoogleMaps,
  GoogleMap,
  GoogleMapsEvent,
  LatLng,
  CameraPosition,
  MarkerOptions,
  GoogleMapsMapTypeId,
  Geocoder
} from '@ionic-native/google-maps';

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

  constructor(
    private geolocation: Geolocation, private zona: NgZone
  ) {
    this.lugares = [];
    this.GoogleAutocomplete = new google.maps.places.AutocompleteService();
    this.geocoder = new google.maps.Geocoder;
    this.autocompletado = {
      input: ''
    };
    this.opcionesBusqueda = {
      componentRestrictions: { country: "es" }
    }
  }

  getCurrentPosition() {
    this.geolocation.getCurrentPosition()
      .then(position => {
        console.log("Ok posicion actual");
        this.posicionActual = {
          latitude: position.coords.latitude,
          longitude: position.coords.longitude
        }
        this.loadMap();
      })
      .catch(error => {
        console.log(error);
      })
  }

  loadMap() {

    // craer mapa
    this.mapa = GoogleMaps.create('map');

    // asignar posición de la cámara
    let posicion: CameraPosition<LatLng> = {
      target: new LatLng(this.posicionActual.latitude, this.posicionActual.longitude),
      zoom: 12,
      tilt: 30
    };

    this.mapa.one(GoogleMapsEvent.MAP_READY).then(() => {
      console.log('Map is ready!');
      console.log(this.posicionActual.latitude, this.posicionActual.longitude);

      // mover la cámara a la posición actual
      this.mapa.moveCamera(posicion);

      // incluir marcador de la posición actual
      let opcionesMarcador: MarkerOptions = {
        position: this.posicionActual
      };

      this.addMarcador(opcionesMarcador);

      this.marcadores.forEach(marcador => {
        this.addMarcador(marcador);
      });

    });
  }

  ionViewDidLoad() {
    this.getCurrentPosition();
  }

  addMarcador(opciones) {
    let opcionesMarcador: MarkerOptions = {
      position: new LatLng(opciones.position.latitude, opciones.position.longitude),
      title: opciones.title,
      icon: opciones.icon
    };
    this.mapa.addMarker(opcionesMarcador);
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
    //this.clearMarkers();
    this.lugares = [];

    this.geocoder.geocode({ 'placeId': lugar.place_id }, (resultados, status) => {
      if (status === 'OK' && resultados[0]) {
        // asignar posición de la cámara para el lugar buscado
        let posicionBusqueda: CameraPosition<LatLng> = {
          target: resultados[0].geometry.location,
          zoom: 12,
          tilt: 30
        };

        // llevar la cámara a la posición de búsqueda
        this.mapa.moveCamera(posicionBusqueda);
      }
    });
  }


}