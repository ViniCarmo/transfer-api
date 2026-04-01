package dev.vinicius.transfer_api.service;

import dev.vinicius.transfer_api.repository.TransferRepository;
import org.springframework.stereotype.Service;

@Service
public class TransferService {

    private final TransferRepository transferRepository;

    public TransferService(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }


}
