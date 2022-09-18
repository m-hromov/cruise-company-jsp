package com.cruisecompany.db.entity;

public class Station implements Identifiable{

    private long id;
    private String name;
    @Override
    public long getId() {
        return id;
    }

    public Station setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Station setName(String name) {
        this.name = name;
        return this;
    }
}
