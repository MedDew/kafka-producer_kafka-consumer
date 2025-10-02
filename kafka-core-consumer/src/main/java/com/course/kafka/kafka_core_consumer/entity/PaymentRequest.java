package com.course.kafka.kafka_core_consumer.entity;

import java.time.LocalDate;

public class PaymentRequest {

    private int amount;

    private String currency;

    private String bankAccountNumber;

    private String notes;

    private LocalDate paymentDate;

    public PaymentRequest() {
    }

    public PaymentRequest(int amount, String currency, String bankAccountNumber, String notes, LocalDate paymentDate) {
        this.amount = amount;
        this.currency = currency;
        this.bankAccountNumber = bankAccountNumber;
        this.notes = notes;
        this.paymentDate = paymentDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "PaymentRequest [amount=" + amount + ", currency=" + currency + ", bankAccountNumber="
                + bankAccountNumber + ", notes=" + notes + ", paymentDate=" + paymentDate + "]";
    }

}
