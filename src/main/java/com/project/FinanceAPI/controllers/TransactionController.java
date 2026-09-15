package com.project.FinanceAPI.controllers;

import com.project.FinanceAPI.DTOs.request.TransactionRequestDTO;
import com.project.FinanceAPI.DTOs.request.TransactionUpdateRequestDTO;
import com.project.FinanceAPI.DTOs.response.TransactionResponseDTO;
import com.project.FinanceAPI.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping(path = "/users/{userId}/accounts/{accountId}/transactions")
    public ResponseEntity<TransactionResponseDTO> createTransaction(@PathVariable(value = "userId") UUID userId,
                                                                    @PathVariable(value = "accountId") UUID accountId,
                                                                    @RequestBody @Validated TransactionRequestDTO transactionRequestDTO) {

        TransactionResponseDTO transactionCreatedDto = this.transactionService.createTransaction(userId, accountId, transactionRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(transactionCreatedDto);
    }

    @GetMapping(path = "/users/{userId}/transactions")
    public ResponseEntity<List<TransactionResponseDTO>> getAllTransactionsByUser(@PathVariable(value = "userId") UUID userId,
                                                                                 @RequestParam(required = false) UUID accountId,
                                                                                 @RequestParam(required = false) UUID categoryId,
                                                                                 @RequestParam(required = false) String type,
                                                                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
                                                                                 ) {
        List<TransactionResponseDTO> transactions = this.transactionService.getAllTransactions(userId, accountId, categoryId, type, startDate, endDate);

        return ResponseEntity.status(HttpStatus.OK).body(transactions);
    }

    @GetMapping(path = "/users/{userId}/transactions/{transactionId}")
    public ResponseEntity<TransactionResponseDTO> getTransactionById(@PathVariable(value = "userId") UUID userId,
                                                                      @PathVariable(value = "transactionId") UUID transactionId) {
        TransactionResponseDTO transactionDto = this.transactionService.getTransactionById(userId, transactionId);

        return ResponseEntity.status(HttpStatus.OK).body(transactionDto);
    }

    @PutMapping(path = "/users/{userId}/transactions/{transactionId}")
    public ResponseEntity<TransactionResponseDTO> updateTransaction(@PathVariable(value = "userId") UUID userId,
                                                                    @PathVariable(value = "transactionId") UUID transactionId,
                                                                    @RequestBody @Validated TransactionUpdateRequestDTO updateDto) {

        TransactionResponseDTO transactionUpdatedDto = this.transactionService.updateTransaction(userId, transactionId, updateDto);

        return ResponseEntity.status(HttpStatus.OK).body(transactionUpdatedDto);
    }

    @DeleteMapping(path = "/users/{userId}/transactions/{transactionId}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable(value = "userId") UUID userId, @PathVariable(value = "transactionId") UUID transactionId) {
        this.transactionService.deleteTransaction(userId, transactionId);

        return ResponseEntity.noContent().build();
    }
}
