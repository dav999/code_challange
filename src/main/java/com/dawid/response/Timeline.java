package com.dawid.response;

import java.util.Date;

public class Timeline {

    String user;
    String message;
    Date date;

    public Timeline(String user, String message, Date date) {
        this.user = user;
        this.message = message;
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Timeline{" +
                "user='" + user + '\'' +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }
}
