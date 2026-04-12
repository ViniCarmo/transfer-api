package dev.vinicius.transfer_api.exception;

public class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(Integer accountId){
        super("Account with id " + accountId + " has insufficient balance");
    }
}
