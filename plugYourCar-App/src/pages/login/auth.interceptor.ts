import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpUserEvent, HttpErrorResponse, HttpEvent, HttpResponse } from '@angular/common/http';
import { Events } from 'ionic-angular';
import { Observable } from 'rxjs/Observable';
import { TokenStorage } from './token.storage';
import 'rxjs/add/operator/do';

const TOKEN_HEADER_KEY = 'Authorization';

// Clase para interceptar las peticiones REST, todas las peticiones pasan por ella y en caso de que para el recurso solicitado se requiera autorización 
// se deberá pasar el token de acceso, de lo contrario se obtendrá un error 401 de usuario no autorizado

@Injectable()
export class Interceptor implements HttpInterceptor {

  constructor(private token: TokenStorage, private events: Events) { }

  intercept(req: HttpRequest<any>, next: HttpHandler):
    Observable<HttpEvent<any>> {
        let authReq = req;
        if (this.token.entregarToken() != null) {
            authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + this.token.entregarToken())});
        }
        return next.handle(authReq).do((event: HttpEvent<any>) => {
            if (event instanceof HttpResponse) {
                // éxito
              }
            }, (error: any) => {   
                if (error instanceof HttpErrorResponse) {
                    if (error.status === 401) {
                        console.log(error);
                        this.events.publish('http:forbidden', "Usuario no autorizado");
                    }
                }
            }
        );
    }
}