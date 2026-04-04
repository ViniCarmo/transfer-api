package dev.vinicius.transfer_api.service;

import dev.vinicius.transfer_api.dto.AccountRequestDto;
import dev.vinicius.transfer_api.dto.AccountResponseDto;
import dev.vinicius.transfer_api.entities.Account;
import dev.vinicius.transfer_api.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void createAccount(AccountRequestDto accountRequestDto) {
        var account = new Account();
        account.setTitularName(accountRequestDto.titularName());
        account.setBalance(account.getBalance().add(account.getBalance()));

        var savedAccount = accountRepository.save(account);
    }

    public AccountResponseDto getAccountById(Integer id){
        var account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        return new AccountResponseDto(
                account.getId(),
                account.getTitularName(),
                account.getBalance()
        );
    }
}
