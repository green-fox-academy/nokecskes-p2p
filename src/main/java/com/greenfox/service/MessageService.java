package com.greenfox.service;

import com.greenfox.models.MessagePacket;
import com.greenfox.models.User;
import com.greenfox.models.UserMessage;
import com.greenfox.repository.UserMessageRepository;
import com.greenfox.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by K on 2017.05.25..
 */
@Service
public class MessageService {

  @Autowired
  UserMessageRepository userMessageRepository;

  @Autowired
  UserRepository userRepository;

  public void checkReceivedMessage(MessagePacket messagePacket) {
    if (!messagePacket.getClient().getId().equals(System.getenv("CHAT_APP_UNIQUE_ID"))) {
      saveMessage(messagePacket);
      sendMessage(messagePacket);
    }
  }

  public void addNewMessage(String newMessage) {
    UserMessage userMessage = new UserMessage(userRepository.findOne(1l).getUsername(), newMessage);
    MessagePacket newMessagePacket = new MessagePacket(userMessage,
        System.getenv("CHAT_APP_UNIQUE_ID"));
    saveMessage(newMessagePacket);
    sendMessage(newMessagePacket);
  }

  public void sendMessage(MessagePacket messagePacket) {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.postForObject(System.getenv("CHAT_APP_PEER_ADDRESS"), messagePacket,
        MessagePacket.class);
  }

  public void saveMessage(MessagePacket messagePacket) {
    userMessageRepository.save(messagePacket.getMessage());
  }
}
