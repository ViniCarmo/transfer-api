package dev.vinicius.transfer_api.exception.handler;

import dev.vinicius.transfer_api.exception.AccountNotFoundException;
import dev.vinicius.transfer_api.exception.InsufficientBalanceException;
import dev.vinicius.transfer_api.exception.RestErrorMessage;
import dev.vinicius.transfer_api.exception.TransferNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<RestErrorMessage> error404Account (AccountNotFoundException e){
    var erro = new RestErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(TransferNotFoundException.class)
    public ResponseEntity<RestErrorMessage> error404Transfer (TransferNotFoundException e){
        var erro = new RestErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<RestErrorMessage> erro422 (InsufficientBalanceException e){
        var erro = new RestErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestErrorMessage> handleValidationErrors(MethodArgumentNotValidException ex) {
        var message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst()
                .orElse("Validation error");

        var error = new RestErrorMessage(HttpStatus.BAD_REQUEST, message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


}
