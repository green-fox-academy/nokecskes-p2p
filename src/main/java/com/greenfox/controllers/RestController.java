package com.greenfox.controllers;

import com.greenfox.models.MessageStatus;
import com.greenfox.models.MessagePacket;
import com.greenfox.models.UserMessage;
import com.greenfox.repository.UserMessageRepository;
import com.greenfox.repository.UserRepository;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
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

  RestTemplate restTemplate = new RestTemplate();

  @CrossOrigin("*")
  @PostMapping("/api/message/receive")
  public MessageStatus receiveMessage(@RequestBody MessagePacket messagePacket) {
    if (!messagePacket.getClient().getId().equals(System.getenv("CHAT_APP_UNIQUE_ID"))) {
      userMessageRepository.save(messagePacket.getMessage());
      restTemplate.postForObject(System.getenv("CHAT_APP_PEER_ADDRESS"), messagePacket, MessagePacket.class);
    }
    return new MessageStatus();
  }

  @PostMapping("/addmessage")
  public void addNewMessage(@RequestParam String newMessage, HttpServletResponse response) throws IOException {
    UserMessage userMessage = new UserMessage(userRepository.findOne(1l).getUsername(), newMessage);
    userMessageRepository.save(userMessage);
    MessagePacket messagePacket = new MessagePacket(userMessage,
        System.getenv("CHAT_APP_UNIQUE_ID"));
    System.out.println("----> DEBUG: " + System.getenv("CHAT_APP_PEER_ADDRESS"));
    System.out.println("----> DEBUG: " + System.getenv("CHAT_APP_UNIQUE_ID"));
    try {
      restTemplate.postForObject(System.getenv("CHAT_APP_PEER_ADDRESS"), messagePacket, MessagePacket.class);
    } catch (Exception e) {
      System.out.println(e);
    }
    response.sendRedirect("/");
    //return "redirect:/";
  }

}


