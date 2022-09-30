package com.cruisecompany.entity;

public class UserAccount implements Identifiable{
    private long id;
    private String login;
    private String password;
    private String role;
    @Override
    public long getId() {
        return id;
    }

    public UserAccount setId(long id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public UserAccount setLogin(String login) {
        this.login = login;
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
}
