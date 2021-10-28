import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomerDashboardComponent } from './customer-dashboard/customer-dashboard.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { OverlayModule } from '@angular/cdk/overlay';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatCardModule } from '@angular/material/card';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatExpansionModule } from '@angular/material/expansion';
import { CustomerOrderComponent } from './customer-order/customer-order.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatBottomSheetModule } from '@angular/material/bottom-sheet';
import { ViewOrderDetailsComponent } from './view-order-details/view-order-details.component';
import { MatTableModule } from '@angular/material/table';
import { AddressTransformPipe } from '../_pipe/address-transform-pipe';
import { CustomerPlaceOrderComponent } from './customer-place-order/customer-place-order.component';
import { CustomerDetailsComponent } from './customer-details/customer-details.component';
import { MatTabsModule } from '@angular/material/tabs';
import { MatTooltipModule } from '@angular/material/tooltip';
import { SelectOrderAddressComponent } from './select-order-address/select-order-address.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { FormsModule } from '@angular/forms';
import { MatNativeDateModule, MAT_DATE_LOCALE } from '@angular/material/core';
import { MatDialogModule } from '@angular/material/dialog';

@NgModule({
  declarations: [
    CustomerDashboardComponent,
    CustomerOrderComponent,
    ViewOrderDetailsComponent,
    AddressTransformPipe,
    CustomerPlaceOrderComponent,
    CustomerDetailsComponent,
    SelectOrderAddressComponent
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
    MatDialogModule
  ],
  providers: [
    ViewOrderDetailsComponent,
    { provide: MAT_DATE_LOCALE, useValue: { useUtc: false } }
  ],
  exports: [
    CustomerDetailsComponent
  ]
})
export class CustomerModule { }
