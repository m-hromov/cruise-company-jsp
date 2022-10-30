package com.cruisecompany.dto;

public class UserAccountDTO {
    private long id;
    private String login;
    private String role;


    public long getId() {
        return id;
    }

    public UserAccountDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public UserAccountDTO setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserAccountDTO setRole(String role) {
        this.role = role;
        return this;
    }
}
