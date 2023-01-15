import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HTTP_INTERCEPTORS,
  HttpErrorResponse
} from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {

  constructor() { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    req = req.clone({
      withCredentials: true,
    });

    return next.handle(req).pipe(catchError((err : HttpErrorResponse)=>{
      console.log('error is intercept')
        console.error(err);
        return throwError(err.message);
    }));
  }

  // private handleServerSideError(error: HttpErrorResponse) {
  //   switch (error.status) {

  //     case 400: //  means the request could not be understood by the server.
  //       //this._snackBar.;
  //       break;
  //     case 401: // means lacks valid authentication credentials for the target resource. 
  //       this._snackBar.open("Unauthorized, please try again later.");
  //       break;
  //     case 403: //  means you are not allowed access to the target resource.
  //       this._snackBar.open("Forbidden access is denied");
  //       break;
  //     case 500: // means there's an issue or temporary glitch with the application's programming
  //       this._snackBar.open("Internal server error, please try again later.");
  //       break;
  //   }
  //}
}

// intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
//   let authReq = req;
//   const token = this.token.getToken();
//   if (token != null) {
//     authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token) });
//   }
//   return next.handle(authReq);
// }


export const authInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: AuthenticationInterceptor, multi: true }
];
