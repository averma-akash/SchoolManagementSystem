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
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {

  handlerResponse = ''
  constructor(
    public snackBar: MatSnackBar
  ) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    req = req.clone({
      withCredentials: true,//This is required when you have to send jwt authentication
      //which is coming in response header
    });

    return next.handle(req).pipe(catchError((err: HttpErrorResponse) => {
      console.log('error is intercept')
      if (!navigator.onLine) {
        this.snackBar.open("No Internet Connection!", 'X', {
          panelClass: ['error']
          , horizontalPosition: 'center', verticalPosition: 'top'
        });
      }
      this.handleServerSideError(err);
      return throwError(err);;
    }));
  }

  handleServerSideError(error: HttpErrorResponse) {
    switch (error.status) {

      case 400: //  means the request could not be understood by the server.
        this.handlerResponse = "Bad Request, please try again later .";
        this.snackBar.open("Bad Request, please try again later .", 'X', { panelClass: ['error'] });
        break;
      case 401: // means lacks valid authentication credentials for the target resource. 
        this.handlerResponse = "Unauthorized, please try again later.";
        this.snackBar.open("Unauthorized, please try again later.", 'X', { panelClass: ['error'] });
        break;
      case 403: //  means you are not allowed access to the target resource.
        this.handlerResponse = "Forbidden access is denied";
        this.snackBar.open("Forbidden access is denied", 'X', { panelClass: ['error'] });
        break;
      case 500: // means there's an issue or temporary glitch with the application's programming
        this.handlerResponse = "Internal server error, please try again later.";
        this.snackBar.open("Internal server error, please try again later.", 'X', { panelClass: ['error'] });
        break;
        case 0: // means server is down
        this.handlerResponse = "Server Down !, please try again later.";
        this.snackBar.open("Server Down !, please try again later.", 'X', { panelClass: ['error'] });
        break;
    }
  }
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
