import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';
import { Constants } from '../../constants/constants';

// servicio para hacer las peticiones REST relacionadas con las cargas y reservas
@Injectable()
export class GestionCargaService {
  
  constructor(private http: HttpClient) {
  }

  iniciarCarga(idConector: string): Observable<any> {
    console.log('Iniciando carga para el conector: ' + idConector);
    return this.http.post(Constants.SERVER_URL + '/carga/' + idConector, null);
  }
}