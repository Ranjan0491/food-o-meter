import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Staff } from 'src/app/_model/staff';
import { AlertService, MessageType } from 'src/app/_service/alert.service';
import { ConfirmationService } from 'src/app/_service/confirmation.service';
import { UserServiceService } from 'src/app/_service/user-service.service';
import { environment } from 'src/environments/environment';
import { StaffAddEditComponent } from '../staff-add-edit/staff-add-edit.component';

@Component({
  selector: 'app-staff-management',
  templateUrl: './staff-management.component.html',
  styleUrls: ['./staff-management.component.css']
})
export class StaffManagementComponent implements OnInit {

  staffs: Staff[] = [];
  staffColumns: string[] = ['name', 'role', 'phone', 'email', 'dob', 'action'];
  staffDataSource: MatTableDataSource<Staff>;
  loggedInStaffId: string = null;

  constructor(private userService: UserServiceService,
    private confirmationService: ConfirmationService,
    private alertService: AlertService,
    public addEditStaffDialog: MatDialog) {
    this.populateStaffDetails();
    this.loggedInStaffId = sessionStorage.getItem(environment.sessionUser.id);
  }

  ngOnInit(): void {
  }

  populateStaffDetails() {
    this.userService.getAllStaffByRoles('DELIVERY_AGENT', 'CHEF', 'ADMIN').subscribe(response => {
      this.staffs = response;
      this.staffDataSource = new MatTableDataSource(this.staffs);
    });
  }

  editStaff(staff: Staff) {
    const dialogRef = this.addEditStaffDialog.open(StaffAddEditComponent, { data: staff, maxWidth: '60%', maxHeight: '80%' });
    dialogRef.afterClosed().subscribe(result => {
      this.populateStaffDetails();
    });
  }

  deleteStaff(staff: Staff) {
    this.confirmationService.showMessage('Do you want to remove ' + staff.firstName + ', ' + staff.lastName + ' (' + staff.userRole + ')')
      .afterClosed().subscribe(result => {
        if (result === 'OK') {
          this.userService.deleteStaff(staff.id, this.loggedInStaffId).subscribe(() => {
            this.populateStaffDetails();
            this.alertService.showMessage('Staff status has been changed to INCATIVE', MessageType.SUCCESS);
          }, error => {
            this.alertService.showErrorResponseMessage(error.error.message, MessageType.ERROR);
          });
        }
      });
  }

  addNewStaff() {
    const dialogRef = this.addEditStaffDialog.open(StaffAddEditComponent, { data: new Staff(null, null, null, null, null, null, null, null), maxWidth: '60%', maxHeight: '80%' });
    dialogRef.afterClosed().subscribe(result => {
      this.populateStaffDetails();
    });
  }

}
