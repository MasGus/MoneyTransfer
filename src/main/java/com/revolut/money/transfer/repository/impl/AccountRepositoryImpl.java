package com.revolut.money.transfer.repository.impl;

import com.revolut.money.transfer.entity.Account;
import com.revolut.money.transfer.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Maria.Guseva
 */
public enum AccountRepositoryImpl implements AccountRepository {
    INSTANCE;
    private static Map<Long, Account> accountMap = new ConcurrentHashMap<>();
    private static AtomicLong lastAccountId = new AtomicLong();
    private static final BigDecimal DEFAULT_BALANCE = BigDecimal.ZERO;

    AccountRepositoryImpl(){}

    @Override
    public Long createAccount(){
        Long accountId = lastAccountId.incrementAndGet();
        Account account = new Account(accountId, DEFAULT_BALANCE, new Date(), null);
        account.setId(accountId);
        accountMap.put(accountId, account);

        return accountId;
    }

    @Override
    public void addAmount(Long id, BigDecimal amount){
        accountMap.computeIfPresent(id, (key, oldAccount) -> new Account(id, oldAccount.getBalance().add(amount),
                oldAccount.getStartDate(), oldAccount.getEndDate()));
    }

    @Override
    public void subtractAmount(Long id, BigDecimal amount){
        accountMap.computeIfPresent(id, (key, oldAccount) -> new Account(id, oldAccount.getBalance().subtract(amount),
                oldAccount.getStartDate(), oldAccount.getEndDate()));
    }

    @Override
    public Account getAccount(Long id){
        return accountMap.get(id);
    }

    @Override
    public void deleteAccount(Long id){
        accountMap.computeIfPresent(id, (key, oldAccount) -> new Account(id, oldAccount.getBalance(),
                oldAccount.getStartDate(), new Date()));
    }

    @Override
    public boolean exists(Long id) {
        return accountMap.containsKey(id);
    }

    @Override
    public synchronized void transferAmount(Long senderId, Long recipientId, BigDecimal amount) {
        subtractAmount(senderId, amount);
        addAmount(recipientId, amount);
    }
}
