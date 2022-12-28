// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

import { Injectable } from "@angular/core";
import { Observable, shareReplay } from "rxjs";
import { HttpClient, HttpBackend } from '@angular/common/http';

export const environment = {
  production: false
};

export class Configuration {
  userLogin : string = '';
  envName : String = '';
}

export class EnvironmentConstants {
  public static userLogin : string;
  public static userRegistration: string;
  public static envName : String;
  public static getNotification : string;
}
@Injectable(
  {providedIn:'root'})
export class ConfigService {

  private readonly envJson = '../assets/Environment.json';

  private config$ : Observable<Configuration>|any;
  constructor(private handler : HttpBackend) {}
  public load() : Observable<Configuration> {

    const backed = new HttpClient(this.handler);
    console.log(this.config$);

    if(!this.config$) {
      this.config$ = backed.get<Configuration>(`${this.envJson}`).pipe(shareReplay(1));
      console.log(this.envJson);
      this.config$.subscribe({
        next : (envData: { [x: string]: any; }) => {
          if(envData && envData[`status`].toLowerCase() === 'success'){
            const data = envData['data'];
            EnvironmentConstants.envName = data.envName;
            EnvironmentConstants.userLogin = data.userLogin;
            EnvironmentConstants.userRegistration = data.userRegistration;
            EnvironmentConstants.getNotification = data.getNotification;
          }
          else {
            window.alert('Error')
          }
        }
    })
    }
    return this.config$
  }
}

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
