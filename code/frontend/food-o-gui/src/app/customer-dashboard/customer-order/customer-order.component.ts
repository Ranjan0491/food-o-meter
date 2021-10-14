import { Component, OnInit } from '@angular/core';
import { MatBottomSheet } from '@angular/material/bottom-sheet';
import { FoodOrder } from 'src/app/_model/food-order';
import { FoodOrderServiceService } from 'src/app/_service/food-order-service.service';
import { ViewOrderDetailsComponent } from '../view-order-details/view-order-details.component';

@Component({
  selector: 'app-customer-order',
  templateUrl: './customer-order.component.html',
  styleUrls: ['./customer-order.component.css']
})
export class CustomerOrderComponent implements OnInit {
  foodOrders: FoodOrder[] = [];
  rows: number[] = [];

  // need to remove hard coding
  customerId = "612c6c509441d78852dc3c4b";

  constructor(private foodOrderService: FoodOrderServiceService,
    private orderDetailsBottomSheet: MatBottomSheet) { }

  ngOnInit(): void {
    this.foodOrderService.getAllOrdersForCustomer(this.customerId).subscribe(data => {
      this.foodOrders = data;
      this.rows = Array.from(Array(Math.floor(this.foodOrders.length / 2)).keys());
    });
  }

  displayOrderDetails(index: number) {
    this.orderDetailsBottomSheet.open(ViewOrderDetailsComponent, { data: { "customerId": this.customerId, "orderId": this.foodOrders[index].id } });
  }

}
