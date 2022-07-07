package com.github.macg20.walletspy.expense;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;
import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExpenseFacadeTest {

    private ExpenseFacade facade;

    @BeforeAll
    void beforeAll() {
        facade = new ExpenseFacade(new ExpenseRepository() {
            @Override
            public ExpenseDocument save(ExpenseDocument document) {
                return new ExpenseDocument();
            }

            @Override
            public Optional<ExpenseDocument> findById(String id) {
                return Optional.empty();
            }
        });
    }

    @Test
    void should_created_expense_wtih_category() {
        facade.save(new ExpenseDto(null, "test", BigDecimal.ONE));
    }
}
