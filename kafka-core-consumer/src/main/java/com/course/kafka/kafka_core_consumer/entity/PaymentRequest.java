package com.course.kafka.kafka_core_consumer.entity;

import java.time.LocalDate;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PaymentRequest {

    private int amount;

    private String currency;

    private String bankAccountNumber;

    private String notes;

    private LocalDate paymentDate;

    public String calculateHash() {
        String rawKey = amount + "//" + currency + "//" + bankAccountNumber;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(rawKey.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = String.format("%02x", b);
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

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
