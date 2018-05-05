package com.parcel.distribution.webapp.registration.service.impl;

import com.parcel.distribution.configuration.Config;
import com.parcel.distribution.db.entity.User;
import com.parcel.distribution.db.repository.UserRepository;
import com.parcel.distribution.webapp.email.email.EmailActivation;
import com.parcel.distribution.webapp.email.service.EmailService;
import com.parcel.distribution.webapp.registration.form.UserForm;
import com.parcel.distribution.webapp.registration.service.RegistrationService;
import com.parcel.distribution.webapp.registration.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;

@Slf4j
@Service
@Transactional
@ConfigurationProperties(prefix = "application")
public class RegistrationServiceImpl implements RegistrationService {

    private static final String REGISTRATION_VIEW_JSP = "registration/registration_view";
    private static final String REGISTRATION_SUCCESS_VIEW_JSP = "registration/registration_success";

    @Value("${host}")
    private String host;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public ModelAndView registration(HttpServletRequest request, HttpServletResponse response, UserForm userForm) {
        ModelAndView modelAndView = new ModelAndView(REGISTRATION_VIEW_JSP);
        modelAndView.addObject("userForm", userForm);
        return modelAndView;
    }

    @Override
    public ModelAndView registration(HttpServletRequest request, HttpServletResponse response, UserForm userForm, BindingResult bindingResult, Model model) throws IOException {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return registration(request, response, userForm);
        } else{
            User user = new User();
            user.setActive(true);
            user.setRole("ROLE_USER");
            user.setPassword(userForm.getPassword());
            user.setLogin(userForm.getLogin());
            user.setEmail(userForm.getEmail());
            user.setPassword(userForm.getPassword());
            userRepository.save(user);
            //emailService.sendEmail(new EmailActivation(user.getEmail(), host));
            return registrationSuccess();
        }
    }

    public ModelAndView registrationSuccess(){
        return new ModelAndView(REGISTRATION_SUCCESS_VIEW_JSP);
    }
}
