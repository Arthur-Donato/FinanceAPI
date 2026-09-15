package com.project.FinanceAPI.services;

import com.project.FinanceAPI.DTOs.request.AccountRequestDTO;
import com.project.FinanceAPI.DTOs.response.AccountResponseDTO;
import com.project.FinanceAPI.exceptions.DuplicationResourceException;
import com.project.FinanceAPI.exceptions.ResourceNotFoundException;
import com.project.FinanceAPI.mapper.implementations.AccountMapper;
import com.project.FinanceAPI.model.entities.Account;
import com.project.FinanceAPI.model.entities.User;
import com.project.FinanceAPI.repository.AccountRepository;
import com.project.FinanceAPI.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    private final UserRepository userRepository;

    private final AccountMapper accountMapper;

    public AccountResponseDTO createAccount(UUID userId, AccountRequestDTO accountDto) {
        User user = this.getUserEntityById(userId);

       if(this.accountRepository.existsByUserIdAndName(userId, accountDto.name())) {
           throw new DuplicationResourceException("This user already have an account with this name.");
       }

       Account account = this.accountMapper.toAccount(accountDto, user);

       Account accountSaved = this.accountRepository.save(account);

       return this.accountMapper.toResponseDTO(accountSaved);
    }

    public List<AccountResponseDTO> getAllAccountsByUserId(UUID userId) {
        List<Account> accounts = this.accountRepository.findAllByUserId(userId);

        return this.accountMapper.toResponseDTOList(accounts);
    }

    public AccountResponseDTO getAccountByUserIdAndId(UUID userId, UUID accountId) {
        Account account = this.getAccountEntityByUserIdAndId(userId, accountId);

        return this.accountMapper.toResponseDTO(account);
    }

    public AccountResponseDTO getAccountByNameAndUserId(UUID userId, String name) {
        Account account = this.getAccountEntityByUserIdAndName(userId, name);

        return this.accountMapper.toResponseDTO(account);
    }

    public AccountResponseDTO updateAccount(UUID userId, UUID accountId, AccountRequestDTO updateRequestDto) {
        Account account = this.getAccountEntityByUserIdAndId(userId, accountId);

        if(this.accountRepository.existsByUserIdAndIdNotAndName(userId, accountId, updateRequestDto.name())) {
            throw new DuplicationResourceException("This user already have an account with this name.");
        }

        account.setName(updateRequestDto.name());

        Account accountUpdated = this.accountRepository.save(account);

        return this.accountMapper.toResponseDTO(accountUpdated);
    }

    public void deleteAccount(UUID userId, UUID accountId){
        Account account = this.getAccountEntityByUserIdAndId(userId, accountId);

        this.accountRepository.delete(account);
    }


    private Account getAccountEntityByUserIdAndId(UUID userId, UUID accountId) {
        return this.accountRepository.findByUserIdAndId(userId, accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found."));
    }

    private User getUserEntityById(UUID userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));
    }

    private Account getAccountEntityByUserIdAndName(UUID userId, String name) {
        return this.accountRepository.findByUserIdAndName(userId, name)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with this parameters."));
    }
}
