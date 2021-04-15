package com.team13.todolist.model;

import java.time.LocalDateTime;

public class ActivityInfo {

    private String user;
    private String action;
    private String title;
    private String from;
    private String to;
    private LocalDateTime actionTime;

    private ActivityInfo(String user, String action, String title, String from, String to, LocalDateTime actionTime) {
        this.user = user;
        this.action = action;
        this.title = title;
        this.from = from;
        this.to = to;
        this.actionTime = actionTime;
    }
    public static ActivityInfo of(Activity activity, User user) {
        return new ActivityInfo(user.getName(), activity.getAction(), activity.getTitle(),
                activity.getFrom(), activity.getTo(), activity.getActionTime());
    }

    public String getUser() {
        return user;
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
