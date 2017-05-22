package com.greenfox.models;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Connor on 2017.05.18..
 */

@Entity
public class UserMessage {

  @Id
  private Long id;

  private String userName;
  private String text;
  private Timestamp timestamp;

  public UserMessage() {
    this.timestamp = new Timestamp(System.currentTimeMillis());
  }

  public UserMessage(String userName, String text) {
    this.id = generateRandomId();
    this.userName = userName;
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

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
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

