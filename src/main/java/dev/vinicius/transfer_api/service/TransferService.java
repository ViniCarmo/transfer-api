package dev.vinicius.transfer_api.service;

import dev.vinicius.transfer_api.dto.TransferRequestDto;
import dev.vinicius.transfer_api.dto.TransferResponseDto;
import dev.vinicius.transfer_api.entities.Transfer;
import dev.vinicius.transfer_api.exception.AccountNotFoundException;
import dev.vinicius.transfer_api.exception.InsufficientBalanceException;
import dev.vinicius.transfer_api.exception.TransferNotFoundException;
import dev.vinicius.transfer_api.repository.AccountRepository;
import dev.vinicius.transfer_api.repository.TransferRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final AccountRepository accountRepository;

    public TransferService(TransferRepository transferRepository, AccountRepository accountRepository) {
        this.transferRepository = transferRepository;
        this.accountRepository = accountRepository;
    }

    public void verifyBalance(Integer id, BigDecimal value) {
        var account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
        if(account.getBalance().compareTo(value) < 0){
                throw new InsufficientBalanceException(id);
        }
    }


    @Transactional
    public void createTransfer(TransferRequestDto transferRequestDto) {
        var source = accountRepository.findById(transferRequestDto.sourceAccountTitularId())
                .orElseThrow(() -> new AccountNotFoundException(transferRequestDto.sourceAccountTitularId()));
        var destination = accountRepository.findById(transferRequestDto.destinationAccountTitularId())
                .orElseThrow(() -> new AccountNotFoundException(transferRequestDto.destinationAccountTitularId()));

        verifyBalance(source.getId(), transferRequestDto.value());

        var value = transferRequestDto.value();

        source.setBalance(source.getBalance().subtract(value));
        destination.setBalance(destination.getBalance().add(value));

        LocalDateTime timestamp = LocalDateTime.now();

        Transfer newTransfer = new Transfer(null,
                value,
                source.getId(),
                destination.getId(),
                timestamp
        );

        transferRepository.save(newTransfer);
    }

    public TransferResponseDto getTransferById(Integer id) {
        var transfer = transferRepository.findById(id)
                .orElseThrow(() -> new TransferNotFoundException(id));

            return new TransferResponseDto(
                    transfer.getId(),
                    transfer.getSourceAccountId(),
                    transfer.getDestinationAccountId(),
                    transfer.getValue(),
                    transfer.getTimestamp()
            );
    }

}
