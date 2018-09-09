import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';
import { Constants } from '../../constants/constants';
import { Saldo } from '../../model/saldo.model';

@Injectable()
export class SaldoService {

  constructor(private http: HttpClient) {
  }

  getSaldo(): Observable<any> {
    console.log('Consultando saldo');
    return this.http.get(Constants.SERVER_URL + '/saldo');
  }

  cargarSaldo(saldo: Saldo): Observable<any> {
    console.log('Cargando saldo');
    return this.http.put(Constants.SERVER_URL + '/saldo', saldo);
  }

}