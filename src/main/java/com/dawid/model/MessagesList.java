package com.dawid.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessagesList {

    private static MessagesList messagesList;

    Map<String, List<MessageWall>> messageList;

    public static MessagesList getInstance(){
        if(messagesList == null){
            messagesList = new MessagesList();
        }
        return messagesList;
    }

    private MessagesList() {
        messageList = new HashMap<>();
    }

    public Map<String, List<MessageWall>> getMessageList() {
        return messageList;
    }

}
