package com.revolut.money.transfer.service.impl;

import com.revolut.money.transfer.entity.Account;
import com.revolut.money.transfer.repository.AccountRepository;
import com.revolut.money.transfer.service.AccountService;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Override
    public Long create() {
        return accountRepository.createAccount();
    }

    @Override
    public void add(Long id, BigDecimal amount) {
        accountRepository.addAmount(id, amount);
    }

    @Override
    public void subtract(Long id, BigDecimal amount) {
        accountRepository.subtractAmount(id, amount);
    }

    @Override
    public Account get(Long id) {
        return accountRepository.getAccount(id);
    }

    @Override
    public void transfer(Long senderId, Long recipientId, BigDecimal amount) {
        accountRepository.transferAmount(senderId, recipientId, amount);
    }

    @Override
    public void delete(Long id) {
        accountRepository.deleteAccount(id);
    }
}
