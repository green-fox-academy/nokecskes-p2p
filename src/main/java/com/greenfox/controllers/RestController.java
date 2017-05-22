package com.greenfox.controllers;

import com.greenfox.models.MessageStatus;
import com.greenfox.models.ReceivedMessage;
import com.greenfox.models.User;
import com.greenfox.models.UserMessage;
import com.greenfox.repository.UserMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by Connor on 2017.05.22..
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {

  @Autowired
  UserMessageRepository userMessageRepository;

  @CrossOrigin("*")
  @PostMapping("/api/message/receive")
  public MessageStatus receiveMessage(@RequestBody ReceivedMessage receivedMessage) {
    userMessageRepository.save(receivedMessage.getMessage());
    return new MessageStatus();
  }

}


