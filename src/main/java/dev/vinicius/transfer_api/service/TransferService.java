package dev.vinicius.transfer_api.service;

import dev.vinicius.transfer_api.entities.Account;
import dev.vinicius.transfer_api.repository.TransferRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final Account account;

    public TransferService(TransferRepository transferRepository, Account account) {
        this.transferRepository = transferRepository;
        this.account = account;
    }

    private boolean validateTransfer(BigDecimal value, String sourceAccountName, String destinationAccountName){
        if(value.compareTo(BigDecimal.ZERO) <= 0){
            return false;
        }
        if(sourceAccountName.equals(destinationAccountName)){
            return false;
        }
        if(account.getBalance().compareTo(value) < 0){
            return false;
        }
        return true;
    }

    public void createTransfer(BigDecimal value, String sourceAccountName, String destinationAccountNam){
        if(validateTransfer(value, sourceAccountName, destinationAccountNam) == true) {

        }

    }

}
