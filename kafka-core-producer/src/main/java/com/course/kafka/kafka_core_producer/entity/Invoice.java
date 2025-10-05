package com.course.kafka.kafka_core_producer.entity;

public class Invoice {

    private String invoiceNumber;

    private double amount;

    private String currency;

    public Invoice() {
    }

    public Invoice(String invoiceNumber, double amount, String currency) {
        this.invoiceNumber = invoiceNumber;
        this.amount = amount;
        this.currency = currency;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Invoice [invoiceNumber=" + invoiceNumber + ", amount=" + amount + ", currency=" + currency + "]";
    }

}
