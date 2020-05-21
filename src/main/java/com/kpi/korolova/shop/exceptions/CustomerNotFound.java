package com.kpi.korolova.shop.exceptions;

public class CustomerNotFound extends ShopException {
    public CustomerNotFound(String message) {
        super(message);
    }

    public CustomerNotFound(String message, Exception sourceException) {
        super(message, sourceException);
    }
}
