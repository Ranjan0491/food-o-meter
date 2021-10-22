import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  currentPassword: string = null;
  currentUsername: string = null;
  hide = true;

  constructor() { }

  ngOnInit(): void {
  }

  login() {

  }

}
