export class FoodItem {
    private category: String;
    private itemName: String;
    private itemPrice: Number;


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
