import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { ExceptionResponse } from 'src/app/_model/exception-response';
import { FoodItem } from 'src/app/_model/food-item';
import { AlertService, MessageType } from 'src/app/_service/alert.service';
import { ConfirmationService } from 'src/app/_service/confirmation.service';
import { FoodItemServiceService } from 'src/app/_service/food-item-service.service';
import { ItemAddEditComponent } from '../item-add-edit/item-add-edit.component';

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
    private alertService: AlertService,
    private confirmationService: ConfirmationService,
    public addEditItemDialog: MatDialog) {
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

  public editFoodItem(foodItem: FoodItem, selectedTabIndex: number) {
    const dialogRef = this.addEditItemDialog.open(ItemAddEditComponent, { data: foodItem });
    dialogRef.afterClosed().subscribe(result => {
      this.foodItemService.getAllFoodItems().subscribe(data => {
        this.foodItems = data;
        this.getUniqueCategories(data);
        this.foodItemDataSource = new MatTableDataSource(this.foodItems.filter(food => food.category.indexOf(this.categories[selectedTabIndex]) >= 0));
      });
    });
  }

  public deleteFoodItem(foodItem: FoodItem, selectedTabIndex: number) {
    this.confirmationService.showMessage('Do you want to remove ' + foodItem.itemName + '?')
      .afterClosed().subscribe(result => {
        if (result === 'OK') {
          this.foodItemService.getAllFoodItems().subscribe(data => {
            this.foodItems = data;
            this.getUniqueCategories(data);
            this.foodItemDataSource = new MatTableDataSource(this.foodItems.filter(food => food.category.indexOf(this.categories[selectedTabIndex]) >= 0));
            this.alertService.showMessage("Item " + foodItem.itemName + " has been deleted", MessageType.SUCCESS);
          }, (error: ExceptionResponse) => {
            this.alertService.showErrorResponseMessage(error, MessageType.ERROR);
          });
        }
      });
  }

  public addNewItem() {
    const dialogRef = this.addEditItemDialog.open(ItemAddEditComponent, { data: new FoodItem(null, null, null, null) });
    dialogRef.afterClosed().subscribe(result => {
      this.foodItemService.getAllFoodItems().subscribe(data => {
        this.foodItems = data;
        this.getUniqueCategories(data);
        this.foodItemDataSource = new MatTableDataSource(this.foodItems.filter(food => food.category.indexOf(this.categories[0]) >= 0));
      });
    });
  }

}
