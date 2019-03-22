package com.revolut.money.transfer.repository;

import com.revolut.money.transfer.entity.Account;
import com.revolut.money.transfer.repository.impl.AccountRepositoryImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author Maria.Guseva
 */
public class AccountRepositoryTest {

    private AccountRepository accountRepository = AccountRepositoryImpl.getInstance();
    private static final BigDecimal AMOUNT = BigDecimal.TEN;

    @Test
    public void createAndGetAccount() {
        Date date = new Date();
        Long accountId = accountRepository.createAccount();
        Account account = accountRepository.getAccount(accountId);
        assertEquals("Account ID's should be equal", accountId, account.getId());
        assertEquals("Balances should be equal", BigDecimal.ZERO, account.getBalance());
        assertTrue("Start dates should be equal (with small tolerance)",
                Math.abs(account.getStartDate().getTime() - date.getTime()) < 1000);
        assertNull("End date should be null", account.getEndDate());
    }

    @Test
    public void addAmount() {
        Date date = new Date();
        Long accountId = accountRepository.createAccount();
        accountRepository.addAmount(accountId, AMOUNT);
        Account account = accountRepository.getAccount(accountId);
        assertEquals("Account ID's should be equal", accountId, account.getId());
        assertEquals("Balances should be equal", AMOUNT, account.getBalance());
        assertTrue("Start dates should be equal (with small tolerance)",
                Math.abs(account.getStartDate().getTime() - date.getTime()) < 1000);
        assertNull("End date should be null", account.getEndDate());
    }

    @Test
    public void subtractAmount() {
        Date date = new Date();
        Long accountId = accountRepository.createAccount();
        accountRepository.subtractAmount(accountId, AMOUNT);
        Account account = accountRepository.getAccount(accountId);
        assertEquals("Account ID's should be equal", accountId, account.getId());
        assertEquals("Balances should be equal", AMOUNT.negate(), account.getBalance());
        assertTrue("Start dates should be equal (with small tolerance)",
                Math.abs(account.getStartDate().getTime() - date.getTime()) < 1000);
        assertNull("End date should be null", account.getEndDate());
    }

    @Test
    public void deleteAccount() {
        Date date = new Date();
        Long accountId = accountRepository.createAccount();
        Account account = accountRepository.getAccount(accountId);
        assertEquals("Account ID's should be equal", accountId, account.getId());
        assertEquals("Balances should be equal", BigDecimal.ZERO, account.getBalance());
        assertTrue("Start dates should be equal (with small tolerance)",
                Math.abs(account.getStartDate().getTime() - date.getTime()) < 1000);
        assertTrue("End dates should be equal (with small tolerance)",
                Math.abs(account.getStartDate().getTime() - date.getTime()) < 1000);
    }

    @Test
    public void existsPositive() {
        Long accountId = accountRepository.createAccount();
        assertTrue("Account should exist", accountRepository.exists(accountId));
    }

    @Test
    public void existsNegative() {
        Long accountId = accountRepository.createAccount();
        assertFalse("Account should not exist", accountRepository.exists(accountId + 1));
    }

    @Test
    public void transferAmount() {
        Long accountIdFrom = accountRepository.createAccount();
        accountRepository.addAmount(accountIdFrom, AMOUNT);
        Long accountIdTo = accountRepository.createAccount();
        accountRepository.transferAmount(accountIdFrom, accountIdTo, AMOUNT);
        Account accountFrom = accountRepository.getAccount(accountIdFrom);
        Account accountTo = accountRepository.getAccount(accountIdTo);
        assertEquals("Sender's Balances should be equal", BigDecimal.ZERO, accountFrom.getBalance());
        assertEquals("Recipient's Balances should be equal", AMOUNT, accountTo.getBalance());
    }
}
