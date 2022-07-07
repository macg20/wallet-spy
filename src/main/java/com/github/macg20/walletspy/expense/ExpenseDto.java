package com.github.macg20.walletspy.expense;

import java.math.BigDecimal;

record ExpenseDto(String id, String category, BigDecimal cost) {
}
