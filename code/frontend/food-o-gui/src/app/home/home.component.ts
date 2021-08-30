import { Component, OnInit } from '@angular/core';
import { FoodItem } from '../_model/food-item';
import { FoodItemServiceService } from '../_service/food-item-service.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  foodItems: FoodItem[] = [];
  category: string[] = [];
  constructor(private foodItemService: FoodItemServiceService) { }

  ngOnInit(): void {
    this.getAllItems();
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
    this.category = Array.from(foodCategory.values());
  }

}
