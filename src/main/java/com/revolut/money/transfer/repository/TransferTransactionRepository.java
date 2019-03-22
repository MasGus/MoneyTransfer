package com.revolut.money.transfer.repository;

import com.revolut.money.transfer.entity.TransferTransaction;

/**
 * @author Maria.Guseva
 */
public interface TransferTransactionRepository {
    void addTransaction(TransferTransaction transaction);
}
