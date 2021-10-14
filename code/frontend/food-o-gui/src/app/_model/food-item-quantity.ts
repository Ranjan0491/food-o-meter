export class FoodItemQuantity {
    foodItemId: String;
    quantity: number;

    constructor(foodItemId?: String,
        quantity?: number) {
            this.foodItemId = foodItemId;
            this.quantity = quantity;
        }
}
