package com.kpi.korolova.shop.exceptions;

public class NotAuthorizedException extends ShopException {
    public NotAuthorizedException(String message, Exception sourceException) {
        super(message, sourceException);
    }

    public NotAuthorizedException(String message) {
        super(message);
    }
}
