package com.parcel.distribution.webapp.activation.controller;

import com.parcel.distribution.webapp.activation.service.ActivationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/parcel/distribution/activeuser")
public class ActivationController {


    @Autowired
    private ActivationService activationService;

    @RequestMapping("/active")
    public ModelAndView activeUser(HttpServletRequest request, HttpServletResponse response) {
        return activationService.activeUser(request, response);
    }

}
