package com.dawid.service;

import com.dawid.application.Application;
import com.dawid.model.MessageWall;
import com.dawid.request.RequestAddMessage;
import com.dawid.response.ResponseMessage;
import com.dawid.response.Timeline;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class MessageServiceTest {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Before
    public void setUp(){
        RequestAddMessage requestAddMessage1 = new RequestAddMessage("user1", "message1");
        RequestAddMessage requestAddMessage2 = new RequestAddMessage("user2", "message2");
        RequestAddMessage requestAddMessage3 = new RequestAddMessage("user3", "message3");

        messageService.addMessage(requestAddMessage1);
        messageService.addMessage(requestAddMessage2);
        messageService.addMessage(requestAddMessage3);

        requestAddMessage1 = new RequestAddMessage("user1", "message1_2");
        requestAddMessage2 = new RequestAddMessage("user2", "message2_2");
        requestAddMessage3 = new RequestAddMessage("user3", "message3_2");

        messageService.addMessage(requestAddMessage1);
        messageService.addMessage(requestAddMessage2);
        messageService.addMessage(requestAddMessage3);

        userService.followUser("user1", "user2");
    }

    @Test
    public void getListOfUsers(){
        List<String> responseMessage = userService.getListOfUsers();

        Assert.assertEquals(responseMessage.size(), 3);
        Assert.assertEquals(responseMessage.get(0), "user1");
        Assert.assertEquals(responseMessage.get(1), "user2");
        Assert.assertEquals(responseMessage.get(2), "user3");
    }

    @Test
    public void getTimeline() throws InterruptedException {
        List<Timeline> timelineList = messageService.getTimeline("user1");

        Assert.assertEquals(timelineList.size(), 2);
        Assert.assertEquals(timelineList.get(0).getMessage(), "message2_2");
        Assert.assertEquals(timelineList.get(0).getUser(), "user2");
        Assert.assertEquals(timelineList.get(1).getMessage(), "message2");
        Assert.assertEquals(timelineList.get(1).getUser(), "user2");
    }

    @Test
    public void followUser(){
        ResponseMessage responseMessage = userService.followUser("user2", "user3");
        Assert.assertEquals(responseMessage.isSuccess(), true);

        ResponseMessage responseMessage2 = userService.followUser("user2", "user4");
        Assert.assertEquals(responseMessage2.isSuccess(), false);
    }

    @Test
    public void getUserMessage(){
        List<MessageWall> messageWallList = messageService.getUserMessage("user1");

        Assert.assertEquals(messageWallList.size(), 2);
        Assert.assertEquals(messageWallList.get(0).getMessage(), "message1_2");
        Assert.assertEquals(messageWallList.get(1).getMessage(), "message1");
    }

    @Test
    public void addMessage(){
        RequestAddMessage requestAddMessage = new RequestAddMessage("user", "message");
        ResponseMessage responseMessage = messageService.addMessage(requestAddMessage);

        Assert.assertEquals(responseMessage.isSuccess(), true);
    }

}
