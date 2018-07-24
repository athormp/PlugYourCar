import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';
import { Posicion } from '../../model/posicion.model';

// servicio para obtener los detalles de los puntos de carga
@Injectable()
export class LocalizacionService {

  baseUrl: string = 'http://192.168.0.159:8080';

  constructor(private http: HttpClient) {
  }

  obtenerResumenLocalizaciones(posicion: Posicion): Observable<any> {
    console.log('Obteniendo datos de puntos de carga relativos a la latitud ' + posicion.latitud + ', longitud ' + posicion.longitud + ' y radio ' + posicion.radio);
    return this.http.post(this.baseUrl + '/puntocarga', posicion);
  }
}