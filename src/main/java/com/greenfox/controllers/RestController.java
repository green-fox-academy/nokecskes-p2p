package com.greenfox.controllers;

import com.greenfox.models.MessageStatus;
import com.greenfox.models.MessagePacket;
import com.greenfox.models.UserMessage;
import com.greenfox.repository.UserMessageRepository;
import com.greenfox.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Connor on 2017.05.22..
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {

  @Autowired
  UserMessageRepository userMessageRepository;

  @Autowired
  UserRepository userRepository;

  @CrossOrigin("*")
  @PostMapping("/api/message/receive")
  public MessageStatus receiveMessage(@RequestBody MessagePacket messagePacket) {
    userMessageRepository.save(messagePacket.getMessage());
    return new MessageStatus();
  }

  @PostMapping("/addmessage")
  public String addNewMessage(@RequestParam String newMessage) {
    UserMessage userMessage = new UserMessage(userRepository.findOne(1l).getUserName(), newMessage);
    userMessageRepository.save(userMessage);
    MessagePacket messagePacket = new MessagePacket(userMessage,
        System.getenv("CHAT_APP_UNIQUE_ID"));
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.postForObject(System.getenv("CHAT_APP_PEER_ADDRESSS"), messagePacket, MessagePacket.class);
    return "redirect:/";
  }

}


