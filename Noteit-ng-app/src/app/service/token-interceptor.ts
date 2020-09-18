import {HTTP_INTERCEPTORS, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  private token: string;
  private TOKEN_HEADER_KEY: string = 'Authorization';

  constructor() {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authReq = request;
    this.token = sessionStorage.getItem("token");
    if (this.token != null) {
      authReq = request.clone({headers: request.headers.set(this.TOKEN_HEADER_KEY, 'Bearer ' + this.token)});
    }
    return next.handle(authReq);
  }

  private addToken(request: HttpRequest<any>, token: string) {
    return request.clone({
      setHeaders: {
        'Authorization': `Bearer ${token}`
      }
    });
  }
}

export const authInterceptorProviders = [
  {provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true}
];
