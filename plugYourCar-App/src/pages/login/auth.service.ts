import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders } from '@angular/common/http';

// servicio para obtener el token durante el proceso de login de usuario
@Injectable()
export class AuthService {

  baseUrl: string = 'http://localhost:8080/oauth';
  clientId: string = '1364a508';
  clientSecret: string = '1364a508@App1';

  constructor(private http: HttpClient) {
  }

  autenticar(dniNie: string, password: string): Observable<any> {
    // añadir cabeceras HTTP
    const headers = new HttpHeaders({
      'Content-Type' : 'application/x-www-form-urlencoded',
      'Authorization': 'Basic ' + btoa(this.clientId + ':' + this.clientSecret),
      'Postman-Token': 'c626f71f-dc93-59e4-037e-36cd2e918eb3'
    });
    // añadir credenciales
    const credenciales = 'username=' + dniNie + '&password=' + password + '&grant_type=password';
    return this.http.post(this.baseUrl +'/token', credenciales, { headers : headers });
  }

}