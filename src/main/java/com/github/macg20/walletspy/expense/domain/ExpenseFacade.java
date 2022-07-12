package com.github.macg20.walletspy.expense.domain;

import com.github.macg20.walletspy.expense.dto.ExpenseDto;

public class ExpenseFacade {

    private ExpenseRepository expenseRepository;

    ExpenseFacade(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public ExpenseDto save(ExpenseDto toSave) {
        ExpenseDocument document = expenseRepository.save(
                expenseRepository.findById(toSave.id())
                        .map(toUpdate -> {
                            toUpdate.setCategory(toSave.category());
                            toUpdate.setCost(toSave.cost());
                            return toUpdate;
                        })
                        .orElseGet(
                                () -> new ExpenseDocument(toSave.category(), toSave.cost(), null)));

        return new ExpenseDto(document.getId(), document.getCategory(), document.getCost());
    }

    public void delete(String id) {
        expenseRepository.findById(id)
                .ifPresent(expense -> expenseRepository.delete(id));
    }
}
