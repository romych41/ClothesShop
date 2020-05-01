package com.kpi.korolova.shop.exceptions;

public class InvalidParamsException extends ShopException {
    public InvalidParamsException(String message) {
        super("Invalid one or more parameters: " + message);
    }
}
