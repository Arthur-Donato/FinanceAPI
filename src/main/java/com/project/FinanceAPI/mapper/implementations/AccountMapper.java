package com.project.FinanceAPI.mapper.implementations;

import com.project.FinanceAPI.DTOs.request.AccountRequestDTO;
import com.project.FinanceAPI.DTOs.response.AccountResponseDTO;
import com.project.FinanceAPI.mapper.interfaces.AccountMapperInterface;
import com.project.FinanceAPI.model.entities.Account;
import com.project.FinanceAPI.model.entities.User;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class AccountMapper implements AccountMapperInterface {
    @Override
    public Account toAccount(AccountRequestDTO dto, User user) {
        if(dto == null) {
            return null;
        }

        return Account.builder()
                .name(dto.name())
                .user(user)
                .build();
    }

    @Override
    public AccountResponseDTO toResponseDTO(Account entity) {
        if(entity == null) {
            return null;
        }

        return new AccountResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getUser().getId(),
                entity.getCreated_at()
        );
    }

    @Override
    public List<AccountResponseDTO> toResponseDTOList(List<Account> entities) {
        if(entities == null) {
            return null;
        }

        return entities.stream()
                .map(this::toResponseDTO)
                .toList();
    }
}
