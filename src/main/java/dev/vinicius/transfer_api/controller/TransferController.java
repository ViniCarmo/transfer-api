package dev.vinicius.transfer_api.controller;

import dev.vinicius.transfer_api.dto.TransferRequestDto;
import dev.vinicius.transfer_api.dto.TransferResponseDto;
import dev.vinicius.transfer_api.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<Void> createTransfer(@Valid  @RequestBody TransferRequestDto transferRequestDto){
        transferService.createTransfer(transferRequestDto);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferResponseDto> getTransferById(@PathVariable Integer id){
        return ResponseEntity.ok(transferService.getTransferById(id));
    }
}

