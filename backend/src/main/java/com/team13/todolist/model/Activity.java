package com.team13.todolist.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Activity {

    @Id
    private Long id;

    private Long userId;

    private String action;
    private String title;
    private String from;
    private String to;

    @CreatedDate
    private LocalDateTime actionTime;

    public Activity(Long id, Long userId, String action, String title, String from, String to, LocalDateTime actionTime) {
        this.id = id;
        this.userId = userId;
        this.action = action;
        this.title = title;
        this.from = from;
        this.to = to;
        this.actionTime = actionTime;
    }

    public static Activity of(Long userId, String action, String title, String from, String to) {
        return new Activity(null, userId, action, title, from, to, LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getAction() {
        return action;
    }

    public String getTitle() {
        return title;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public LocalDateTime getActionTime() {
        return actionTime;
    }
}
