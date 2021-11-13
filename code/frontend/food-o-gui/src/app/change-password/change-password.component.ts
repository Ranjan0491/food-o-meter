import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { environment } from 'src/environments/environment';
import { ExceptionResponse } from '../_model/exception-response';
import { UserServiceService } from '../_service/user-service.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  currentPassword: string = null;
  newPassword: string = null;
  newPassword2: string = null;
  hideCurrentPassword = true;
  hideNewPassword = true;
  hideNewPassword2 = true;
  errorText = null;

  loggedInCustomerId: string = null;

  constructor(public dialogRef: MatDialogRef<ChangePasswordComponent>,
    private userService: UserServiceService) {
    this.loggedInCustomerId = sessionStorage.getItem(environment.sessionUser.id);
  }

  ngOnInit(): void {
  }

  updatePassword() {
    if (this.currentPassword !== null && this.newPassword !== null && this.newPassword2 !== null) {
      if (this.newPassword === this.newPassword2) {
        this.userService.updateUserPassword(this.loggedInCustomerId, this.currentPassword, this.newPassword).subscribe(response => {
          this.dialogRef.close();
        }, (error: ExceptionResponse) => {
          this.errorText = error.message;
        });
      } else {
        this.errorText = "Passwords do not match";
      }
    } else {
      this.errorText = "All fields are required";
    }
  }

}
