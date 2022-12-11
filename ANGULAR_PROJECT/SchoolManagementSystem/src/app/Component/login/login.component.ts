import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { UserService } from 'src/app/_service/user.service';

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

  constructor(
    private userService : UserService
  ) { }

  ngOnInit(): void {
  }

  onSubmit() {
    console.log(this.loginForm);
    const response: Observable<any> = this.userService.userLogin(this.loginForm);
    response.subscribe(
      {
        next: data => {
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
