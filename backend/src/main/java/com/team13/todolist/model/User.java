package com.team13.todolist.model;

import org.springframework.data.annotation.Id;

public class User {

    @Id
    private Long id;
    private String name;
    private String password;

    public User(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public User of(String name, String password) {
        return new User(null, name, password);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
