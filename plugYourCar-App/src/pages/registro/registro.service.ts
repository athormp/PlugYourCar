import { Usuario } from '../../model/usuario.model';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';
import { Constants } from '../../constants/constants';

@Injectable()
export class RegistroService {

  constructor(private http: HttpClient) {
  }

  registrar(usuario: Usuario): Observable<any> {
    console.log('Registrando Usuario: ' + usuario.nombre + " " + usuario.apellidos);
    return this.http.post(Constants.SERVER_URL + '/registro', usuario);
  }

}