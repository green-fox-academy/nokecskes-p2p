package com.greenfox.controllers;

import com.greenfox.models.LogMessage;
import com.greenfox.models.User;
import com.greenfox.models.UserMessage;
import com.greenfox.repository.UserMessageRepository;
import com.greenfox.repository.UserRepository;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Connor on 2017.05.17..
 */
@Controller
public class MainController {

  @Autowired
  UserRepository userRepository;

  @Autowired
  UserMessageRepository userMessageRepository;

  @ModelAttribute
  public void createLogMessage(HttpServletRequest request) {
    LogMessage logMessage = new LogMessage(request);
    System.out.println(logMessage);
  }

  @GetMapping("/")
  public String getIndexPage(Model model) {
    if (userRepository.findOne(1l) == null) {
      return "redirect:/enter";
    } else {
      model.addAttribute("messages", userMessageRepository.findAll());
      return "index";
    }
  }

  @GetMapping("/enter")
  public String enterUser(Model model) {
    if (userRepository.findOne(1l) == null) {
      model.addAttribute("user", new User());
      return "enter";
    } else {
      return "redirect:/";
    }
  }

  @PostMapping("/entering")
  public String saveNewUser(@RequestParam String userName) {
    userRepository.save(new User(userName));
    return "redirect:/";
  }

  @PostMapping("/updating")
  public String updateUser(@RequestParam String newUserName) {
    User user = userRepository.findOne(1l);
    user.setUserName(newUserName);
    userRepository.save(user);
    return "redirect:/";
  }

  @PostMapping("/addingnewmessage")
  public String addNewMessage(@RequestParam String newMessage) {
    UserMessage userMessage = new UserMessage(userRepository.findOne(1l).getUserName(), newMessage);
    userMessageRepository.save(userMessage);
    return "redirect:/";
  }
}
