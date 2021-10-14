import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { DetailedFoodItemQuantity } from 'src/app/_model/detailed-food-item-quantity';
import { FoodItem } from 'src/app/_model/food-item';
import { FoodItemQuantity } from 'src/app/_model/food-item-quantity';
import { FoodItemServiceService } from 'src/app/_service/food-item-service.service';

@Component({
  selector: 'app-customer-place-order',
  templateUrl: './customer-place-order.component.html',
  styleUrls: ['./customer-place-order.component.css']
})
export class CustomerPlaceOrderComponent implements OnInit {

  foodItems: FoodItem[] = [];
  categories: string[] = [];
  selectedTabControl = new FormControl(0);
  foodItemColumns: string[] = ['name', 'price', 'add'];
  foodItemDataSource: MatTableDataSource<FoodItem>;

  selectedFoodItemQuantity: FoodItemQuantity[] = [];
  detailedSelectedFoodItemQuantity: DetailedFoodItemQuantity[] = [];
  totalAmount: number = 0;

  constructor(private foodItemService: FoodItemServiceService) {
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
      foodCategory.add(food.category.toString())
    });
    this.categories = Array.from(foodCategory.values());
  }

  public changeFoodItemTable(selectedTabIndex: number) {
    this.selectedTabControl.setValue(selectedTabIndex);
    this.foodItemDataSource = new MatTableDataSource(this.foodItems.filter(food => food.category.toString().indexOf(this.categories[selectedTabIndex]) >= 0));
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

}
