import { Component, OnInit, ViewChild } from '@angular/core';
import { MatAccordion } from '@angular/material/expansion';
import { FoodOrder } from 'src/app/_model/food-order';
import { FoodOrderServiceService } from 'src/app/_service/food-order-service.service';

@Component({
  selector: 'app-customer-order',
  templateUrl: './customer-order.component.html',
  styleUrls: ['./customer-order.component.css']
})
export class CustomerOrderComponent implements OnInit {
  @ViewChild(MatAccordion) accordion: MatAccordion;
  foodOrders: FoodOrder[] = [];

  constructor(private foodOrderService: FoodOrderServiceService) { }

  ngOnInit(): void {
    let customerId = "612c6c509441d78852dc3c4b";
    this.foodOrderService.getAllOrdersForCustomer(customerId).subscribe(data => {
      this.foodOrders = data;
    });
  }

}
