import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, tap, throwError } from 'rxjs';
import { EnvironmentConstants } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private httpClient: HttpClient
  ) { }

  userRegistration(registrationPayload): Observable<any> {
    console.log("URL " +EnvironmentConstants.userRegistration);
    return this.httpClient.post(EnvironmentConstants.userRegistration, registrationPayload)
  }

  userLogin(loginPayload): Observable<any> {
    console.log("URL " +EnvironmentConstants.userLogin);
    return this.httpClient.post(EnvironmentConstants.userLogin, loginPayload)
  }

  getNotification(userType): Observable<any> {
    
    return this.httpClient.get(EnvironmentConstants.getNotification, {params: new HttpParams().set('userRole', userType)    
    }).pipe(
      catchError((err) => {
        console.log('error caught in service')
        console.error(err);
        //Handle the error here
        return throwError(() => (new Error(err)));    //Rethrow it back to component
      })
    );
  }
}