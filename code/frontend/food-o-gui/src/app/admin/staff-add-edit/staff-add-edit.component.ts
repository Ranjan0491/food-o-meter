import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Address } from 'src/app/_model/address';
import { Staff } from 'src/app/_model/staff';
import { UserServiceService } from 'src/app/_service/user-service.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-staff-add-edit',
  templateUrl: './staff-add-edit.component.html',
  styleUrls: ['./staff-add-edit.component.css']
})
export class StaffAddEditComponent implements OnInit {

  currentStaff: Staff = null;
  currentStaffDob: Date = null;
  newItem = false;
  roles = environment.staffRoles;
  newAddressFlag = false;
  newAddress: Address = null;

  constructor(public dialogRef: MatDialogRef<StaffAddEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Staff,
    private userService: UserServiceService) {
    this.currentStaff = data;
    this.currentStaffDob = new Date(this.currentStaff.dob);
    if (this.currentStaff.id === null || this.currentStaff.id === undefined) {
      this.newItem = true;
    }
  }

  ngOnInit(): void {
  }

  saveStaff() {
    this.currentStaff.dob = this.currentStaffDob.toLocaleDateString("en-US");
    if (this.newItem) {
      console.log(this.currentStaff);
      this.userService.createNewUser(this.currentStaff.toUser()).subscribe(response => {
        console.log("Saved user: " + response);
        this.dialogRef.close();
      }, error => {
        console.error(error);
      });
    } else {
      this.userService.updateUserDetails(this.currentStaff.id, this.currentStaff.toUser()).subscribe(response => {
        console.log("Updated user: " + response);
        this.dialogRef.close();
      }, error => {
        console.error(error);
      });
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
      if (this.currentStaff.addresses === null || this.currentStaff.addresses === undefined) {
        this.currentStaff.addresses = [];
      }
      this.currentStaff.addresses.push(this.newAddress);
      this.cancelNewAddress();
    }
  }

}
