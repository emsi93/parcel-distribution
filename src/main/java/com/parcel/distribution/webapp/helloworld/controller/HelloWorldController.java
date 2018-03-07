package com.parcel.distribution.webapp.helloworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/parcel/distribution/content")
public class HelloWorldController {

    @RequestMapping("/index")
    public ModelAndView helloWorld(){
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }
}
