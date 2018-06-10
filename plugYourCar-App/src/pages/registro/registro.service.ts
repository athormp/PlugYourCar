import { Usuario } from './../usuario/usuario.model';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class RegistroService {

  baseUrl: 'http://localhost:8080/usuarios';

  constructor(private http: HttpClient) {
  }

  registrar(usuario: Usuario): Observable<any> {
    console.log('Registrando Usuario: ' + usuario.nombre + " " + usuario.apellidos);
    return this.http.post('http://localhost:8080/usuarios/registro', usuario);
  }

}