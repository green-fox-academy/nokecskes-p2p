package com.greenfox.models;

import javax.persistence.Entity;

/**
 * Created by Connor on 2017.05.22..
 */
public class StatusOk {

  private String status;

  public StatusOk() {
    this.status = "ok";
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
