package com.parcel.distribution.webapp.error.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/parcel/distribution/error")
public class ErrorController {

    @RequestMapping("failureLogin")
    public ModelAndView failureLogin() {
        return new ModelAndView("error/failureLogin_view");
    }

    @RequestMapping("accessDenied")
    public ModelAndView accessDenied() {
        return new ModelAndView("error/accessDenied_view");
    }
}
