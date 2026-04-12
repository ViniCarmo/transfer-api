package dev.vinicius.transfer_api.exception;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(Integer id){
        super("Account with id " + id + " not found");
    }
}
