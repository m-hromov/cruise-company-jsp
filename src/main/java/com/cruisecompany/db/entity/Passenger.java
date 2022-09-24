package com.cruisecompany.db.entity;

import java.math.BigDecimal;

public class Passenger implements Identifiable{

    private long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private double money;
    private String documentPath;
    private UserAccount userAccount;

    @Override
    public long getId() {
        return id;
    }

    public Passenger setId(long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Passenger setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Passenger setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Passenger setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public double getMoney() {
        return money;
    }

    public Passenger setMoney(double money) {
        this.money = money;
        return this;
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public Passenger setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Passenger setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public Passenger setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
        return this;
    }
}
