package com.greenfox.controllers;

import com.greenfox.models.MessageStatus;
import com.greenfox.models.User;
import com.greenfox.models.UserMessage;
import com.greenfox.repository.UserMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Connor on 2017.05.22..
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {

  @Autowired
  UserMessageRepository userMessageRepository;

  @CrossOrigin("*")
  @RequestMapping("/api/message/receive")
  public MessageStatus receiveMessage(@RequestBody UserMessage userMessage, User user) {
    userMessageRepository.save(userMessage);
    return new MessageStatus();
  }

}

/*

  String url = "https://limitless-thicket-98020.herokuapp.com/posts";
  RestTemplate restTemplate = new RestTemplate();

  @GetMapping("/add")
  public Post index(
          @RequestParam(name = "title") String title,
          @RequestParam(name = "href") String href) {

    Post p = new Post(title, href);

    Post newPost = restTemplate.postForObject(url, p, Post.class);
    return newPost;
  }*/
