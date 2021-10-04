import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { FoodItem } from '../_model/food-item';
import { FoodItemServiceService } from '../_service/food-item-service.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  foodItems: FoodItem[] = [];
  categories: string[] = [];
  selectedTabControl = new FormControl(0);
  foodItemColumns: string[] = ['name', 'price'];
  foodItemDataSource: MatTableDataSource<FoodItem>;

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

}
