package com.cruisecompany.entity;

public class UserAccount implements Identifiable{
    private long id;
    private String email;
    private String password;
    private String passwordSalt;
    private String role;
    @Override
    public long getId() {
        return id;
    }

    public UserAccount setId(long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserAccount setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserAccount setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserAccount setRole(String role) {
        this.role = role;
        return this;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public UserAccount setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
        return this;
    }
}
