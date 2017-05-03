package com.dawid.controller;

import com.dawid.request.RequestFollowUser;
import com.dawid.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public String getUserList() {
        return new Gson().toJson(userService.getListOfUsers());
    }

    @RequestMapping(value = "/followUser", method = RequestMethod.POST)
    public String followUser(@RequestBody RequestFollowUser requestFollowUser) {
        return new Gson().toJson(userService.followUser(requestFollowUser.getUser(), requestFollowUser.getFollowedUser()));
    }

}
