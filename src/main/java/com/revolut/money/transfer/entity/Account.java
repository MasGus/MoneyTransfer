package com.revolut.money.transfer.entity;

import jersey.repackaged.com.google.common.base.MoreObjects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Maria.Guseva
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {
    private Long id;
    private BigDecimal balance;
    private Date startDate;
    private Date endDate;

    @Override
    public String toString(){
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("balance", balance)
                .add("startDate", startDate)
                .add("endDate", endDate)
                .toString();
    }
}
