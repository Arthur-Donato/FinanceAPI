package com.project.FinanceAPI.specification;

import com.project.FinanceAPI.model.entities.Transaction;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.UUID;

public class TransactionSpecification {

    public static Specification<Transaction> hasUserId(UUID userId) {
        return (root, query, cb) ->
                cb.equal(root.get("account").get("user").get("id"), userId);
    }

    public static Specification<Transaction> hasAccountId(UUID accountId) {
        if(accountId == null) {
            return null;
        }

        return (root, query, cb) ->
                cb.equal(root.get("account").get("id"), accountId);
    }

    public static Specification<Transaction> hasCategoryId(UUID categoryId) {
        if(categoryId == null) {
            return null;
        }

        return (root, query, cb) ->
                cb.equal(root.get("category").get("id"), categoryId);
    }

    public static Specification<Transaction> hasType(String type){
        if(type == null) {
            return null;
        }

        return (root, query, cb) ->
                cb.equal(root.get("type"), type);
    }
    public static Specification<Transaction> hasDateBetween(LocalDate startDate, LocalDate endDate) {
        if(startDate == null || endDate == null) {
            return null;
        }

        return (root, query, cb) ->
                cb.between(root.get("date"), startDate, endDate);
    }
}
