import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Observable } from 'rxjs';
import { ToastService } from 'src/app/_service/toast.service';
import { UserService } from 'src/app/_service/user.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  //@Output() modalFunction = new EventEmitter();

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
    private userService: UserService,
    private toastService : ToastService
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
            desc: "<strong>your userId : " + data['data']['userId'] + "</strong>",
          };
          this.showToastMessge(toastObject);
        } else if(data && data['status'].toLowerCase() === 'fail') {
          this.isSignUpFailed = true;
          this.errorMessage = data['data']['message'];
          let toastObject = {
            title: "Unsuccesful !",
            desc: "<strong>"+ data['data']['message'] + "</strong>",
          };
          this.showToastMessge(toastObject);
        }
      },
      error: err => {
        
        this.isSignUpFailed = true;
      }
    });

  }
  showToastMessge(toastObject) {
    
    let toastObbject = {
      title: toastObject?.title,
      desc: toastObject?.desc
    };
    this.toastService.show(toastObbject);

  }

}
