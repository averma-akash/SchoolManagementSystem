import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegistrationComponent } from './Component/registration/registration.component';
import { LoginComponent } from './Component/login/login.component';
import { ErrorComponent } from './Component/error/error.component';
import { ConfigService } from 'src/environments/environment';

@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    LoginComponent,
    ErrorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
  ],
  providers: [
    {
      provide : APP_INITIALIZER,
      useFactory : (envConfigService : ConfigService) => () =>envConfigService.load().toPromise(),
      deps : [ConfigService],
      multi : true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
