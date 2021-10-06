import { Address } from "./address";
import { DetailedFoodItemQuantity } from "./detailed-food-item-quantity";
import { Staff } from "./staff";
import { User } from "./user";

export class DetailedFoodOrder {
    id: String;
    foodItems: DetailedFoodItemQuantity[];
    orderTimestamp: Date;
    orderAmount: Number;
    orderStatus: String;
    discount: Number;
    payableAmount: Number;
    customer: User;
    orderAddress: Address;
    chef: Staff;
    deliveryAgent: Staff;

    constructor(id?: String,
        foodItems?: DetailedFoodItemQuantity[],
        orderTimestamp?: Date,
        orderAmount?: Number,
        orderStatus?: String,
        discount?: Number,
        payableAmount?: Number,
        customer?: User,
        orderAddress?: Address,
        chef?: Staff,
        deliveryAgent?: Staff) {
            this.id = id;
            this.foodItems = foodItems;
            this.orderTimestamp = orderTimestamp;
            this.orderAmount = orderAmount;
            this.orderStatus = orderStatus;
            this.discount = discount;
            this.payableAmount = payableAmount;
            this.customer = customer;
            this.orderAddress = orderAddress;
            this.chef = chef;
            this.deliveryAgent = deliveryAgent;
        }
}
