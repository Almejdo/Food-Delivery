package com.fooddelivery.exception;

public class DeliveryManMaxOrdersReachedException extends RuntimeException {
    public DeliveryManMaxOrdersReachedException() {
        super("Delivery man has reached their maximum number of orders.");
    }
}
