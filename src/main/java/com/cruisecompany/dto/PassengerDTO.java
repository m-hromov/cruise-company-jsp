package com.cruisecompany.dto;

import java.math.BigDecimal;

public class PassengerDTO implements Cloneable {
    private long passengerId;
    private long userAccountId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private BigDecimal money;
    private String documentPath;
    private String role;

    public PassengerDTO() {
    }

    public PassengerDTO(PassengerDTO passengerDTO) {
        this.passengerId = passengerDTO.passengerId;
        this.userAccountId = passengerDTO.userAccountId;
        this.firstName = passengerDTO.firstName;
        this.lastName = passengerDTO.lastName;
        this.phone = passengerDTO.phone;
        this.email = passengerDTO.email;
        this.money = passengerDTO.money;
        this.documentPath = passengerDTO.documentPath;
        this.role = passengerDTO.role;
    }

    public long getPassengerId() {
        return passengerId;
    }

    public PassengerDTO setPassengerId(long passengerId) {
        this.passengerId = passengerId;
        return this;
    }

    public long getUserAccountId() {
        return userAccountId;
    }

    public PassengerDTO setUserAccountId(long userAccountId) {
        this.userAccountId = userAccountId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public PassengerDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public PassengerDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public PassengerDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public PassengerDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public PassengerDTO setMoney(BigDecimal money) {
        this.money = money;
        return this;
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public PassengerDTO setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
        return this;
    }

    public String getRole() {
        return role;
    }

    public PassengerDTO setRole(String role) {
        this.role = role;
        return this;
    }
}
