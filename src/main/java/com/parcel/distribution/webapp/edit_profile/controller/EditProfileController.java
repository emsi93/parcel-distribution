package com.parcel.distribution.webapp.edit_profile.controller;


import com.parcel.distribution.webapp.edit_profile.form.EditProfileForm;
import com.parcel.distribution.webapp.edit_profile.service.EditProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/parcel/distribution/editprofile")
public class EditProfileController {

    @Autowired
    private EditProfileService service;

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView editProfile(Principal principal, EditProfileForm editProfileForm) {
        return service.editProfile(principal, editProfileForm);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editProfile(Principal principal, EditProfileForm editProfileForm, BindingResult bindingResult) {
        return service.editProfile(principal, editProfileForm, bindingResult);
    }
}
