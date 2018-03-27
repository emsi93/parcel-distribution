package com.parcel.distribution.webapp.edit_profile.service;

import com.parcel.distribution.webapp.edit_profile.form.AddressForm;
import com.parcel.distribution.webapp.edit_profile.form.FirstForm;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

public interface EditProfileService {

    ModelAndView editProfileFirstGet(Principal principal, FirstForm firstForm);

    ModelAndView editProfileFirstPost(Principal principal, FirstForm firstForm, BindingResult bindingResult);

    ModelAndView editAddressGet(Principal principal,  AddressForm addressForm);

    ModelAndView editAddressPost(Principal principal, AddressForm addressForm,  BindingResult bindingResult);
}
