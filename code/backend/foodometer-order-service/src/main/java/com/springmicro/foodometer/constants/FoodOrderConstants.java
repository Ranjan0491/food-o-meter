package com.springmicro.foodometer.constants;

public interface FoodOrderConstants {
    String ORDER_ID_HEADER = "ORDER_ID_HEADER";
    String ORDER_OBJECT_HEADER = "ORDER_OBJECT_HEADER";

    String VALIDATE_ORDER_QUEUE = "validate-order";
    String VALIDATE_ORDER_RESPONSE_QUEUE = "validate-order-response";
    String ALLOCATE_ORDER_QUEUE = "allocate-order";
    String ALLOCATE_ORDER_RESPONSE_QUEUE = "allocate-order-response";
    String ALLOCATE_FAILURE_QUEUE = "allocation-failure";
    String DEALLOCATE_ORDER_QUEUE = "deallocate-order";

    String FOOD_ITEM_SERVICE_NAME = "fom-item-service";
    String FOOD_USER_SERVICE_NAME = "fom-user-service";
}
