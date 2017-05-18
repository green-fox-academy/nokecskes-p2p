package com.greenfox.controllers;

import com.greenfox.models.LogMessage;
import com.greenfox.models.User;
import com.greenfox.repository.UserRepository;
import com.greenfox.service.UserService;
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
  UserService userService;

  @ModelAttribute
  public void createLogMessage(HttpServletRequest request) {
    LogMessage logMessage = new LogMessage(request);
    System.out.println(logMessage);
  }

  @GetMapping("/")
  public String getIndexPage() {
    return userService.setMainPage();
  }

  @GetMapping("/enter")
  public String enterUser(Model model) {
    if(userRepository.findOne(1l) == null){
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
}
