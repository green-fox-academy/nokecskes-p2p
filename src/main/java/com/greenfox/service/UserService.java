package com.greenfox.service;

import com.greenfox.models.User;
import com.greenfox.repository.UserRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Connor on 2017.05.17..
 */
@Component
public class UserService {

  @Autowired
  UserRepository userRepository;

  public String setMainPage(){
    if (userRepository.findOne(1l) == null) {
      return "redirect:/enter";
    } else {
      return "index";
    }
  }




}
