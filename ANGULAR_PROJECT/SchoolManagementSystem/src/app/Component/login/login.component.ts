import { state } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { TokenStorageService } from 'src/app/_service/JWTTokenStorage/token-storage.service';
import { UserService } from 'src/app/_service/user.service';
import { TeacherDashboardComponent } from '../dashboard/teacher/teacher-dashboard/teacher-dashboard.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm : any = {
    username: null,
    password: null
  }

  isLoggedIn = false;
  roles = '';

  constructor(
    private userService : UserService,
    private router : Router,
    private tokenStorage : TokenStorageService
  ) { }

  ngOnInit(): void {

    if (this.tokenStorage.isLoggedIn()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
    }
  }

  onSubmit() {
    console.log(this.loginForm);
    const response: Observable<any> = this.userService.userLogin(this.loginForm);
    response.subscribe(
      {
        next: data => {
          
          this.tokenStorage.saveUser(data);

          if(data['roles'] === 'TEACHER') {
          this.router.navigate(['teacher-dashboard'], { state: {rowDetail : data}})
          }
          if(data['roles'] === 'STUDENT') {
            this.router.navigate(['student-dashboard'], { state: {rowDetail : data}})
            }
          console.log("data" + data);
          console.log(data['email']);
          console.log(data['id']);
          console.log(data['roles']);
          console.log(data['username']);
        }
      }
    )
    }
}
