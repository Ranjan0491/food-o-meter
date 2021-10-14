import { Component, Inject, OnInit } from '@angular/core';
import { MatBottomSheetRef, MAT_BOTTOM_SHEET_DATA } from '@angular/material/bottom-sheet';
import { Address } from 'src/app/_model/address';
import { UserServiceService } from 'src/app/_service/user-service.service';

@Component({
  selector: 'app-select-order-address',
  templateUrl: './select-order-address.component.html',
  styleUrls: ['./select-order-address.component.css']
})
export class SelectOrderAddressComponent implements OnInit {

  customerAddress: Address[] = [];
  selectedCustomerAddress: Address = null;

  constructor(@Inject(MAT_BOTTOM_SHEET_DATA) private data: String,
  private bottomsheet: MatBottomSheetRef<SelectOrderAddressComponent>,
  private userService: UserServiceService) {
    userService.getAllAddressesForCustomer(data).subscribe(response => {
      this.customerAddress = response;
    });
  }

  ngOnInit(): void {
  }

  submitSelectedAddress(address: Address) {
    this.selectedCustomerAddress = address;
    this.bottomsheet.dismiss(address);
  }

}
