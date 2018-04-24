package com.parcel.distribution.webapp.helloworld.controller;

import com.parcel.distribution.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class HelloWorldController {

    private final String HELLO_WORLD_VIEW_JSP = "helloWorld";
    private final String ABOUT_VIEW_JSP = "about";
    private final String CONTACT_VIEW_JSP = "contact";
    private final String INDEX_VIEW_JSP = "index";

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/parcel/distribution/content/index")
    public ModelAndView index(Principal principal) {
        ModelAndView modelAndView = new ModelAndView(INDEX_VIEW_JSP);
        String role = userRepository.findByLogin(principal.getName()).getRole();
        modelAndView.addObject("role", role);
        modelAndView.addObject("username", principal.getName());
        return modelAndView;
    }

    @RequestMapping("/")
    public ModelAndView helloWorld() {
        ModelAndView modelAndView = new ModelAndView(HELLO_WORLD_VIEW_JSP);
        return modelAndView;
    }

    @RequestMapping("/about")
    public ModelAndView about() {
        ModelAndView modelAndView = new ModelAndView(ABOUT_VIEW_JSP);
        return modelAndView;
    }

    @RequestMapping("/contact")
    public ModelAndView contact() {
        ModelAndView modelAndView = new ModelAndView(CONTACT_VIEW_JSP);
        return modelAndView;
    }

}
