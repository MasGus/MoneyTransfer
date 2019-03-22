package com.revolut.money.transfer.controller.exception;

/**
 * @author Maria.Guseva
 */
public class WrongAmountException extends RuntimeException {
    public WrongAmountException(String message) {
        super(message);
    }
}
