package dev.vinicius.transfer_api.service;

import dev.vinicius.transfer_api.dto.AccountRequestDto;
import dev.vinicius.transfer_api.entities.Account;
import dev.vinicius.transfer_api.exception.AccountNotFoundException;
import dev.vinicius.transfer_api.repository.AccountRepository;
import dev.vinicius.transfer_api.repository.TransferRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransferRepository transferRepository;

    @InjectMocks
    private AccountService accountService;


    @Test
    void shouldCreateAccountSucessfull() {
        var dto = new AccountRequestDto("Vinicius");

        accountService.createAccount(dto);

        verify(accountRepository).save(any(Account.class));

    }

    @Test
    void shouldReturnAccountById(){
        var account = new Account(1, "Vinicius", BigDecimal.valueOf(200));

        when(accountRepository.findById(1)).thenReturn(Optional.of(account));

        var result = accountService.getAccountById(1);

        assertEquals(1, result.id());
        assertEquals("Vinicius", result.titularName());
        assertEquals(BigDecimal.valueOf(200), result.balance());

    }

    @Test
    void shouldThrowExceptionWhenAccountNotFound(){
        assertThrows(AccountNotFoundException.class,
                () -> accountService.getAccountById(1));
    }
}