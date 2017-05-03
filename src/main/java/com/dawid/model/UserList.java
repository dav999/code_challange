package com.dawid.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserList {

    private static UserList userList;
    List<String> userNameList;
    Map<String, List<String>> followersList;

    public static UserList getInstance(){
        if(userList == null){
            userList = new UserList();
        }
        return userList;
    }

    private UserList() {
        userNameList = new ArrayList<>();
        followersList = new HashMap<>();
    }

    public List<String> getUserNameList() {
        return userNameList;
    }

    public Map<String, List<String>> getFollowersList() {
        return followersList;
    }
}
