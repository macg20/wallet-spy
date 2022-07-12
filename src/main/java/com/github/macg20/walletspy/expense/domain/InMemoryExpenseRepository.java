package com.github.macg20.walletspy.expense.domain;

import java.util.*;

class InMemoryExpenseRepository implements ExpenseRepository {

    private Map<String, ExpenseDocument> map = new HashMap<>();

    @Override
    public ExpenseDocument save(ExpenseDocument document) {
        Objects.requireNonNull(document);
        if (document.getId() == null) {
            String id = UUID.randomUUID().toString();
            document.setId(id);
        }
        map.put(document.getId(), document);
        return document;
    }

    @Override
    public Optional<ExpenseDocument> findById(String id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public void delete(String id) {
        map.remove(id);
    }
}
