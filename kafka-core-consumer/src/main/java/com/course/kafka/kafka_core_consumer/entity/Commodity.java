package com.course.kafka.kafka_core_consumer.entity;

public class Commodity {

    private String name;

    private double price;

    private String measurement;

    private long timestamp;

    public Commodity() {
    }

    public Commodity(String name, double price, String measurement, long timestamp) {
        this.name = name;
        this.setPrice(price);
        this.measurement = measurement;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getMeasurement() {
        return measurement;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = Math.round(price * 100.0) / 100.0; // Round to 2 decimal places
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Commodity [name=" + name + ", price=" + price + ", measurement=" + measurement + ", timestamp="
                + timestamp + "]";
    }

}
