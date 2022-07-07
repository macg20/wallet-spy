package com.github.macg20.walletspy.expense;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.math.BigInteger;

@Document
class ExpenseDocument {
    @Field(name = "_id")
    private String id;

    @Field(name = "category")
    private String category;

    @Field(name = "cost")
    private BigDecimal cost;

    @Field(name = "user_id")
    private BigInteger userId;

    ExpenseDocument() {
    }

    ExpenseDocument(String category, BigDecimal cost, BigInteger userId) {
        this.category = category;
        this.cost = cost;
        this.userId = userId;
    }

    String getId() {
        return id;
    }

    void setId(String id) {
        this.id = id;
    }

    String getCategory() {
        return category;
    }

    void setCategory(String category) {
        this.category = category;
    }

    BigDecimal getCost() {
        return cost;
    }

    void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
