import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { UserService } from 'src/app/_service/user.service';

@Component({
  selector: 'app-student-dashboard',
  templateUrl: './student-dashboard.component.html',
  styleUrls: ['./student-dashboard.component.css']
})
export class StudentDashboardComponent implements OnInit {

  email = '';
  username = '';
  userRole = '';
  userId = '';

  notificationData : any;

  constructor(
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.getUserDetail();
    this.getNotification();
  }

  getNotification() {
    const response: Observable<any> = this.userService.getNotification(window.history.state.rowDetail.roles);
    response.subscribe({
      next: data => {
        if (data && data['status'].toLowerCase() === 'success') {
          this.notificationData = data['data'];
        }
      },
      error: err => {
        console.log("Error");
      }
    });
  }

  getUserDetail() {
    this.username = window.history.state.rowDetail.username;
    this.userRole = window.history.state.rowDetail.roles;
    this.email = window.history.state.rowDetail.email;
    this.userId = window.history.state.rowDetail.id;
  }

}
