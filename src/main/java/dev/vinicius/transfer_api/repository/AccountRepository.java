package dev.vinicius.transfer_api.repository;


import dev.vinicius.transfer_api.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
