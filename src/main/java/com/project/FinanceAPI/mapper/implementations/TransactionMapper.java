package com.project.FinanceAPI.mapper.implementations;

import com.project.FinanceAPI.DTOs.request.TransactionRequestDTO;
import com.project.FinanceAPI.DTOs.response.TransactionResponseDTO;
import com.project.FinanceAPI.mapper.interfaces.TransactionMapperInterface;
import com.project.FinanceAPI.model.entities.Account;
import com.project.FinanceAPI.model.entities.Category;
import com.project.FinanceAPI.model.entities.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionMapper implements TransactionMapperInterface {

    @Override
    public Transaction toTransaction(TransactionRequestDTO dto, Account account, Category category) {
        if(dto == null) {
            return null;
        }

        return Transaction.builder()
                .type(dto.type())
                .description(dto.description())
                .date(dto.date())
                .value(dto.value())
                .category(category)
                .account(account)
                .build();
    }

    @Override
    public TransactionResponseDTO toResponseDTO(Transaction entity) {
        if(entity == null) {
            return null;
        }

        return new TransactionResponseDTO(
                entity.getId(),
                entity.getType(),
                entity.getDescription(),
                entity.getDate(),
                entity.getValue(),
                entity.getAccount().getId(),
                entity.getCategory().getId(),
                entity.getCreated_at()
        );
    }

    @Override
    public List<TransactionResponseDTO> toResponseDTOList(List<Transaction> entities) {
        if(entities == null) {
            return null;
        }

        return entities.stream()
                .map(this::toResponseDTO)
                .toList();
    }
}
