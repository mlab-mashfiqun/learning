package com.mashfiqun.myfancypdfinvoices.springboot.model;

public class User {
    private String id;
    private String name;

    public User() {}

    public String getId() {
        return id;
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
