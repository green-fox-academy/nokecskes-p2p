package com.greenfox.models;

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
@Getter
@Setter
@Entity
public class UserMessage {

  @Id
  private Long id;

  private String userName;
  private String text;

  public UserMessage() {
  }

  public UserMessage(String userName, String text) {
    this.userName = userName;
    this.text = text;
    this.id = generateRandomId();
  }

  public Long generateRandomId() {
    int random = 1000000 + (int) (Math.random() * 9999999);
    return Long.valueOf((long) random);
  }

}

