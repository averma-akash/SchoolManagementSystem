import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegistrationComponent } from './Component/registration/registration.component';
import { LoginComponent } from './Component/login/login.component';
import { ErrorComponent } from './Component/error/error.component';
import { ConfigService } from 'src/environments/environment';
import { FormsModule } from '@angular/forms';
import { NgbModalModule, NgbToastModule } from '@ng-bootstrap/ng-bootstrap';
import { ToastComponent } from './Component/toast/toast.component';
import { ToastrModule } from 'ngx-toastr';

@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    LoginComponent,
    ErrorComponent,
    ToastComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModalModule,
    NgbToastModule,
    FormsModule
  ],
  exports: [
    NgbToastModule
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
