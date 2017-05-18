package com.greenfox.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Connor on 2017.05.17..
 */
@Entity
@Getter
@Setter
@Table(name = "usertable")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  private String userName;

  public User() {
    this.userName = "unknown";
  }

  public User(String userName) {
    this.userName = userName;
  }
}
