import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';
import { Constants } from '../../constants/constants';

// servicio para hacer las peticiones REST relacionadas con las cargas y reservas
@Injectable()
export class GestionCargaService {
  
  constructor(private http: HttpClient) {
  }

  obtenerCargas(): Observable<any> {
    console.log('Recuperando cargas para un usuario');
    return this.http.get(Constants.SERVER_URL + '/carga');
  }

  obtenerDetalleCarga(idCarga: string): Observable<any> {
    console.log('Recuperando carga para un usuario con id: ' + idCarga);
    return this.http.get(Constants.SERVER_URL + '/carga/' + idCarga);
  }

  iniciarCarga(idConector: string, cargaConReserva): Observable<any> {
    console.log('Iniciando carga para el conector: ' + idConector);
    return this.http.post(Constants.SERVER_URL + '/carga/' + idConector + "/" + cargaConReserva, null);
  }

  finalizarCarga(idCarga: string): Observable<any> {
    console.log('Finalizando carga para el conector: ' + idCarga);
    return this.http.put(Constants.SERVER_URL + '/carga/' + idCarga, null);
  }
}