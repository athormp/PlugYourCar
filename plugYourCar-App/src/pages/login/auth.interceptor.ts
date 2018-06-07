import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpSentEvent, HttpHeaderResponse, HttpProgressEvent, 
HttpResponse, HttpUserEvent, HttpErrorResponse } from '@angular/common/http';
import { NavController } from 'ionic-angular';
import { Observable } from 'rxjs/Observable';
import { TokenStorage } from './token.storage';
import 'rxjs/add/operator/do';

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class Interceptor implements HttpInterceptor {

  constructor(public navCtrl: NavController, private token: TokenStorage) { }

  intercept(req: HttpRequest<any>, next: HttpHandler):
    Observable<HttpUserEvent<any>> {
        let authReq = req;
        if (this.token.entregarToken() != null) {
            authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + this.token.entregarToken())});
        }
        return next.handle(authReq).do(
            (err: any) => {
                if (err instanceof HttpErrorResponse) {
                    if (err.status === 401) {
                        this.navCtrl.push('login');
                    }
                }
            }
        );
    }
}