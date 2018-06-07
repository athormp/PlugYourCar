import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class AuthService {

  baseUrl: 'http://localhost:8080/usuarios';

  constructor(private http: HttpClient) {
  }

  autenticar(dniNie: string, password: string): Observable<any> {
    const credenciales = {dniNie: dniNie, password: password};
    console.log('attempAuth ::');
    return this.http.post('http://localhost:8080/token/generate-token', credenciales);
  }

}