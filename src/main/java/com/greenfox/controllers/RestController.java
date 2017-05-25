package com.greenfox.controllers;

import com.greenfox.models.MessagePacket;
import com.greenfox.service.MessageService;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Connor on 2017.05.22..
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {

  @Autowired
  MessageService messageService;

  @CrossOrigin("*")
  @PostMapping("/api/message/receive")
  public ResponseEntity receiveMessage(@RequestBody MessagePacket messagePacket) {
    return messageService.checkReceivedMessage(messagePacket);
  }

  @PostMapping("/addmessage")
  public void addNewMessage(@RequestParam String newMessage, HttpServletResponse response)
      throws IOException {
    messageService.addNewMessage(newMessage);
    response.sendRedirect("/");
  }

}


