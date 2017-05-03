package com.dawid.service;

import com.dawid.model.MessageWall;
import com.dawid.model.MessagesList;
import com.dawid.model.UserList;
import com.dawid.request.RequestAddMessage;
import com.dawid.response.ResponseMessage;
import com.dawid.response.Timeline;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MessageService {

    public ResponseMessage addMessage(RequestAddMessage requestAddMessage){
        ResponseMessage responseMessage = validRequest(requestAddMessage);
        if(!responseMessage.isSuccess()) {
            return responseMessage;
        }

        if(MessagesList.getInstance().getMessageList().containsKey(requestAddMessage.getUser())){
            MessagesList.getInstance().getMessageList().get(requestAddMessage.getUser()).add(new MessageWall(requestAddMessage.getMessage(), new Date()));
        } else {
            UserList.getInstance().getUserNameList().add(requestAddMessage.getUser());
            List<MessageWall> messageWallsList = new ArrayList<>();
            messageWallsList.add(new MessageWall(requestAddMessage.getMessage(), new Date()));
            MessagesList.getInstance().getMessageList().put(requestAddMessage.getUser(), messageWallsList);
        }

        responseMessage.setMessage("Successful added a new message. Id:"
                +  (MessagesList.getInstance().getMessageList().get(requestAddMessage.getUser()).size()));

        return responseMessage;
    }

    public List<MessageWall> getUserMessage(String userName){
        if(userName == null || userName.isEmpty() || !MessagesList.getInstance().getMessageList().containsKey(userName))
            return Collections.emptyList();

        List<MessageWall> messageWallsList = new ArrayList<>();
        messageWallsList.addAll(MessagesList.getInstance().getMessageList().get(userName));
        Collections.reverse(messageWallsList);
        return messageWallsList;
    }

    public List<Timeline> getTimeline(String userName){
        List<String> followersList = UserList.getInstance().getFollowersList().get(userName);

        if(followersList == null || followersList.isEmpty())
            return Collections.emptyList();

        LinkedList<Timeline> timeLines = new LinkedList<Timeline>();

        for(String follower : followersList){
            List<MessageWall> messageWallsList = new ArrayList<>();
            messageWallsList.addAll(MessagesList.getInstance().getMessageList().get(follower));

            if(messageWallsList.isEmpty())
                break;

            Collections.reverse(messageWallsList);

            for(MessageWall message : messageWallsList){
                Timeline followerUserWall = new Timeline(follower, message.getMessage(), message.getDate());
                if(timeLines.isEmpty()){
                    timeLines.add(followerUserWall);
                } else if(timeLines.getFirst().getDate().before(followerUserWall.getDate())){
                    timeLines.addFirst(followerUserWall);
                } else if (timeLines.getLast().getDate().after(followerUserWall.getDate())) {
                    timeLines.addLast(followerUserWall);
                } else {
                    for (int i = 0; i < timeLines.size(); i++) {
                        if (timeLines.get(i).getDate().before(followerUserWall.getDate())) {
                            timeLines.add(i - 1, followerUserWall);
                            break;
                        }
                    }
                }
            }
        }

        return timeLines;
    }

    private ResponseMessage validRequest(RequestAddMessage requestAddMessage) {
        ResponseMessage responseMessage = new ResponseMessage(false);

        if(requestAddMessage.getMessage().length() > 140){
            responseMessage.setMessage("Message is too long");
        }

        responseMessage.setSuccess(true);
        return responseMessage;
    }

}
