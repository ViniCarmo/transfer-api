package dev.vinicius.transfer_api.controller;

import dev.vinicius.transfer_api.dto.AccountRequestDto;
import dev.vinicius.transfer_api.dto.AccountResponseDto;
import dev.vinicius.transfer_api.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Void> createAccount(@Valid @RequestBody AccountRequestDto accountRequestDto) {
        accountService.createAccount(accountRequestDto);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDto> getAccountById(@Valid @PathVariable Integer id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }
}
