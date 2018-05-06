package com.parcel.distribution.webapp.edit_profile.service;

import com.parcel.distribution.webapp.edit_profile.form.ChangePasswordForm;
import com.parcel.distribution.webapp.edit_profile.form.EditProfileForm;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

public interface EditProfileService {

    ModelAndView editProfile(Principal principal, EditProfileForm editProfileForm);

    ModelAndView editProfile(Principal principal, EditProfileForm editProfileForm, BindingResult bindingResult);

    ModelAndView changePasswordForm(Principal principal, ChangePasswordForm changePasswordForm);

    ModelAndView changePasswordForm(Principal principal, ChangePasswordForm changePasswordForm, BindingResult bindingResult);
}
