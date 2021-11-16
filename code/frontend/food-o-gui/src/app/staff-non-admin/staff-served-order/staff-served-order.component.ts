import { Component, OnInit } from '@angular/core';
import { DetailedFoodOrder } from 'src/app/_model/detailed-food-order';
import { FoodOrderServiceService } from 'src/app/_service/food-order-service.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-staff-served-order',
  templateUrl: './staff-served-order.component.html',
  styleUrls: ['./staff-served-order.component.css']
})
export class StaffServedOrderComponent implements OnInit {

  foodOrders: DetailedFoodOrder[] = [];
  rows: number[] = [];

  loggedInStaffId: string = null;

  constructor(private foodOrderService: FoodOrderServiceService) {
    this.loggedInStaffId = sessionStorage.getItem(environment.sessionUser.id);
  }

  ngOnInit(): void {
    this.fetchOrderList();
  }

  fetchOrderList() {
    this.foodOrderService.getOrdersByStaffId(this.loggedInStaffId).subscribe(data => {
      data.forEach(v => {
        if (v !== null && v !== undefined) {
          this.foodOrders.push(v);
        }
      })
      console.log(this.foodOrders);
      this.rows = Array.from(Array(Math.ceil(this.foodOrders.length / 2)).keys());
    });
  }

}
