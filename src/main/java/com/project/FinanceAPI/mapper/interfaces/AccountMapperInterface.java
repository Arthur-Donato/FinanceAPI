package com.project.FinanceAPI.mapper.interfaces;

import com.project.FinanceAPI.DTOs.request.AccountRequestDTO;
import com.project.FinanceAPI.DTOs.response.AccountResponseDTO;
import com.project.FinanceAPI.model.entities.Account;
import com.project.FinanceAPI.model.entities.User;

import java.util.List;

public interface AccountMapperInterface {

    Account toAccount(AccountRequestDTO dto, User user);

    AccountResponseDTO toResponseDTO(Account entity);

    List<AccountResponseDTO> toResponseDTOList(List<Account> entities);

}
