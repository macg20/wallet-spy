package com.github.macg20.walletspy.expense.dto;

import java.math.BigDecimal;

public record ExpenseDto(String id, String category, BigDecimal cost) {
}
