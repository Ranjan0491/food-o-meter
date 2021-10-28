import { FoodItem } from "./food-item";

export class DetailedFoodItemQuantity {
    foodItemDto: FoodItem;
    quantity: number;

    constructor(foodItemDto?: FoodItem,
        quantity?: number) {
            this.foodItemDto = foodItemDto;
            this.quantity = quantity;
        }
}
