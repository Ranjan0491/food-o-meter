export class FoodItemQuantity {
    foodItemId: string;
    quantity: number;

    constructor(foodItemId?: string,
        quantity?: number) {
        this.foodItemId = foodItemId;
        this.quantity = quantity;
    }
}
