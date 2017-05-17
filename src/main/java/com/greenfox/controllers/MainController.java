package com.greenfox.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Connor on 2017.05.17..
 */
@Controller
public class MainController {

  @GetMapping("/")
  public String getIndexPage() {
    return "index";
  }

}
