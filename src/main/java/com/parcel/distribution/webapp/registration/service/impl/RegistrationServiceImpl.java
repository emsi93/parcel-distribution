package com.parcel.distribution.webapp.registration.service.impl;

import com.parcel.distribution.configuration.CaptchaConfig;
import com.parcel.distribution.configuration.Config;
import com.parcel.distribution.db.entity.User;
import com.parcel.distribution.db.repository.UserRepository;
import com.parcel.distribution.webapp.registration.form.UserForm;
import com.parcel.distribution.webapp.registration.service.RegistrationService;
import com.parcel.distribution.webapp.registration.validator.CaptchaValidator;
import com.parcel.distribution.webapp.registration.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CaptchaConfig captchaConfig;

    @Autowired
    private CaptchaValidator captchaValidator;

    @Override
    public ModelAndView registration(HttpServletRequest request, HttpServletResponse response, UserForm userForm) {
        ModelAndView modelAndView = new ModelAndView(REGISTRATION_VIEW_JSP);
        modelAndView.addObject("userForm", userForm);
        modelAndView.addObject("recaptchaUrl", returnRecaptchaUrl(request));
        modelAndView.addObject("recaptchaSiteKey", captchaConfig.getSiteKey());
        return modelAndView;
    }

    @Override
    public ModelAndView registration(HttpServletRequest request, HttpServletResponse response, UserForm userForm, BindingResult bindingResult, Model model) throws IOException {
        userValidator.validate(userForm, bindingResult);
        boolean validCaptcha = captchaValidator.verify(request.getParameter(Config.RECAPTCHA_PARAM));
        if (bindingResult.hasErrors() || !validCaptcha) {
            return registration(request, response, userForm);
        }

        User user = new User();
        user.setActive(true);
        user.setRole("ROLE_ADMIN");
        user.setPassword(userForm.getPassword());
        user.setLogin(userForm.getLogin());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        userRepository.save(user);
        return registration(request, response, new UserForm(null, null, null, null));
    }

    private String returnRecaptchaUrl(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        String lang = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("my-locale-cookie")) {
                    lang = cookie.getValue();
                    break;
                }
            }
        }
        return returnRecaptchaUrl(lang);
    }

    private String returnRecaptchaUrl(String lang) {
        switch (lang) {
            case "pl":
                return Config.RECAPTCHA_URL_PL;
            case "en":
                return Config.RECAPTCHA_URL_EN;
            default:
                return Config.RECAPTCHA_URL_PL;
        }
    }

}
