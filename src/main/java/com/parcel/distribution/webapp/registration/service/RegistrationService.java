package com.parcel.distribution.webapp.registration.service;

import com.parcel.distribution.webapp.registration.form.UserForm;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface RegistrationService {

    ModelAndView registration(HttpServletRequest request, HttpServletResponse response, UserForm userForm);

    ModelAndView registration(HttpServletRequest request, HttpServletResponse response, UserForm userForm, BindingResult bindingResult, Model model) throws IOException;
}
