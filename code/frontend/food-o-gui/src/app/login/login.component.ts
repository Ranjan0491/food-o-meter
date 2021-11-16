import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { Login } from '../_model/login';
import { UserServiceService } from '../_service/user-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  userLogin: Login = new Login(null, null);
  hide = true;
  errorMessage: string = null;

  constructor(private userService: UserServiceService, private router: Router) { }

  ngOnInit(): void {
  }

  login() {
    this.errorMessage = null;
    this.userService.userLogin(this.userLogin).subscribe(data => {
      sessionStorage.setItem(environment.sessionUser.id, data.id);
      sessionStorage.setItem(environment.sessionUser.firstName, data.firstName);
      sessionStorage.setItem(environment.sessionUser.lastName, data.lastName);
      sessionStorage.setItem(environment.sessionUser.role, data.userRole);

      this.userService.sendLoginEvent(environment.loginEvent.loggedIn);

      if (data.userRole === environment.customerRole) {
        this.router.navigate(['customer']);
      } else if (data.userRole === environment.chefRole || data.userRole === environment.deliveryAgentRole) {
        this.router.navigate(['staff']);
      } else if (data.userRole === environment.adminRole) {
        this.router.navigate(['admin']);
      } else {
        this.errorMessage = 'User does not have required role';
      }

    }, error => {
      this.errorMessage = error.error.message;
    });
  }

}
