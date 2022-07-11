package com.github.macg20.walletspy.expense.domain;

import com.github.macg20.walletspy.expense.domain.ExpenseDocument;
import com.github.macg20.walletspy.expense.domain.ExpenseFacade;
import com.github.macg20.walletspy.expense.domain.ExpenseRepository;
import com.github.macg20.walletspy.expense.dto.ExpenseDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExpenseFacadeTest {

    private ExpenseFacade facade;

    @BeforeAll
    void beforeAll() {
        facade = new ExpenseFacade(new ExpenseRepository() {
            @Override
            public ExpenseDocument save(ExpenseDocument document) {
                ExpenseDocument savedDocument = new ExpenseDocument(document.getCategory(), document.getCost(), null);
                savedDocument.setId(UUID.randomUUID().toString());
                return savedDocument;
            }

            @Override
            public Optional<ExpenseDocument> findById(String id) {
                return Optional.empty();
            }
        });
    }

    @Test
    void should_created_expense_wtih_category() {
        //given
        var dto = new ExpenseDto(null, "test", BigDecimal.ONE);
        //when
        var result = facade.save(dto);

        //then
        assertThat(result)
                .isNotNull();

        assertThat(result.category()).isNotNull().isNotBlank();
        assertThat(result.id()).isNotNull().isNotBlank();
        assertThat(result.cost()).isNotNull().isGreaterThan(BigDecimal.ZERO);
    }
}
