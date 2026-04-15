package dev.vinicius.transfer_api.service;

import dev.vinicius.transfer_api.dto.TransferRequestDto;
import dev.vinicius.transfer_api.dto.TransferResponseDto;
import dev.vinicius.transfer_api.entities.Account;
import dev.vinicius.transfer_api.entities.Transfer;
import dev.vinicius.transfer_api.exception.AccountNotFoundException;
import dev.vinicius.transfer_api.exception.InsufficientBalanceException;
import dev.vinicius.transfer_api.exception.SameAccountTransferException;
import dev.vinicius.transfer_api.exception.TransferNotFoundException;
import dev.vinicius.transfer_api.repository.AccountRepository;
import dev.vinicius.transfer_api.repository.TransferRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TransferServiceTest {

    @Mock
    private TransferRepository transferRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private TransferService transferService;

    @Test
    void shouldCreateTransferSuccessfully() {
        var source = new Account(1, "Vinicius", BigDecimal.valueOf(1000));
        var destination = new Account(2, "Maria", BigDecimal.valueOf(500));
        var dto = new TransferRequestDto(1, 2, BigDecimal.valueOf(200));

        when(accountRepository.findById(1)).thenReturn(Optional.of(source));
        when(accountRepository.findById(2)).thenReturn(Optional.of(destination));

        // Act
        transferService.createTransfer(dto);

        // Assert
        assertEquals(BigDecimal.valueOf(800), source.getBalance());
        assertEquals(BigDecimal.valueOf(700), destination.getBalance());
        verify(transferRepository).save(any(Transfer.class));
    }

    @Test
    void ShouldThrowExceptionWhenSameAccount() {
        TransferRequestDto transfer = new TransferRequestDto(1, 1, BigDecimal.valueOf(100));

        assertThrows(SameAccountTransferException.class,
                () -> transferService.createTransfer(transfer));
    }


    @Test
    void ShouldThrowExceptionInsufficientBalance() {
        var accountSender = new Account(1, "Vinicius", BigDecimal.valueOf(500));
        var accountDestinantion = new Account(2, "Carlos", BigDecimal.valueOf(500));
        var transfer = new TransferRequestDto(accountSender.getId(), accountDestinantion.getId(), BigDecimal.valueOf(600));

        when(accountRepository.findById(1)).thenReturn(Optional.of(accountSender));
        when(accountRepository.findById(2)).thenReturn(Optional.of(accountDestinantion));

        assertThrows(InsufficientBalanceException.class,
                () -> transferService.createTransfer(transfer));

    }

    @Test
    void shouldThrowExceptionWhenSourceAccountNotFound() {
        var transfer = new TransferRequestDto(1, 2, BigDecimal.valueOf(300));

        assertThrows(AccountNotFoundException.class,
                () -> transferService.createTransfer(transfer));
    }

    @Test
    void shouldThrowExceptionWhenDestinantionAccountNotFound() {
        var accountSource = new Account(1, "Vinicius", BigDecimal.valueOf(540));
        var transfer = new TransferRequestDto(1, 2, BigDecimal.valueOf(300));

        when(accountRepository.findById(1)).thenReturn(Optional.of(accountSource));

        assertThrows(AccountNotFoundException.class,
                () -> transferService.createTransfer(transfer));
    }

    @Test
    void shouldReturnTransferById() {
        var transfer = new Transfer(1, BigDecimal.valueOf(500), 1, 2, LocalDateTime.now());

        when(transferRepository.findById(1)).thenReturn(Optional.of(transfer));

        var result = transferService.getTransferById(1);

        assertEquals(1, result.id());
        assertEquals(BigDecimal.valueOf(500), result.value());

    }

    @Test
    void shouldThrowExceptionWhenTransferNotFound() {
        assertThrows(TransferNotFoundException.class,
                () -> transferService.getTransferById(1));
    }


}