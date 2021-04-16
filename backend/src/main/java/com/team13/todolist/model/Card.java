package com.team13.todolist.model;

import org.springframework.data.annotation.Id;

public class Card {
    @Id
    private final Long id;

    private String title;
    private String body;

    Card(Long id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public static Card of(String title, String body) {
        return new Card(null, title, body);
    }

    public Card withId(Long id) {
        return new Card(id, this.title, this.body);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public void update(UpdateCardParameter updateCardInfo) {
        this.title = updateCardInfo.getTitle();
        this.body = updateCardInfo.getBody();
    }
}
