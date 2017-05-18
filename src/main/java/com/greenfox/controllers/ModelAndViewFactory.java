package com.greenfox.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Connor on 2017.05.17..
 */
@Configuration
public class ModelAndViewFactory {

  @Bean
  public ModelAndView getModelAndView() {
    return new ModelAndView();
  }

}
