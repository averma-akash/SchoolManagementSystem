import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StudentDashboardComponent } from './Component/dashboard/student/student-dashboard/student-dashboard.component';
import { TeacherDashboardComponent } from './Component/dashboard/teacher/teacher-dashboard/teacher-dashboard.component';
import { ErrorComponent } from './Component/error/error.component';
import { LoginComponent } from './Component/login/login.component';
import { RegistrationComponent } from './Component/registration/registration.component';
import { RouterService } from './_service/router.service';

const routes: Routes = [
  {path : '', component : LoginComponent},
  {path : 'login', component : LoginComponent},
  {path : 'signup', component : RegistrationComponent},
  {path : 'teacher-dashboard', component : TeacherDashboardComponent, canActivate : [RouterService]},
  {path : 'student-dashboard', component : StudentDashboardComponent, canActivate : [RouterService]},
  {path : '**', component : ErrorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
