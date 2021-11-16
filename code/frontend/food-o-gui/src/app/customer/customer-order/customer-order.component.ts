import { Component, OnInit } from '@angular/core';
import { MatBottomSheet } from '@angular/material/bottom-sheet';
import { FoodOrder } from 'src/app/_model/food-order';
import { FoodOrderServiceService } from 'src/app/_service/food-order-service.service';
import { environment } from 'src/environments/environment';
import { ViewOrderDetailsComponent } from '../view-order-details/view-order-details.component';

@Component({
  selector: 'app-customer-order',
  templateUrl: './customer-order.component.html',
  styleUrls: ['./customer-order.component.css']
})
export class CustomerOrderComponent implements OnInit {
  foodOrders: FoodOrder[] = [];
  rows: number[] = [];

  loggedInCustomerId: string = null;

  constructor(private foodOrderService: FoodOrderServiceService,
    private orderDetailsBottomSheet: MatBottomSheet) {
    this.loggedInCustomerId = sessionStorage.getItem(environment.sessionUser.id);
  }

  ngOnInit(): void {
    this.fetchOrderList();
  }

  displayOrderDetails(index: number) {
    this.orderDetailsBottomSheet.open(ViewOrderDetailsComponent, { data: { "customerId": this.loggedInCustomerId, "orderId": this.foodOrders[index].id } });
  }

  fetchOrderList() {
    this.foodOrderService.getAllOrdersForCustomer(this.loggedInCustomerId).subscribe(data => {
      this.foodOrders = data;
      this.rows = Array.from(Array(Math.ceil(this.foodOrders.length / 2)).keys());
    });
  }

}
