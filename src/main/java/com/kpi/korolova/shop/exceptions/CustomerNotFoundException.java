package com.kpi.korolova.shop.exceptions;

public class CustomerNotFoundException extends ShopException {
    public CustomerNotFoundException(String message) {
        super(message);
    }

    public CustomerNotFoundException(String message, Exception sourceException) {
        super(message, sourceException);
    }
}
