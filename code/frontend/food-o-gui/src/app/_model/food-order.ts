import { FoodItemQuantity } from "./food-item-quantity";

export class FoodOrder {
    id: String;
    foodItems: FoodItemQuantity[];
    orderTimestamp: Date;
    orderAmount: Number;
    orderStatus: String;
    discount: Number;
    discountedAmount: Number;
    customerId: String;
    customerAddressId: String;

    constructor(id?: String,
        foodItems?: FoodItemQuantity[],
        orderTimestamp?: Date,
        orderAmount?: Number,
        orderStatus?: String,
        discount?: Number,
        discountedAmount?: Number,
        customerId?: String,
        customerAddressId?: String) {
            this.id = id;
            this.foodItems = foodItems;
            this.orderTimestamp = orderTimestamp;
            this.orderAmount = orderAmount;
            this.orderStatus = orderStatus;
            this.discount = discount;
            this.discountedAmount = discountedAmount;
            this.customerId = customerId;
            this.customerAddressId = customerAddressId;
        }
}
