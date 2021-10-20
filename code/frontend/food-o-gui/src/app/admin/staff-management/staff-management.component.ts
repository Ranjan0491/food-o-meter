import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Staff } from 'src/app/_model/staff';
import { AlertService } from 'src/app/_service/alert.service';
import { UserServiceService } from 'src/app/_service/user-service.service';
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

  constructor(private userService: UserServiceService,
    private alertServicce: AlertService,
    public addEditStaffDialog: MatDialog) {
    this.populateStaffDetails();
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

  }

  addNewStaff() {
    const dialogRef = this.addEditStaffDialog.open(StaffAddEditComponent, { data: new Staff(null, null, null, null, null, null, null, null), maxWidth: '60%', maxHeight: '80%' });
    dialogRef.afterClosed().subscribe(result => {
      this.populateStaffDetails();
    });
  }

}
