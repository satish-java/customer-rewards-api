
package com.example.rewards.model;

import java.time.LocalDate;

/**
 * Represents a purchase transaction.
 */
public class Transaction {

    private final String customerId;
    private final double amount;
    private final LocalDate transactionDate;

    public Transaction(String customerId, double amount, LocalDate transactionDate) {
        this.customerId = customerId;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }
}
