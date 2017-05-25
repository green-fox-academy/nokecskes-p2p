package com.greenfox.models;

/**
 * Created by K on 2017.05.25..
 */
public class StatusError {

  private String status;
  private String message;

  public StatusError(String missingFields) {
    this.status = "error";
    this.message = "Missing field(s): " + missingFields;
  }
}
