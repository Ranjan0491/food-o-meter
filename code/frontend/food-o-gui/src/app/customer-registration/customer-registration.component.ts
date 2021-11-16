import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Address } from '../_model/address';
import { User } from '../_model/user';
import { AlertService, MessageType } from '../_service/alert.service';
import { UserServiceService } from '../_service/user-service.service';

@Component({
  selector: 'app-customer-registration',
  templateUrl: './customer-registration.component.html',
  styleUrls: ['./customer-registration.component.css']
})
export class CustomerRegistrationComponent implements OnInit {

  currentUser: User = null;
  currentUserDob: Date = null;
  newAddressFlag = false;
  newAddress: Address = null;
  currentUserConfirmPassword: string = null;
  hide = true;

  constructor(private userService: UserServiceService, private alertService: AlertService) {
    this.currentUser = new User();
  }

  ngOnInit(): void {
  }

  saveUser() {
    console.log(this.currentUser);
    console.log(this.currentUserDob);
    console.log(this.currentUserConfirmPassword);

    this.currentUser.dob = this.currentUserDob.toLocaleDateString("en-US");
    if (this.currentUser.firstName !== null && this.currentUser.lastName !== null && this.currentUser.email !== null && this.currentUser.phone !== null && this.currentUser.dob !== null) {
      if (this.currentUser.addresses !== null && this.currentUser.addresses !== undefined && this.currentUser.addresses.length > 0) {
        if (this.currentUser.password !== null && this.currentUser.password !== undefined && this.currentUserConfirmPassword !== null && this.currentUserConfirmPassword !== undefined && this.currentUser.password === this.currentUserConfirmPassword) {
          this.currentUser.userRole = environment.customerRole;
          this.userService.createNewUser(this.currentUser).subscribe(response => {
            this.reset();
            this.alertService.showMessage('User has been registered successfully', MessageType.SUCCESS);
          }, error => {
            this.alertService.showErrorResponseMessage(error.error.message, MessageType.ERROR);
          });
        } else {
          this.alertService.showMessage('Passwords do not match', MessageType.ERROR);
        }
      } else {
        this.alertService.showMessage('Please provide at least one address', MessageType.ERROR);
      }
    } else {
      this.alertService.showMessage('Please fill up all fields', MessageType.ERROR);
    }
  }

  displayNewAddressFields() {
    this.newAddressFlag = true;
    this.newAddress = new Address(null, null, null, null, null, null, null);
  }

  cancelNewAddress() {
    this.newAddressFlag = false;
  }

  addNewAddress() {
    if (this.newAddress.line1 !== null && this.newAddress.city !== null && this.newAddress.state !== null && this.newAddress.pinCode !== null) {
      if (this.currentUser.addresses === null || this.currentUser.addresses === undefined) {
        this.currentUser.addresses = [];
      }
      this.currentUser.addresses.push(this.newAddress);
      this.cancelNewAddress();
    }
  }

  reset() {
    this.currentUser = new User();
    this.currentUserDob = null;
    this.currentUserConfirmPassword = null;
    this.cancelNewAddress();
    this.hide = true;
  }

}
