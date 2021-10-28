import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FoodItem } from 'src/app/_model/food-item';
import { FoodItemServiceService } from 'src/app/_service/food-item-service.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-item-add-edit',
  templateUrl: './item-add-edit.component.html',
  styleUrls: ['./item-add-edit.component.css']
})
export class ItemAddEditComponent implements OnInit {

  currentItem: FoodItem = null;
  newItem = false;
  categories = environment.foodItemCategory;

  constructor(public dialogRef: MatDialogRef<ItemAddEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: FoodItem,
    private foodItemService: FoodItemServiceService) {
    this.currentItem = data;
    if (this.currentItem.id === null || this.currentItem.id === undefined) {
      this.newItem = true;
    }
  }

  ngOnInit(): void {
  }

  saveItem() {
    if (this.newItem) {
      console.log(this.currentItem);
      this.foodItemService.saveFoodItem(this.currentItem).subscribe(response => {
        console.log("Saved Item: " + response);
        this.dialogRef.close();
      }, error => {
        console.error(error);
      });
    } else {
      this.foodItemService.updateFoodItem(this.currentItem.id, this.currentItem).subscribe(response => {
        console.log("Updated Item: " + response);
        this.dialogRef.close();
      }, error => {
        console.error(error);
      });
    }
  }

}
