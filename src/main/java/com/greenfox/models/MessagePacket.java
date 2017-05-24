package com.greenfox.models;

/**
 * Created by Connor on 2017.05.22..
 */
public class MessagePacket {

  private UserMessage message;
  private Client client;

  public MessagePacket() {
  }

  public UserMessage getMessage() {
    return message;
  }

  public MessagePacket(UserMessage message, String client) {
    this.message = message;
    this.client = new Client(client);
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
