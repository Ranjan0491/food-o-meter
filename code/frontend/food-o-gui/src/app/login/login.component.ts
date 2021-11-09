import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { Login } from '../_model/login';
import { AlertService, MessageType } from '../_service/alert.service';
import { UserServiceService } from '../_service/user-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  userLogin: Login = new Login(null, null);
  hide = true;

  constructor(private userService: UserServiceService, private alertService: AlertService, private router: Router) { }

  ngOnInit(): void {
  }

  login() {
    this.userService.userLogin(this.userLogin).subscribe(data => {
      sessionStorage.setItem(environment.sessionUser, JSON.stringify(data));
      if (data.userRole === environment.customerRole) {
        this.router.navigate(['customer']);
      } else if (data.userRole === environment.chefRole || data.userRole === environment.deliveryAgentRole) {
        this.router.navigate(['staff']);
      } else if (data.userRole === environment.adminRole) {
        this.router.navigate(['admin']);
      }
    }, error => {
      this.alertService.showErrorResponseMessage(error, MessageType.ERROR);
    });
  }

}
