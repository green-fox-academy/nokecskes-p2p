package com.greenfox.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.servlet.http.HttpServletRequest;
import org.apache.tomcat.jni.Local;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

/**
 * Created by Connor on 2017.05.17..
 */
public class LogMessage {

  String path;
  String method;
  LocalDate date;
  LocalTime time;
  String logLevel;
  String requestData;

  public LogMessage(HttpServletRequest request) {
    this.path = request.getRequestURI();
    this.method = request.getMethod();
    this.date = LocalDate.now();
    this.time = LocalTime.now();
    this.logLevel = System.getenv("CHAT_APP_LOGLEVEL");
    this.requestData = request.getQueryString();
  }

  @Override
  public String toString() {
    return String.format("%s %s %s Request %s %s %s", date, time, logLevel, path, method, requestData);
  }
}


