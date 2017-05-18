package com.greenfox.controllers;

import com.greenfox.models.LogMessage;
import com.greenfox.models.User;
import com.greenfox.repository.UserRepository;
import com.greenfox.service.UserService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Connor on 2017.05.17..
 */
@Controller
public class MainController {

  @Autowired
  UserRepository userRepository;

  @Autowired
  ModelAndView modelAndView;

  @Autowired
  UserService userService;

  @ModelAttribute
  public void createLogMessage(HttpServletRequest request) {
    LogMessage logMessage = new LogMessage(request);
    System.out.println(logMessage);
  }

  @GetMapping("/")
  public String getIndexPage() {
    if(userRepository.findAll() == null) {
      return "redirect:/enter";
    } else {
      return "index";
    }
  }

  @GetMapping("/enter")
  public ModelAndView enterUser() {
    modelAndView.addObject("user", new User());
    modelAndView.setViewName("enter");
    return modelAndView;
  }

  @PostMapping("/entering")
  public String saveNewUser(@RequestParam String userName) {
    userRepository.save(new User(userName));
    return "redirect:/";
  }

  @PostMapping("/updating")
  public String updateUser(@RequestParam String newUserName) {
    userRepository.findById(0l).setUserName(newUserName);
    return "redirect:/";
  }
}
