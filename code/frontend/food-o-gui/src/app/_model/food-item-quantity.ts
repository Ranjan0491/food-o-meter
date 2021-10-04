export class FoodItemQuantity {
    foodItemId: String;
    quantity: Number;

    constructor(foodItemId?: String,
        quantity?: Number) {
            this.foodItemId = foodItemId;
            this.quantity = quantity;
        }
}
