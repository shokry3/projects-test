package com.app.shop.exceptionhandel;

public class StoreSecurityException  extends RuntimeException {
    public StoreSecurityException(String message) {
        super(message);
    }
}
