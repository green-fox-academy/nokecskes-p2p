package com.greenfox.models;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Connor on 2017.05.18..
 */

@Entity
public class UserMessage {

  @Id
  private Long id;

  private String username;
  private String text;
  private Timestamp timestamp;

  public UserMessage() {
    this.timestamp = new Timestamp(System.currentTimeMillis());
  }

  public UserMessage(String username, String text) {
    this.id = generateRandomId();
    this.username = username;
    this.text = text;
    this.timestamp = new Timestamp(System.currentTimeMillis());
  }

  public Long generateRandomId() {
    int random = 1000000 + (int) (Math.random() * 9999999);
    return Long.valueOf((long)random);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }
}

