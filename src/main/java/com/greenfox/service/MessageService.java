package com.greenfox.service;

import com.greenfox.models.MessagePacket;
import com.greenfox.models.StatusError;
import com.greenfox.models.StatusOk;
import com.greenfox.models.User;
import com.greenfox.models.UserMessage;
import com.greenfox.repository.UserMessageRepository;
import com.greenfox.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sun.plugin2.message.Message;

/**
 * Created by K on 2017.05.25..
 */
@Service
public class MessageService {

  @Autowired
  UserMessageRepository userMessageRepository;

  @Autowired
  UserRepository userRepository;

  public ResponseEntity checkReceivedMessage(MessagePacket messagePacket) {
    saveIfNotMyMessage(messagePacket);
    return giveResponse(messagePacket);
  }

  public void saveIfNotMyMessage (MessagePacket messagePacket) {
    if (!isMyMessage(messagePacket.getClient().getId())){
      saveMessage(messagePacket);
      sendMessage(messagePacket);
    }
  }

  public boolean isMyMessage (String id) {
    if (id.equals(System.getenv("CHAT_APP_UNIQUE_ID"))) {
      return true;
    }
    return false;
  }

  public void saveMessage(MessagePacket messagePacket) {
    userMessageRepository.save(messagePacket.getMessage());
  }

  public void sendMessage(MessagePacket messagePacket) {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.postForObject(System.getenv("CHAT_APP_PEER_ADDRESS"), messagePacket,
        MessagePacket.class);
  }

  public ResponseEntity giveResponse (MessagePacket messagePacket) {
    String missingFields = "";
    if (messagePacket.getMessage().getId() == 0) {
      missingFields += "message.id, ";
    }
    if (messagePacket.getMessage().getUsername() == null) {
      missingFields += "message.username, ";
    }
    if (messagePacket.getMessage().getText()== null ){
      missingFields += "message.text, ";
    }
    if (messagePacket.getMessage().getTimestamp()== null ){
      missingFields += "message.timestamp, ";
    }
    if (messagePacket.getClient().getId()== null){
      missingFields += "client.id, ";
    }
    if (missingFields.equals("")) {
      return new ResponseEntity(new StatusOk(), HttpStatus.OK);
    } else {
      return new ResponseEntity(new StatusError(missingFields), HttpStatus.BAD_REQUEST);
    }
  }

  public void addNewMessage(String newMessage) {
    UserMessage userMessage = new UserMessage(userRepository.findOne(1l).getUsername(), newMessage);
    MessagePacket newMessagePacket = new MessagePacket(userMessage,
        System.getenv("CHAT_APP_UNIQUE_ID"));
    saveMessage(newMessagePacket);
    sendMessage(newMessagePacket);
  }

}
