import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OverlayModule } from '@angular/cdk/overlay';
import { FormsModule } from '@angular/forms';
import { MatBottomSheetModule } from '@angular/material/bottom-sheet';
import { MatButtonModule } from '@angular/material/button';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatCardModule } from '@angular/material/card';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatDialogModule } from '@angular/material/dialog';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTableModule } from '@angular/material/table';
import { MatTabsModule } from '@angular/material/tabs';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTooltipModule } from '@angular/material/tooltip';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { ItemManagementComponent } from './item-management/item-management.component';
import { ItemAddEditComponent } from './item-add-edit/item-add-edit.component';
import { MatSelectModule } from '@angular/material/select';
import { StaffManagementComponent } from './staff-management/staff-management.component';
import { StaffAddEditComponent } from './staff-add-edit/staff-add-edit.component';
import { CustomerModule } from '../customer/customer.module';


@NgModule({
  declarations: [
    AdminDashboardComponent,
    ItemManagementComponent,
    ItemAddEditComponent,
    StaffManagementComponent,
    StaffAddEditComponent
  ],
  imports: [
    CommonModule,
    MatSidenavModule,
    MatListModule,
    OverlayModule,
    MatIconModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCardModule,
    MatToolbarModule,
    MatGridListModule,
    MatExpansionModule,
    MatFormFieldModule,
    MatBottomSheetModule,
    MatTableModule,
    MatTabsModule,
    MatTooltipModule,
    MatSnackBarModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    FormsModule,
    MatDialogModule,
    MatSelectModule,
    CustomerModule
  ],
  providers: [
    ItemAddEditComponent,
    StaffAddEditComponent
  ]
})
export class AdminModule { }
