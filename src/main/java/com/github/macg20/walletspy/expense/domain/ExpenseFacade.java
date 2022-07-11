package com.github.macg20.walletspy.expense.domain;

import com.github.macg20.walletspy.expense.dto.ExpenseDto;

public class ExpenseFacade {

    private ExpenseRepository expenseRepository;

    ExpenseFacade(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    ExpenseDto save(ExpenseDto toSave) {
        ExpenseDocument document = expenseRepository.save(
                expenseRepository.findById(toSave.id())
                        .orElseGet(() -> new ExpenseDocument(toSave.category(), toSave.cost(), null))
        );
        return new ExpenseDto(document.getId(), document.getCategory(), document.getCost());
    }
}
