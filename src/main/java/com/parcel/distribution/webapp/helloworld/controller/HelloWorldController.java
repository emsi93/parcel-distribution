package com.parcel.distribution.webapp.helloworld.controller;

import com.parcel.distribution.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class HelloWorldController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/parcel/distribution/content/index")
    public ModelAndView index(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("index");
        String role = userRepository.findByLogin(principal.getName()).getRole();
        modelAndView.addObject("role", role);
        return modelAndView;
    }

    @RequestMapping("/")
    public ModelAndView helloWorld() {
        ModelAndView modelAndView = new ModelAndView("helloWorld");
        return modelAndView;
    }

}
