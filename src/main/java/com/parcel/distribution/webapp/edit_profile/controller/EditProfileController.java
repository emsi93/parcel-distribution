package com.parcel.distribution.webapp.edit_profile.controller;


import com.parcel.distribution.webapp.edit_profile.form.AddressForm;
import com.parcel.distribution.webapp.edit_profile.form.FirstForm;
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
    public ModelAndView editProfileFirstGet(Principal principal, FirstForm firstForm) {
        return service.editProfileFirstGet(principal, firstForm);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editProfileFirstPost(Principal principal, FirstForm firstForm, BindingResult bindingResult) {
        return service.editProfileFirstPost(principal, firstForm, bindingResult);
    }

    @RequestMapping(value = "/edit_address", method = RequestMethod.GET)
    public ModelAndView editAddressGet(Principal principal, AddressForm addressForm) {
        return service.editAddressGet(principal, addressForm);
    }

    @RequestMapping(value = "/edit_address", method = RequestMethod.POST)
    public ModelAndView editAddressPost(Principal principal, AddressForm addressForm, BindingResult bindingResult) {
        return service.editAddressPost(principal, addressForm, bindingResult);
    }
}
