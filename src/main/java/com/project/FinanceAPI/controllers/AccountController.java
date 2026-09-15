package com.project.FinanceAPI.controllers;

import com.project.FinanceAPI.DTOs.request.AccountRequestDTO;
import com.project.FinanceAPI.DTOs.response.AccountResponseDTO;
import com.project.FinanceAPI.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/users/{userId}/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping()
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody @Validated AccountRequestDTO accountRequestDTO, @PathVariable(value = "userId") UUID userId) {
        AccountResponseDTO accountCreatedDto = this.accountService.createAccount(userId, accountRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(accountCreatedDto);
    }

    @GetMapping()
    public ResponseEntity<List<AccountResponseDTO>> getAllAccountsByUser(@PathVariable(value = "userId") UUID userId) {
        List<AccountResponseDTO> accounts = this.accountService.getAllAccountsByUserId(userId);

        return ResponseEntity.status(HttpStatus.OK).body(accounts);
    }

    @GetMapping(path = "/{accountId}")
    public ResponseEntity<AccountResponseDTO> getAccountByUserIdAndId(@PathVariable(value = "userId") UUID userId, @PathVariable(value = "accountId") UUID accountId ) {
        AccountResponseDTO account = this.accountService.getAccountByUserIdAndId(userId, accountId);

        return ResponseEntity.status(HttpStatus.OK).body(account);
    }

    @PutMapping(path ="/{accountId}")
    public ResponseEntity<AccountResponseDTO> updateAccount(@PathVariable(value = "userId") UUID userId, @PathVariable(value = "accountId") UUID accountId,
                                                            @RequestBody @Validated AccountRequestDTO updateRequestDto) {

        AccountResponseDTO accountUpdatedDto = this.accountService.updateAccount(userId, accountId, updateRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(accountUpdatedDto);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable(value = "userId") UUID userId, @PathVariable(value = "accountId") UUID accountId) {

        this.accountService.deleteAccount(userId, accountId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
