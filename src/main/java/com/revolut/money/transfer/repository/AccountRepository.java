package com.revolut.money.transfer.repository;


import com.revolut.money.transfer.entity.Account;

import java.math.BigDecimal;

/**
 * @author Maria.Guseva
 */
public interface AccountRepository {
    Long createAccount();

    void addAmount(Long id, BigDecimal amount);

    void subtractAmount(Long id, BigDecimal amount);

    Account getAccount(Long id);

    void deleteAccount(Long id);

    boolean exists(Long id);

    void transferAmount(Long senderId, Long recipientId, BigDecimal amount);
}
