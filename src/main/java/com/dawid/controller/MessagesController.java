package com.dawid.controller;

import com.dawid.request.RequestAddMessage;
import com.dawid.response.ResponseMessage;
import com.dawid.service.MessageService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class MessagesController {

    private MessageService messageService;

    @Autowired
    public MessagesController(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(value = "/addMessage", method = RequestMethod.POST)
    public ResponseMessage addMessage(@RequestBody RequestAddMessage requestAddMessage) {
        return messageService.addMessage(requestAddMessage);
    }

    @ResponseBody
    @RequestMapping(value = "/getMessages/{userName}", method = RequestMethod.GET)
    public String addMessage(@PathVariable String userName) {
        return new Gson().toJson(messageService.getUserMessage(userName));
    }

    @ResponseBody
    @RequestMapping(value = "/getTimeline/{userName}", method = RequestMethod.GET)
    public String getTimeline(@PathVariable String userName) {
        return new Gson().toJson(messageService.getTimeline(userName));
    }

}