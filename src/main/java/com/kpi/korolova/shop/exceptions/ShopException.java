package com.kpi.korolova.shop.exceptions;

public abstract class ShopException extends Exception {
    private Exception sourceException;

    protected ShopException(String message, Exception sourceException) {
        super("Invalid one or more parameters: " + message);
        this.sourceException = sourceException;
    }

    protected ShopException(String message) {
        super(message);
    }
}
