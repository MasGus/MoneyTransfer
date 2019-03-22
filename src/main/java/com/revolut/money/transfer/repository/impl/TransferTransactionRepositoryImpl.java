package com.revolut.money.transfer.repository.impl;

import com.revolut.money.transfer.entity.TransferTransaction;
import com.revolut.money.transfer.repository.TransferTransactionRepository;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Maria.Guseva
 */
public enum TransferTransactionRepositoryImpl implements TransferTransactionRepository {
    INSTANCE;

    private static CopyOnWriteArrayList<TransferTransaction> transactionList = new CopyOnWriteArrayList<>();

    TransferTransactionRepositoryImpl(){}

    @Override
    public void addTransaction(TransferTransaction transaction) {
        transactionList.add(transaction);
    }
}
