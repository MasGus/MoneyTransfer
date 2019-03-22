package com.revolut.money.transfer.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revolut.money.transfer.controller.validator.AccountValidator;
import com.revolut.money.transfer.entity.Account;
import com.revolut.money.transfer.repository.AccountRepository;
import com.revolut.money.transfer.repository.TransferTransactionRepository;
import com.revolut.money.transfer.repository.impl.AccountRepositoryImpl;
import com.revolut.money.transfer.repository.impl.TransferTransactionRepositoryImpl;
import com.revolut.money.transfer.service.AccountService;
import com.revolut.money.transfer.service.impl.AccountServiceImpl;
import jersey.repackaged.com.google.common.collect.ImmutableMap;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;

/**
 * @author Maria.Guseva
 */
@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
public class AccountController {
    private static final String EXCEPTION_FIELD_NAME = "exception";
    private Gson gson = new GsonBuilder().serializeNulls().create();
    private static AccountRepository accountRepository = AccountRepositoryImpl.INSTANCE;
    private static TransferTransactionRepository transactionRepository = TransferTransactionRepositoryImpl.INSTANCE;
    private static AccountService accountService = new AccountServiceImpl(accountRepository, transactionRepository);
    private static AccountValidator accountValidator = new AccountValidator(accountRepository);

    @POST
    public Response create() {
        try {
            Long accountId = accountService.create();
            return Response.status(Response.Status.CREATED)
                    .entity(gson.toJson(ImmutableMap.of("id", accountId))).build();
        } catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(gson.toJson(ImmutableMap.of(EXCEPTION_FIELD_NAME, e.getMessage()))).build();
        }
    }

    @PUT
    @Path("/{id}/add")
    public Response add(@PathParam("id") String id,
                        @QueryParam("amount") String amount) {
        try {
            accountValidator.validateId(id);
            accountValidator.validateAmount(amount);
            accountService.add(Long.parseLong(id), new BigDecimal(amount));
            return Response.status(Response.Status.OK).build();
        } catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(gson.toJson(ImmutableMap.of(EXCEPTION_FIELD_NAME, e.getMessage()))).build();
        }
    }

    @PUT
    @Path("/{id}/subtract")
    public Response subtract(@PathParam("id") String id,
                             @QueryParam("amount") String amount) {
        try {
            accountValidator.validateId(id);
            accountValidator.validateAmount(amount);
            accountService.subtract(Long.parseLong(id), new BigDecimal(amount));
            return Response.status(Response.Status.OK).build();
        } catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(gson.toJson(ImmutableMap.of(EXCEPTION_FIELD_NAME, e.getMessage()))).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") String id){
        try {
            accountValidator.validateId(id);
            Account account = accountService.get(Long.parseLong(id));
            return Response.status(Response.Status.OK).entity(gson.toJson(account)).build();
        } catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(gson.toJson(ImmutableMap.of(EXCEPTION_FIELD_NAME, e.getMessage()))).build();
        }
    }


    @POST
    @Path("/{id}/transfer")
    public Response transfer(@PathParam("id") String senderId,
                             @QueryParam("recipientId") String recipientId,
                             @QueryParam("amount") String amount){
        try {
            accountValidator.validateId(senderId);
            accountValidator.validateId(recipientId);
            accountValidator.validateAmount(amount);
            accountService.transfer(Long.parseLong(senderId), Long.parseLong(recipientId), new BigDecimal(amount));
            return Response.status(Response.Status.OK).build();
        } catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(gson.toJson(ImmutableMap.of(EXCEPTION_FIELD_NAME, e.getMessage()))).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id){
        try {
            accountValidator.validateId(id);
            accountService.delete(Long.parseLong(id));
            return Response.status(Response.Status.OK).build();
        } catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(gson.toJson(ImmutableMap.of(EXCEPTION_FIELD_NAME, e.getMessage()))).build();
        }
    }
}
