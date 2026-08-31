package com.project.FinanceAPI.repository;

import com.project.FinanceAPI.model.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    List<Account> findAllByUserId(UUID userID);

    Optional<Account> findByNameAndUserId(String name, UUID userId);

    boolean existsByNameAndUserId(String name, UUID userId);
}
