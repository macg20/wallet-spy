package com.github.macg20.walletspy.expense.domain;

import com.github.macg20.walletspy.expense.dto.ExpenseDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExpenseFacadeTest {

    private ExpenseFacade facade;
    private InMemoryExpenseRepository inMemoryExpenseRepository;

    @BeforeAll
    void beforeAll() {
        inMemoryExpenseRepository = new InMemoryExpenseRepository();
        facade = new ExpenseFacade(inMemoryExpenseRepository);
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

        assertThat(result.id()).isNotNull().isNotBlank();
        assertThat(result.category()).isNotNull().isNotBlank();
        assertThat(result.cost()).isNotNull().isGreaterThan(BigDecimal.ZERO);
    }

    @Test
    void should_remove_expense_by_id() {
        // given
        var dto = new ExpenseDto(null, "toDelete", BigDecimal.ONE);

        //when
        var savedExpense = facade.save(dto);
        facade.delete(savedExpense.id());
        var result = inMemoryExpenseRepository.findById(savedExpense.id());

        assertFalse(result.isPresent());
    }

    @Test
    void should_update_category_when_task_exists() {
        var dto = new ExpenseDto(null, "INSURANCE", BigDecimal.ONE);

        //when
        var savedExpense = facade.save(dto);
        var updatedCategoryDto = new ExpenseDto(savedExpense.id(), "SHOPPING", savedExpense.cost());

        var updatedResult = facade.save(updatedCategoryDto);

        assertThat(updatedResult.id()).isNotNull().isNotBlank()
                .isEqualTo(savedExpense.id());

        assertThat(updatedResult.category()).isNotNull().isNotBlank().isEqualTo("SHOPPING");
        assertThat(updatedResult.cost()).isNotNull().isEqualTo(savedExpense.cost());
    }
}
