import { Component, OnInit } from '@angular/core';
import { DetailedFoodOrder } from 'src/app/_model/detailed-food-order';
import { FoodOrderServiceService } from 'src/app/_service/food-order-service.service';

@Component({
  selector: 'app-staff-served-order',
  templateUrl: './staff-served-order.component.html',
  styleUrls: ['./staff-served-order.component.css']
})
export class StaffServedOrderComponent implements OnInit {

  foodOrders: DetailedFoodOrder[] = [];
  rows: number[] = [];

  // need to remove hard coding
  staffId = "61554b7427e44790892e43ef";

  constructor(private foodOrderService: FoodOrderServiceService) { }

  ngOnInit(): void {
    this.fetchOrderList();
  }

  fetchOrderList() {
    this.foodOrderService.getOrdersByStaffId(this.staffId).subscribe(data => {
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
