package com.parcel.distribution.webapp.registration.controller;

import com.parcel.distribution.webapp.registration.form.UserForm;
import com.parcel.distribution.webapp.registration.service.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/parcel/distribution")
public class RegistrationController {

    @Autowired
    private RegistrationService service;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration(HttpServletRequest request, HttpServletResponse response, UserForm userForm) {
        return service.registration(request, response, userForm);
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registration(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("userForm") UserForm userForm, BindingResult bindingResult, Model model) throws IOException {
        return service.registration(request, response, userForm, bindingResult, model);
    }
}
