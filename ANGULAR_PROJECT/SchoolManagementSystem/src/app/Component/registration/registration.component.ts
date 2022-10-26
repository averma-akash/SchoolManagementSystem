import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Observable } from 'rxjs';
import { UserService } from 'src/app/_service/user.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  @Output() modalFunction: EventEmitter<any> = new EventEmitter();

  regForm: any = {
    name: null,
    email: null,
    password: null,
    registerAs: null
  };
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(
    private userService: UserService
  ) { }

  ngOnInit(): void { }

  onSubmit() {
    console.log(this.regForm);
    let request = {
      userName: this.regForm.name,
      email: this.regForm.email,
      password: this.regForm.password,
      role: this.regForm.registerAs
    }
    const response: Observable<any> = this.userService.userRegistration(request);
    response.subscribe({
      next: data => {
        console.log("data" +data);
        if (data && data['status'].toLowerCase() === 'success') {
          this.isSuccessful = true;
          this.isSignUpFailed = false;
          console.log("data inside if:" + data);
          let toastObject = {
            title: "Registration Successful for (" + request.userName + ")",
            desc: "<strong>your userId : " + data['userId'] + "</strong>",
          }
          this.modalFunction.emit(toastObject);
        }
      },
      error: err => {
        console.log("data :" + err.error.message);
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
        // let toastObject = {
        //   title: "Registration Successful for (" + request.userName + ")",
        //   desc: "<strong>your userId : " + this.errorMessage + "</strong>",
        // }
        // this.modalFunction.emit(toastObject);
      }
    });

  }

}
