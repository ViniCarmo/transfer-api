package dev.vinicius.transfer_api.repository;

import dev.vinicius.transfer_api.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Integer> {
}
