export class FoodItem {
    id: String;
    category: String;
    itemName: String;
    itemPrice: number;


    constructor(
        id?: String,
        category?: String,
        itemName?: String,
        itemPrice?: number
    ) {
        this.id = id;
        this.category = category!;
        this.itemName = itemName!;
        this.itemPrice = itemPrice!;
    }
}
