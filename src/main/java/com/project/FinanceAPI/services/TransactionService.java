package com.project.FinanceAPI.services;


import com.project.FinanceAPI.DTOs.request.TransactionRequestDTO;
import com.project.FinanceAPI.DTOs.request.TransactionUpdateRequestDTO;
import com.project.FinanceAPI.DTOs.response.TransactionResponseDTO;
import com.project.FinanceAPI.exceptions.ResourceNotFoundException;
import com.project.FinanceAPI.mapper.implementations.TransactionMapper;
import com.project.FinanceAPI.model.entities.Account;
import com.project.FinanceAPI.model.entities.Category;
import com.project.FinanceAPI.model.entities.Transaction;
import com.project.FinanceAPI.repository.AccountRepository;
import com.project.FinanceAPI.repository.CategoryRepository;
import com.project.FinanceAPI.repository.TransactionRepository;
import com.project.FinanceAPI.specification.TransactionSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionMapper transactionMapper;

    private final TransactionRepository transactionRepository;

    private final AccountRepository accountRepository;

    private final CategoryRepository categoryRepository;

    public TransactionResponseDTO createTransaction(UUID userId, UUID accountId, TransactionRequestDTO transactionRequestDTO) {
        Category category = this.getCategoryEntityByUserIdAndId(userId, transactionRequestDTO.categoryId());

        Account account = this.getAccountEntityByUserIdAndId(userId, accountId);

        Transaction transaction = this.transactionMapper.toTransaction(transactionRequestDTO, account, category);

        Transaction transactionSaved = this.transactionRepository.save(transaction);

        return this.transactionMapper.toResponseDTO(transactionSaved);
    }

    public List<TransactionResponseDTO> getAllTransactions(UUID userId, UUID accountId, UUID categoryId, String type,
                                                           LocalDate startDate, LocalDate endDate){

        List<Specification<Transaction>> specs = Stream.of(
                TransactionSpecification.hasUserId(userId),
                TransactionSpecification.hasAccountId(accountId),
                TransactionSpecification.hasCategoryId(categoryId),
                TransactionSpecification.hasType(type),
                TransactionSpecification.hasDateBetween(startDate, endDate)
        ).filter(Objects::nonNull).toList();

        Specification<Transaction> spec = Specification.allOf(specs);

        List<Transaction> transactions = this.transactionRepository.findAll(spec);

        return this.transactionMapper.toResponseListDTO(transactions);
    }

    public TransactionResponseDTO getTransactionById(UUID userId, UUID transactionId) {
        Transaction transaction = this.getTransactionEntityByUserIdAndId(userId, transactionId);

        return this.transactionMapper.toResponseDTO(transaction);
    }

    public TransactionResponseDTO updateTransaction(UUID userId, UUID transactionId, TransactionUpdateRequestDTO updateRequestDTO) {
        Transaction transaction = this.getTransactionEntityByUserIdAndId(userId, transactionId);

        if(updateRequestDTO.categoryId() != null) {
            Category category = this.getCategoryEntityByUserIdAndId(updateRequestDTO.categoryId(), userId);
            transaction.setCategory(category);

        }

        if(updateRequestDTO.type() != null) {
            transaction.setType(updateRequestDTO.type());
        }

        if(updateRequestDTO.description() != null && !updateRequestDTO.description().isBlank()) {
            transaction.setDescription(updateRequestDTO.description());
        }

        if(updateRequestDTO.date() != null) {
            transaction.setDate(updateRequestDTO.date());
        }

        if(updateRequestDTO.value() != null) {
            transaction.setValue(updateRequestDTO.value());
        }

        Transaction transactionUpdated = this.transactionRepository.save(transaction);

        return this.transactionMapper.toResponseDTO(transactionUpdated);

    }
    public void deleteTransaction(UUID userId, UUID transactionId) {
        Transaction transaction = this.getTransactionEntityByUserIdAndId(userId, transactionId);

        this.transactionRepository.delete(transaction);
    }

    private Account getAccountEntityByUserIdAndId(UUID userId, UUID accountId) {
        return this.accountRepository.findByUserIdAndId(userId, accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found."));
    }

    private Category getCategoryEntityByUserIdAndId(UUID userId, UUID categoryId) {
        return this.categoryRepository.findByUserIdAndId(userId, categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found."));
    }

    private Transaction getTransactionEntityByUserIdAndId(UUID userId, UUID transactionId) {
        return this.transactionRepository.findByAccountUserIdAndId(userId, transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found."));
    }

}
