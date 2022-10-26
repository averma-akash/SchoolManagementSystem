import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable, tap } from 'rxjs';
import { EnvironmentConstants } from 'src/environments/environment';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    //     'Access-Control-Allow-Origin': 'http://localhost:4200',
    //     'Access-Control-Allow-Headers': 'Origin,X-Requested-With,Content-Type,Accept',
    //     'Access-Control-Allow-Methods' : 'GET,POST,PUT,PATCH,DELETE,OPTIONS'
  })
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private httpClient: HttpClient
  ) { }

  userRegistration(registrationPayload): Observable<any> {
    console.log(EnvironmentConstants.userRegistration);
    console.log(registrationPayload);
    // console.log(httpOptions);
    return this.httpClient.post(EnvironmentConstants.userRegistration, registrationPayload)
    // .pipe(
    //     tap(data =>
    //     console.log('All: ' + JSON.stringify(data)))
    //   );;
  }
}

export class RegistrationSuccessfull {
  message: string = '';
  userId: string = '';

  constructor() { }
}
