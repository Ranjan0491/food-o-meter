import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { UserServiceService } from '../_service/user-service.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  loggedInUserFirstName: string = null;
  loggedInUserLastName: string = null;
  loggedIn: boolean = false;

  constructor(private router: Router, private userService: UserServiceService) {
    userService.getLoginEvent().subscribe(data => {
      console.log("session items: " + sessionStorage.length);
      if (data === 'loggedIn') {
        if (sessionStorage.getItem(environment.sessionUser.id) !== null && sessionStorage.getItem(environment.sessionUser.id) !== undefined) {
          this.loggedInUserFirstName = sessionStorage.getItem(environment.sessionUser.firstName);
          this.loggedInUserLastName = sessionStorage.getItem(environment.sessionUser.lastName);
          this.loggedIn = true;
        }
      } else {
        this.loggedIn = false;
      }
    });
  }

  ngOnInit(): void {
  }

  routeToLoginPage() {
    this.router.navigate(["sign-in"]);
  }

  userLogout() {
    sessionStorage.clear();
    this.userService.sendLoginEvent('logout');
    this.router.navigate([""]);
  }

}
