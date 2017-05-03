package com.dawid.service;

import com.dawid.model.MessageWall;
import com.dawid.model.UserList;
import com.dawid.response.ResponseMessage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {

    public List<String> getListOfUsers(){
        return UserList.getInstance().getUserNameList();
    }

    public ResponseMessage followUser(String whoFollow, String followedUser){
        if(!validUser(whoFollow) || !validUser(followedUser)) {
            return new ResponseMessage(false, "User not exist");
        }

        if(UserList.getInstance().getFollowersList().containsKey(whoFollow)){
            UserList.getInstance().getFollowersList().get(whoFollow).add(followedUser);
        } else {
            List<String> usersFollowedList = new ArrayList<>();
            usersFollowedList.add(followedUser);
            UserList.getInstance().getFollowersList().put(whoFollow, usersFollowedList);
        }
        return new ResponseMessage(true, "User " + whoFollow + " is following now " + followedUser);
    }

    private boolean validUser(String user) {
        return UserList.getInstance().getUserNameList().contains(user);
    }
}
