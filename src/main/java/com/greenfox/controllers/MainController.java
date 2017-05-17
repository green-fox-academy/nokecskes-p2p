package com.greenfox.controllers;

import com.greenfox.models.LogMessage;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by Connor on 2017.05.17..
 */
@Controller
public class MainController {

  @ModelAttribute
  public void createLogMessage(HttpServletRequest request) {
    LogMessage logMessage = new LogMessage(request);
    System.out.println(logMessage);
  }

  @GetMapping("/")
  public String getIndexPage() {
    return "index";
  }

}
