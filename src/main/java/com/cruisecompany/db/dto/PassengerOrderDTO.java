package com.cruisecompany.db.dto;


public class PassengerOrderDTO   {
    private long orderId;
    private long passengerId;
    private long cruiseId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private boolean paid;
    private boolean banned;
    private boolean confirmed;
    private String documentPath;

    public long getOrderId() {
        return orderId;
    }

    public PassengerOrderDTO setOrderId(long orderId) {
        this.orderId = orderId;
        return this;
    }

    public long getPassengerId() {
        return passengerId;
    }

    public PassengerOrderDTO setPassengerId(long passengerId) {
        this.passengerId = passengerId;
        return this;
    }

    public long getCruiseId() {
        return cruiseId;
    }

    public PassengerOrderDTO setCruiseId(long cruiseId) {
        this.cruiseId = cruiseId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public PassengerOrderDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public PassengerOrderDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public PassengerOrderDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public PassengerOrderDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public boolean isPaid() {
        return paid;
    }

    public PassengerOrderDTO setPaid(boolean paid) {
        this.paid = paid;
        return this;
    }

    public boolean isBanned() {
        return banned;
    }

    public PassengerOrderDTO setBanned(boolean banned) {
        this.banned = banned;
        return this;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public PassengerOrderDTO setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
        return this;
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public PassengerOrderDTO setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
        return this;
    }
}
