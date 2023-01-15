import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegistrationComponent } from './Component/registration/registration.component';
import { LoginComponent } from './Component/login/login.component';
import { ErrorComponent } from './Component/error/error.component';
import { FormsModule } from '@angular/forms';
import { NgbModalModule, NgbToastModule } from '@ng-bootstrap/ng-bootstrap';
import { ToastComponent } from './Component/toast/toast.component';
import { EnvironmentConstants ,ConfigService } from '../environments/environment';
import { TeacherDashboardComponent } from './Component/dashboard/teacher/teacher-dashboard/teacher-dashboard.component';
import { StudentDashboardComponent } from './Component/dashboard/student/student-dashboard/student-dashboard.component';
import { authInterceptorProviders } from './_helpers/authentication.interceptor';
import { SampleExampleComponent } from './Component/SampleExamples/sample-example/sample-example.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    LoginComponent,
    ErrorComponent,
    ToastComponent,
    TeacherDashboardComponent,
    StudentDashboardComponent,
    SampleExampleComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModalModule,
    NgbToastModule,
    FormsModule,
    BrowserAnimationsModule,
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
    },
    authInterceptorProviders
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
