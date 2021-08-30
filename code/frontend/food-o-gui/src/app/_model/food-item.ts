export class FoodItem {
    category: String;
    itemName: String;
    itemPrice: Number;


    constructor(
        category?: String,
        itemName?: String,
        itemPrice?: Number
    ) {
        this.category = category!;
        this.itemName = itemName!;
        this.itemPrice = itemPrice!;
    }
}
