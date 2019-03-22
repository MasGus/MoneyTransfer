package com.revolut.money.transfer.controller.validator;

import com.revolut.money.transfer.controller.exception.WrongAccountIdException;
import com.revolut.money.transfer.controller.exception.WrongAmountException;
import com.revolut.money.transfer.repository.AccountRepository;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

import static com.revolut.money.transfer.controller.exception.ExceptionMessages.*;

/**
 * @author Maria.Guseva
 */
@AllArgsConstructor
public class AccountValidator {
    private AccountRepository accountRepository;

    public void validateId(String idString){
        if(idString == null || idString.isEmpty()){
            throw new WrongAccountIdException(WRONG_ACCOUNT_ID_FORMAT);
        }

        try {
            Long id = Long.parseLong(idString);
            if (id <= 0){
                throw new WrongAccountIdException(WRONG_ACCOUNT_ID_FORMAT);
            }

            if (!accountRepository.exists(id)){
                throw new WrongAccountIdException(NON_EXISTING_ACCOUNT_ID);
            }

            if (accountRepository.getAccount(id).getEndDate() != null){
                throw new WrongAccountIdException(OUTDATED_ACCOUNT);
            }
        } catch (NumberFormatException e){
            throw new WrongAccountIdException(WRONG_ACCOUNT_ID_FORMAT);
        }
    }

    public void validateAmount(String amountString){
        if(amountString.isEmpty()){
            throw new WrongAmountException(WRONG_AMOUNT_FORMAT);
        }

        try {
            BigDecimal amount = new BigDecimal(amountString);
            if (amount.compareTo(BigDecimal.ZERO) <= 0){
                throw new WrongAmountException(WRONG_AMOUNT_FORMAT);
            }
        } catch (NumberFormatException e){
            throw new WrongAmountException(WRONG_AMOUNT_FORMAT);
        }
    }
}
