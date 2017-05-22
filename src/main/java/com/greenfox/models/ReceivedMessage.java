package com.greenfox.models;

/**
 * Created by Connor on 2017.05.22..
 */
public class ReceivedMessage {

  private UserMessage message;
  private Client client;

  public ReceivedMessage() {
  }

  public UserMessage getMessage() {
    return message;
  }

  public void setMessage(UserMessage message) {
    this.message = message;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }
}
