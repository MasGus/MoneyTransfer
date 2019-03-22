package com.revolut.money.transfer.repository.impl;

import com.revolut.money.transfer.entity.TransferTransaction;
import com.revolut.money.transfer.repository.TransferTransactionRepository;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Maria.Guseva
 */
public class TransferTransactionRepositoryImpl implements TransferTransactionRepository {
    private static volatile TransferTransactionRepository instance;
    private static CopyOnWriteArrayList<TransferTransaction> transactionList = new CopyOnWriteArrayList<>();

    private TransferTransactionRepositoryImpl(){}

    public static synchronized TransferTransactionRepository getInstance() {
        if (instance == null)
            instance = new TransferTransactionRepositoryImpl();
        return instance;
    }

    @Override
    public void addTransaction(TransferTransaction transaction) {
        transactionList.add(transaction);
    }
}
