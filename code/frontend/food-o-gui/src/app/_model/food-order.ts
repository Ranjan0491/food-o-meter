import { FoodItemQuantity } from "./food-item-quantity";

export class FoodOrder {
    id: string;
    foodItems: FoodItemQuantity[];
    orderTimestamp: Date;
    orderAmount: number;
    orderStatus: string;
    discount: number;
    payableAmount: number;
    customerId: string;
    customerAddressId: string;

    constructor(
        customerId?: string,
        customerAddressId?: string,
        foodItems?: FoodItemQuantity[],
        orderTimestamp?: Date,
        orderAmount?: number,
        orderStatus?: string,
        discount?: number,
        payableAmount?: number,
        id?: string) {
        this.id = id;
        this.foodItems = foodItems;
        this.orderTimestamp = orderTimestamp;
        this.orderAmount = orderAmount;
        this.orderStatus = orderStatus;
        this.discount = discount;
        this.payableAmount = payableAmount;
        this.customerId = customerId;
        this.customerAddressId = customerAddressId;
    }
}
