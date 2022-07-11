package com.github.macg20.walletspy.expense.domain;

import java.util.Optional;

interface ExpenseRepository {

    ExpenseDocument save(ExpenseDocument document);

    Optional<ExpenseDocument> findById(String id);
}
