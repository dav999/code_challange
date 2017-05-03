package com.dawid.request;


public class RequestFollowUser {

    private String user;
    private String followedUser;

    public RequestFollowUser() {
    }

    public RequestFollowUser(String user, String followedUser) {
        this.user = user;
        this.followedUser = followedUser;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFollowedUser() {
        return followedUser;
    }

    public void setFollowedUser(String followedUser) {
        this.followedUser = followedUser;
    }

    @Override
    public String toString() {
        return "RequestFollowUser{" +
                "user='" + user + '\'' +
                ", followedUser='" + followedUser + '\'' +
                '}';
    }
}
