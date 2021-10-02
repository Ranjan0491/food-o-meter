package com.springmicro.foodometer.constants;

public interface FoodOrderConstants {
    String ORDER_ID_HEADER = "ORDER_ID_HEADER";
    String ORDER_OBJECT_HEADER = "ORDER_OBJECT_HEADER";

    String ALLOCATE_CHEF_ORDER_QUEUE = "allocate-chef-order";
    String ALLOCATE_DELIVERY_AGENT_ORDER_QUEUE = "allocate-delivery-agent-order";

    String FOOD_ITEM_SERVICE_NAME = "fom-item-service";
    String FOOD_USER_SERVICE_NAME = "fom-user-service";
}
