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
  category: String[] = [];

  constructor(private foodItemService: FoodItemServiceService) { }
  ngOnInit(): void {
  }

  public getAllItems() {
    this.foodItemService.getAllFoodItems().subscribe((data: FoodItem[]) => { this.foodItems = data; });
  }

}
