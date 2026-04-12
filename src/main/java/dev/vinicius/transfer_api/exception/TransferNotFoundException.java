package dev.vinicius.transfer_api.exception;

public class TransferNotFoundException extends RuntimeException{
    public TransferNotFoundException(Integer id){
        super("Transfer with id " + id + " not found");
    }
}
