export class FoodItem {
    id: string;
    category: string;
    itemName: string;
    itemPrice: number;


    constructor(
        id?: string,
        category?: string,
        itemName?: string,
        itemPrice?: number
    ) {
        this.id = id;
        this.category = category!;
        this.itemName = itemName!;
        this.itemPrice = itemPrice!;
    }
}
