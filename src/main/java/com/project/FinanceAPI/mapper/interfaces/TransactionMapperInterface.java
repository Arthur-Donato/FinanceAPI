package com.project.FinanceAPI.mapper.interfaces;

import com.project.FinanceAPI.DTOs.request.TransactionRequestDTO;
import com.project.FinanceAPI.DTOs.response.TransactionResponseDTO;
import com.project.FinanceAPI.model.entities.Account;
import com.project.FinanceAPI.model.entities.Category;
import com.project.FinanceAPI.model.entities.Transaction;

import java.util.List;

public interface TransactionMapperInterface {

    Transaction toTransaction(TransactionRequestDTO dto, Account account, Category category);

    TransactionResponseDTO toResponseDTO(Transaction entity);

    List<TransactionResponseDTO> toResponseDTOList(List<Transaction> entities);
}
