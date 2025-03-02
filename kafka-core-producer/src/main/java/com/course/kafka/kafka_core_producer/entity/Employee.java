package com.course.kafka.kafka_core_producer.entity;

import java.time.LocalDate;
import java.util.UUID;

public class Employee {

    private UUID employeeId;
    private String name;
    private LocalDate birthDate;

    public Employee() {
    }

    public Employee(UUID employeeId, String name, LocalDate birthDate) {
        this.employeeId = employeeId;
        this.name = name;
        this.birthDate = birthDate;
    }

    public UUID getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(UUID employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Employee [employeeId=" + employeeId + ", name=" + name + ", birthDate=" + birthDate + "]";
    }

}
