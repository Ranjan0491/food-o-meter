import { Component, OnInit } from '@angular/core';
import { Address } from 'src/app/_model/address';
import { User } from 'src/app/_model/user';
import { AlertService, MessageType } from 'src/app/_service/alert.service';
import { UserServiceService } from 'src/app/_service/user-service.service';

@Component({
  selector: 'app-customer-details',
  templateUrl: './customer-details.component.html',
  styleUrls: ['./customer-details.component.css']
})
export class CustomerDetailsComponent implements OnInit {

  // need to remove hard coding
  customerId = "612c6c509441d78852dc3c4b";
  currentUser: User = null;
  newAddressFlag = false;
  newAddress: Address = null;

  constructor(private userService: UserServiceService,
    private alertService: AlertService) {
    this.fetchUserDetails();
  }

  ngOnInit(): void {
  }

  fetchUserDetails() {
    this.userService.getUserDetails(this.customerId).subscribe(response => {
      this.currentUser = response;
    });
  }

  updateUserDetails() {
    if (this.currentUser !== null && this.currentUser.firstName !== null && this.currentUser.lastName !== null && this.currentUser.phone !== null && this.currentUser.email !== null && this.currentUser.dob !== null) {
      console.log(this.currentUser.dob);
      this.userService.updateUserDetails(this.customerId, this.currentUser).subscribe(() => {
        this.fetchUserDetails();
        this.alertService.showMessage('User updated successfully', MessageType.SUCCESS);
      });
    } else {
      this.alertService.showMessage('User details are incomplete', MessageType.ERROR);
    }
  }

  addNewAddress() {
    if (this.newAddress.line1 !== null && this.newAddress.city !== null && this.newAddress.state !== null && this.newAddress.pinCode !== null) {
      this.userService.addNewAddress(this.customerId, this.newAddress).subscribe(() => {
        this.newAddressFlag = false;
        this.fetchUserDetails();
        this.alertService.showMessage('Address saved successfully', MessageType.SUCCESS);
      });
    } else {
      this.alertService.showMessage('Address details are incomplete', MessageType.ERROR);
    }
  }

  displayNewAddressFields() {
    this.newAddressFlag = true;
    this.newAddress = new Address(null, null, null, null, null, null, null);
  }

  cancelNewAddress() {
    this.newAddressFlag = false;
  }

}
