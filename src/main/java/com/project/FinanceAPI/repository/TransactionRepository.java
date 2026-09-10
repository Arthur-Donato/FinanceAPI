package com.project.FinanceAPI.repository;

import com.project.FinanceAPI.model.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    List<Transaction> findAllByAccountUserId(UUID userId);

    Optional<Transaction> findByAccountUserIdAndId(UUID userId, UUID transactionId);
}
