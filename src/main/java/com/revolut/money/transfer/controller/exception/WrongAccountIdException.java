package com.revolut.money.transfer.controller.exception;

/**
 * @author Maria.Guseva
 */
public class WrongAccountIdException extends RuntimeException{
    public WrongAccountIdException(String message) {
        super(message);
    }
}
