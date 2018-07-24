import { Usuario } from './../../model/usuario.model';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class RegistroService {

  baseUrl:string = 'http://192.168.0.159:8080/usuarios';

  constructor(private http: HttpClient) {
  }

  registrar(usuario: Usuario): Observable<any> {
    console.log('Registrando Usuario: ' + usuario.nombre + " " + usuario.apellidos);
    return this.http.post(this.baseUrl + '/registro', usuario);
  }

}