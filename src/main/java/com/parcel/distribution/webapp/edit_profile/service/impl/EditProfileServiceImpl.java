package com.parcel.distribution.webapp.edit_profile.service.impl;

import com.parcel.distribution.db.entity.Address;
import com.parcel.distribution.db.entity.User;
import com.parcel.distribution.db.repository.AddressRepository;
import com.parcel.distribution.db.repository.UserRepository;
import com.parcel.distribution.webapp.edit_profile.form.EditProfileForm;
import com.parcel.distribution.webapp.edit_profile.service.EditProfileService;
import com.parcel.distribution.webapp.edit_profile.validator.EditFormValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.security.Principal;

@Slf4j
@Service
@Transactional
@ConfigurationProperties(prefix = "application")
public class EditProfileServiceImpl implements EditProfileService {

    private static final String EDIT_PROFILE_VIEW_JSP = "edit_profile/edit";
    private static final String EDIT_PROFILE_SUCCESS_VIEW_JSP = "edit_profile/edit_success";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private EditFormValidator editFormValidator;

    @Override
    public ModelAndView editProfile(Principal principal, EditProfileForm editProfileForm) {
        ModelAndView modelAndView = new ModelAndView(EDIT_PROFILE_VIEW_JSP);
        String role = userRepository.findByLogin(principal.getName()).getRole();
        modelAndView.addObject("role", role);
        modelAndView.addObject("username", principal.getName());
        User user = userRepository.findByLogin(principal.getName());

        if (editProfileForm.getName() == null) {
            editProfileForm.setName(user.getName());
            editProfileForm.setSurname(user.getSurname());
            editProfileForm.setPhoneNumber(user.getPhoneNumber());
        }

        if (editProfileForm.getStreet() == null) {
            Address address = addressRepository.findByUser(user);
            if(address != null){
                editProfileForm.setCity(address.getCity());
                editProfileForm.setPostCode(address.getPostCode());
                editProfileForm.setStreet(address.getStreet());
                editProfileForm.setStreetNumber(address.getStreetNumber());
                editProfileForm.setFlatNumber(address.getFlatNumber());
            }
        }
        modelAndView.addObject("editProfileForm", editProfileForm);
        return modelAndView;
    }

    @Override
    public ModelAndView editProfile(Principal principal, EditProfileForm editProfileForm, BindingResult bindingResult) {
        editFormValidator.validate(editProfileForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return editProfile(principal, editProfileForm);
        } else {
            User user = userRepository.findByLogin(principal.getName());
            user.setName(editProfileForm.getName());
            user.setSurname(editProfileForm.getSurname());
            user.setPhoneNumber(editProfileForm.getPhoneNumber());

            Address address = createAddress(user, editProfileForm);

            userRepository.save(user);
            addressRepository.save(address);
            return editProfileSuccess(principal);
        }
    }

    private ModelAndView editProfileSuccess(Principal principal){
        ModelAndView modelAndView = new ModelAndView(EDIT_PROFILE_SUCCESS_VIEW_JSP);
        String role = userRepository.findByLogin(principal.getName()).getRole();
        modelAndView.addObject("role", role);
        modelAndView.addObject("username", principal.getName());
        return modelAndView;
    }

    private Address createAddress(User user, EditProfileForm editProfileForm) {
        Address address = addressRepository.findByUser(user);
        if(address == null){
            address = new Address();
            address.setUser(user);
        }

        address.setStreet(editProfileForm.getStreet());
        address.setStreetNumber(editProfileForm.getStreetNumber());
        address.setFlatNumber(editProfileForm.getFlatNumber());
        address.setPostCode(editProfileForm.getPostCode());
        address.setCity(editProfileForm.getCity());
        return address;
    }
}
