package com.course.kafka.kafka_core_consumer.entity;

import java.util.UUID;

public class PurchaseRequest {

    private UUID requestId;

    private String requestNumber;

    private int amount;

    private String currency;

    public PurchaseRequest() {
    }

    public PurchaseRequest(UUID requestId, String requestNumber, int amount, String currency) {
        this.requestId = requestId;
        this.requestNumber = requestNumber;
        this.amount = amount;
        this.currency = currency;
    }

    public UUID getRequestId() {
        return requestId;
    }

    public void setRequestId(UUID requestId) {
        this.requestId = requestId;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
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

    @Override
    public String toString() {
        return "PurchaseRequest [requestId=" + requestId + ", requestNumber=" + requestNumber + ", amount=" + amount
                + ", currency=" + currency + "]";
    }

}
