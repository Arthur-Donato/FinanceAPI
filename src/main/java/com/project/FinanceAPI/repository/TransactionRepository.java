package com.project.FinanceAPI.repository;

import com.project.FinanceAPI.model.entities.Transaction;

import java.util.List;

public interface TransactionRepository {

    List<Transaction> findAllByAccountAndCategory(String accountName, String categoryName);
}
