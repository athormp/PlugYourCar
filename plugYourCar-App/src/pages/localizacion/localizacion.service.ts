import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';
import { Posicion } from '../../model/posicion.model';
import { Constants } from '../../constants/constants';

// servicio para obtener los detalles de los puntos de carga
@Injectable()
export class LocalizacionService {
  
  constructor(private http: HttpClient) {
  }

  obtenerResumenLocalizaciones(posicion: Posicion): Observable<any> {
    console.log('Obteniendo datos de puntos de carga relativos a la latitud ' + posicion.latitud + ', longitud ' + posicion.longitud + ' y radio ' + posicion.radio);
    return this.http.post(Constants.SERVER_URL + '/puntocarga', posicion);
  }

  obtenerDetalleLocalizacion(id: string): Observable<any> {
    console.log('Obteniendo detalle de punto de carga con id ' + id);
    return this.http.get(Constants.SERVER_URL + '/puntocarga/' + id);
  }
}