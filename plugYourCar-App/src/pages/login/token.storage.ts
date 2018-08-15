import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt'

const TOKEN_KEY = 'AuthToken';
const jwtHelperService = new JwtHelperService();

@Injectable()
export class TokenStorage {

  constructor() {}

  logOut() {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.clear();
  }

  // guardar el access_token devuelto por el servidor Oauth2
  public guardarToken(token: string) {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
    console.log(window.sessionStorage.getItem(TOKEN_KEY));
  }

  // recuperar token del sessionStorage
  public entregarToken(): string {
    return window.sessionStorage.getItem(TOKEN_KEY);
  }

  public loggedIn(): boolean {
    // obtener el token
    const token = this.entregarToken();
    // devuelve un booleano indicando si el token ha expirado o no
    return jwtHelperService.isTokenExpired(token);
  }
}