import { FoodItem } from "./food-item";

export class DetailedFoodItemQuantity {
    foodItemDto: FoodItem;
    quantity: Number;

    constructor(foodItemDto?: FoodItem,
        quantity?: Number) {
            this.foodItemDto = foodItemDto;
            this.quantity = quantity;
        }
}
