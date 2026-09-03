package com.project.FinanceAPI.services;

import com.project.FinanceAPI.DTOs.request.AccountRequestDTO;
import com.project.FinanceAPI.DTOs.response.AccountResponseDTO;
import com.project.FinanceAPI.exceptions.AccountNotFoundException;
import com.project.FinanceAPI.exceptions.DuplicationResourceException;
import com.project.FinanceAPI.exceptions.UserNotFoundException;
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

    public AccountResponseDTO createAccount(AccountRequestDTO accountDto, UUID userId) {
        User user = this.getUserEntityById(userId);

        List<Account> accounts = user.getAccounts();

        boolean existsAccount= accounts.stream()
                .anyMatch(account -> accountDto.name().equals(account.getName()));

        if(existsAccount){
            throw new DuplicationResourceException("This account name has already exists.");
        }

        Account accountSaved = this.accountRepository.save(this.accountMapper.toAccount(accountDto, user));

        return this.accountMapper.toResponseDTO(accountSaved);
    }

    public List<AccountResponseDTO> getAllAccounts() {
        List<Account> accounts = this.accountRepository.findAll();

        return this.accountMapper.toResponseDTOList(accounts);
    }

    public AccountResponseDTO getOneAccountById(UUID accountId) {
        Account account = this.getAccountEntityById(accountId);

        return this.accountMapper.toResponseDTO(account);
    }

    public AccountResponseDTO updateAccount(UUID accountId, AccountRequestDTO updateRequestDto) {
        Account account = this.getAccountEntityById(accountId);

        account.setName(updateRequestDto.name());

        Account accountUpdated = this.accountRepository.save(account);

        return this.accountMapper.toResponseDTO(accountUpdated);
    }

    public void deleteAccount(UUID accountId){
        Account account = this.getAccountEntityById(accountId);

        this.accountRepository.delete(account);
    }


    private Account getAccountEntityById(UUID accountId) {
        return this.accountRepository.findById(accountId)
                .orElseThrow(AccountNotFoundException::new);
    }

    private User getUserEntityById(UUID userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }
}
