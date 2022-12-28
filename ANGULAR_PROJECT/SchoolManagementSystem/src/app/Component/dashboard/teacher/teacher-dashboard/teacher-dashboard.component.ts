import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-teacher-dashboard',
  templateUrl: './teacher-dashboard.component.html',
  styleUrls: ['./teacher-dashboard.component.css']
})
export class TeacherDashboardComponent implements OnInit {

  email = '';
  username ='';
  userRole='';
  userId='';

  constructor(private actRoute : ActivatedRoute) { }

  ngOnInit(): void {
      this.getUserDetail();
      this.getDasboard();   
  }

  getUserDetail() {
    this.username = window.history.state.rowDetail.username;
    this.userRole = window.history.state.rowDetail.roles;
    this.email = window.history.state.rowDetail.email;
    this.userId = window.history.state.rowDetail.id;
  }
  
  getDasboard() {
    throw new Error('Function not implemented.');
  }

}