import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatBottomSheet } from '@angular/material/bottom-sheet';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Address } from 'src/app/_model/address';
import { DetailedFoodItemQuantity } from 'src/app/_model/detailed-food-item-quantity';
import { FoodItem } from 'src/app/_model/food-item';
import { FoodItemQuantity } from 'src/app/_model/food-item-quantity';
import { FoodOrder } from 'src/app/_model/food-order';
import { FoodItemServiceService } from 'src/app/_service/food-item-service.service';
import { FoodOrderServiceService } from 'src/app/_service/food-order-service.service';
import { SelectOrderAddressComponent } from '../select-order-address/select-order-address.component';

@Component({
  selector: 'app-customer-place-order',
  templateUrl: './customer-place-order.component.html',
  styleUrls: ['./customer-place-order.component.css']
})
export class CustomerPlaceOrderComponent implements OnInit {

  // need to remove hard coding
  customerId = "612c6c509441d78852dc3c4b";

  foodItems: FoodItem[] = [];
  categories: string[] = [];
  selectedTabControl = new FormControl(0);
  foodItemColumns: string[] = ['name', 'price', 'add'];
  foodItemDataSource: MatTableDataSource<FoodItem>;

  selectedFoodItemQuantity: FoodItemQuantity[] = [];
  detailedSelectedFoodItemQuantity: DetailedFoodItemQuantity[] = [];
  totalAmount: number = 0;
  orderAddress: Address = null;

  constructor(private foodItemService: FoodItemServiceService,
    private foodOrderService: FoodOrderServiceService,
    private placeOrderBottomSheet: MatBottomSheet,
    private infoSnackBar: MatSnackBar) {
    this.getAllItems();
    this.foodItemDataSource = new MatTableDataSource([]);
  }

  ngOnInit(): void {
  }

  public getAllItems() {
    this.foodItemService.getAllFoodItems().subscribe(data => {
      this.foodItems = data;
      this.getUniqueCategories(data);
    });
  }

  private getUniqueCategories(foods: FoodItem[]) {
    let foodCategory = new Set<string>();
    foods.forEach(food => {
      foodCategory.add(food.category)
    });
    this.categories = Array.from(foodCategory.values());
  }

  public changeFoodItemTable(selectedTabIndex: number) {
    this.selectedTabControl.setValue(selectedTabIndex);
    this.foodItemDataSource = new MatTableDataSource(this.foodItems.filter(food => food.category.indexOf(this.categories[selectedTabIndex]) >= 0));
  }

  public addFoodItem(foodItem: FoodItem) {
    if (foodItem) {
      if (this.selectedFoodItemQuantity.length == 0) {
        let foodItemQuantity = new FoodItemQuantity(foodItem.id, 1);
        this.selectedFoodItemQuantity.push(foodItemQuantity);
        this.detailedSelectedFoodItemQuantity.push(new DetailedFoodItemQuantity(foodItem, 1));
      } else {
        let i = this.selectedFoodItemQuantity.findIndex(quantity => quantity.foodItemId === foodItem.id);
        if (i > -1) {
          let foodItemQuantity = this.selectedFoodItemQuantity[i];
          foodItemQuantity.quantity = foodItemQuantity.quantity + 1;
          this.selectedFoodItemQuantity[i] = foodItemQuantity;

          let detailedFoodItemQuantity = this.detailedSelectedFoodItemQuantity[i];
          detailedFoodItemQuantity.quantity = detailedFoodItemQuantity.quantity + 1;
          this.detailedSelectedFoodItemQuantity[i] = detailedFoodItemQuantity;
        } else {
          let foodItemQuantity = new FoodItemQuantity(foodItem.id, 1);
          this.selectedFoodItemQuantity.push(foodItemQuantity);
          this.detailedSelectedFoodItemQuantity.push(new DetailedFoodItemQuantity(foodItem, 1));
        }
      }
      this.totalAmount += foodItem.itemPrice;
    } else {
      console.log("Selected Item cannot be null");
    }
  }

  public openAddressSelection() {
    const bottomSheetRef = this.placeOrderBottomSheet.open(SelectOrderAddressComponent, { data: this.customerId });
    bottomSheetRef.afterDismissed().subscribe((dataFromChild) => {
      this.orderAddress = dataFromChild;
    });
  }

  public plcaeOrder() {
    let newOrder = new FoodOrder(this.customerId, this.orderAddress.id, this.selectedFoodItemQuantity, null, null, null, null, null, null);
    this.foodOrderService.saveOrderForCustomer(newOrder).subscribe(response => {
      let message = 'Congratulations!! Your order is placed. Your payable amount would be ' + response.payableAmount + ' INR';
      if (response.discount > 0) {
        message = 'Congratulations!! Your order is placed and you are eligible for ' + response.discount + '% discount. Your payable amount would be ' + response.payableAmount + ' INR';
      }
      this.infoSnackBar.open(message, 'OK', {
        horizontalPosition: 'end',
        verticalPosition: 'bottom',
      });
    });
  }
}
