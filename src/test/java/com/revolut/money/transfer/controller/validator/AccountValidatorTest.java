package com.revolut.money.transfer.controller.validator;

import com.revolut.money.transfer.controller.exception.ExceptionMessages;
import com.revolut.money.transfer.controller.exception.WrongAccountIdException;
import com.revolut.money.transfer.controller.exception.WrongAmountException;
import com.revolut.money.transfer.repository.AccountRepository;

import com.revolut.money.transfer.repository.impl.AccountRepositoryImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Maria.Guseva
 */
public class AccountValidatorTest {
    private AccountRepository accountRepository = AccountRepositoryImpl.INSTANCE;
    private AccountValidator validator = new AccountValidator(accountRepository);
    private Long accountId;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();


    @Before
    public void setUp() {
        accountId = accountRepository.createAccount();
    }

    @Test
    public void validateIdNonExistingAccountId() {
        expectedEx.expect(WrongAccountIdException.class);
        expectedEx.expectMessage(ExceptionMessages.NON_EXISTING_ACCOUNT_ID);

        validator.validateId("100");
    }

    @Test
    public void validateIdEmptyString() {
        expectedEx.expect(WrongAccountIdException.class);
        expectedEx.expectMessage(ExceptionMessages.WRONG_ACCOUNT_ID_FORMAT);

        validator.validateId("");
    }

    @Test
    public void validateIdNullString() {
        expectedEx.expect(WrongAccountIdException.class);
        expectedEx.expectMessage(ExceptionMessages.WRONG_ACCOUNT_ID_FORMAT);

        validator.validateId(null);
    }

    @Test
    public void validateIdNegativeAccountId() {
        expectedEx.expect(WrongAccountIdException.class);
        expectedEx.expectMessage(ExceptionMessages.WRONG_ACCOUNT_ID_FORMAT);

        validator.validateId("-1");
    }

    @Test
    public void validateIdZeroAccountId() {
        expectedEx.expect(WrongAccountIdException.class);
        expectedEx.expectMessage(ExceptionMessages.WRONG_ACCOUNT_ID_FORMAT);

        validator.validateId("0");
    }

    @Test
    public void validateIdTooLongAccountId() {
        expectedEx.expect(WrongAccountIdException.class);
        expectedEx.expectMessage(ExceptionMessages.WRONG_ACCOUNT_ID_FORMAT);

        String tooLongString = Long.MAX_VALUE + "1";

        validator.validateId(tooLongString);
    }

    @Test
    public void validateIdPositive() {
        validator.validateId(String.valueOf(accountId));
    }

    @Test
    public void validateIdNegativeAmount() {
        expectedEx.expect(WrongAmountException.class);
        expectedEx.expectMessage(ExceptionMessages.WRONG_AMOUNT_FORMAT);

        validator.validateAmount("-1");
    }

    @Test
    public void validateIdZeroAmount() {
        expectedEx.expect(WrongAmountException.class);
        expectedEx.expectMessage(ExceptionMessages.WRONG_AMOUNT_FORMAT);

        validator.validateAmount("0");
    }

    @Test
    public void validateAmountPositive() {
        validator.validateAmount("100.57");
    }

}
