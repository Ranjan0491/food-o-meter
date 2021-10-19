import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { FoodItem } from 'src/app/_model/food-item';
import { AlertService, MessageType } from 'src/app/_service/alert.service';
import { FoodItemServiceService } from 'src/app/_service/food-item-service.service';

@Component({
  selector: 'app-item-management',
  templateUrl: './item-management.component.html',
  styleUrls: ['./item-management.component.css']
})
export class ItemManagementComponent implements OnInit {

  foodItems: FoodItem[] = [];
  categories: string[] = [];
  selectedTabControl = new FormControl(0);
  foodItemColumns: string[] = ['name', 'price', 'action'];
  foodItemDataSource: MatTableDataSource<FoodItem>;

  constructor(private foodItemService: FoodItemServiceService,
    private alertService: AlertService) {
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

  public editFoodItem(foodItem: FoodItem) {

  }

  public deleteFoodItem(foodItem: FoodItem) {
    this.foodItemService.deleteFoodItem(foodItem.id).subscribe(response => {
      this.getAllItems();
    }, error => {
      this.alertService.showMessage("Item could not be deleted", MessageType.ERROR);
    });
  }

}
