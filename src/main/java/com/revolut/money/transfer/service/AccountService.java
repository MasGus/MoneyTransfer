package com.revolut.money.transfer.service;

import com.revolut.money.transfer.entity.Account;

import java.math.BigDecimal;

/**
 * @author Maria.Guseva
 */
public interface AccountService {

    Long create();

    void add(Long id, BigDecimal amount);

    void subtract(Long id, BigDecimal amount);

    Account get(Long id);

    void transfer(Long senderId, Long recipientId, BigDecimal amount);

    void delete(Long id);
}
