package com.revolut.money.transfer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Maria.Guseva
 */
@AllArgsConstructor
@Data
public class TransferTransaction {
    private Long senderId;
    private Long recipientId;
    private BigDecimal amount;
    private Date operationDate;
}
