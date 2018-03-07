package com.parcel.distribution.webapp.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/parcel/distribution")
public class SecurityController {

    @RequestMapping("login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login/login_view");
        return modelAndView;
    }


    @RequestMapping("logout")
    public ModelAndView logout() {
        return new ModelAndView("logout/logout_view");
    }
}
