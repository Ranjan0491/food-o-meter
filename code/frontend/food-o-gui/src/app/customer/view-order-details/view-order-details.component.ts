import { Component, Inject, OnInit } from '@angular/core';
import { MAT_BOTTOM_SHEET_DATA } from '@angular/material/bottom-sheet';
import { MatTableDataSource } from '@angular/material/table';
import { DetailedFoodItemQuantity } from 'src/app/_model/detailed-food-item-quantity';
import { DetailedFoodOrder } from 'src/app/_model/detailed-food-order';
import { AlertService, MessageType } from 'src/app/_service/alert.service';
import { FoodOrderServiceService } from 'src/app/_service/food-order-service.service';

@Component({
  selector: 'app-view-order-details',
  templateUrl: './view-order-details.component.html',
  styleUrls: ['./view-order-details.component.css']
})
export class ViewOrderDetailsComponent implements OnInit {

  detailedFoodOrder: DetailedFoodOrder = new DetailedFoodOrder();
  detailedFoodItemQuantityColumns: string[] = ['name', 'quantity', 'price'];
  detailedFoodItemQuantityDataSource: MatTableDataSource<DetailedFoodItemQuantity>;

  constructor(@Inject(MAT_BOTTOM_SHEET_DATA) private data: any,
    private foodOrderService: FoodOrderServiceService,
    private alertService: AlertService) {
    foodOrderService.getDetailedOrderForCustomer(data.customerId, data.orderId).subscribe(response => {
      this.detailedFoodOrder = response;
      this.detailedFoodItemQuantityDataSource = new MatTableDataSource(this.detailedFoodOrder.foodItems);
    });
  }

  ngOnInit(): void {
  }

  cancelOrder() {
    this.foodOrderService.cancelOrderForCustomer(this.data.customerId, this.data.orderId).subscribe(response => {
      this.alertService.showMessage("Order has been cancelled.", MessageType.SUCCESS);
    }, error => {
      this.alertService.showMessage(error.error.message, MessageType.ERROR);
    });
  }

}
