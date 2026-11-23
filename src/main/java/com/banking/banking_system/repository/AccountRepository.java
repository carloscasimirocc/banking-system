package com.banking.banking_system.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.banking_system.model.Account;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    Account findByAccountNumber(String accountNumber);

    List<Account> findAllByCustomerId_Id(UUID customerId);

    boolean existsByAccountNumber(String accountNumber);

}
