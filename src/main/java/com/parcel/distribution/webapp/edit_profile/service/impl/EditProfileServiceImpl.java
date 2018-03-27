package com.parcel.distribution.webapp.edit_profile.service.impl;

import com.parcel.distribution.db.entity.Address;
import com.parcel.distribution.db.entity.User;
import com.parcel.distribution.db.repository.AddressRepository;
import com.parcel.distribution.db.repository.UserRepository;
import com.parcel.distribution.webapp.edit_profile.form.AddressForm;
import com.parcel.distribution.webapp.edit_profile.form.FirstForm;
import com.parcel.distribution.webapp.edit_profile.service.EditProfileService;
import com.parcel.distribution.webapp.edit_profile.validator.AddressFormValidator;
import com.parcel.distribution.webapp.edit_profile.validator.FirstFormValidator;
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
    private static final String EDIT_ADDRESS_VIEW_JSP = "edit_profile/edit_address";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private FirstFormValidator firstFormValidator;

    @Autowired
    private AddressFormValidator addressFormValidator;


    @Override
    public ModelAndView editProfileFirstGet(Principal principal, FirstForm firstForm) {
        ModelAndView modelAndView = new ModelAndView(EDIT_PROFILE_VIEW_JSP);

        if (firstForm.getName() == null){
            User user = userRepository.findByLogin(principal.getName());
            firstForm.setName(user.getName());
            firstForm.setSurname(user.getSurname());
            firstForm.setPhoneNumber(user.getPhoneNumber());
        }
        modelAndView.addObject("firstForm", firstForm);
        return modelAndView;
    }

    @Override
    public ModelAndView editProfileFirstPost(Principal principal, FirstForm firstForm, BindingResult bindingResult) {
        firstFormValidator.validate(firstForm, bindingResult);
        if (bindingResult.hasErrors()){
            return editProfileFirstGet(principal, firstForm);
        }else{
            ModelAndView modelAndView = new ModelAndView(EDIT_ADDRESS_VIEW_JSP);
            User user = userRepository.findByLogin(principal.getName());
            user.setName(firstForm.getName());
            user.setSurname(firstForm.getSurname());
            user.setPhoneNumber(firstForm.getPhoneNumber());
            userRepository.save(user);
            modelAndView.addObject("addressForm", new AddressForm());
            return modelAndView;
        }
    }

    @Override
    public ModelAndView editAddressGet(Principal principal, AddressForm addressForm) {
        ModelAndView modelAndView = new ModelAndView(EDIT_ADDRESS_VIEW_JSP);

        if(addressForm.getStreet() == null){
            User user = userRepository.findByLogin(principal.getName());
            Address address = addressRepository.findByUser(user);
            addressForm.setCity(address.getCity());
            addressForm.setPostCode(address.getPostCode());
            addressForm.setStreet(address.getStreet());
            addressForm.setStreetNumber(address.getStreetNumber());
            addressForm.setFlatNumber(address.getFlatNumber());
        }

        modelAndView.addObject("addressForm", addressForm);
        return modelAndView;
    }

    @Override
    public ModelAndView editAddressPost(Principal principal, AddressForm addressForm, BindingResult bindingResult) {
        addressFormValidator.validate(addressForm, bindingResult);
        if (bindingResult.hasErrors()){
            return editAddressGet(principal, addressForm);
        }else{
            User user = userRepository.findByLogin(principal.getName());
            Address address = new Address();
            address.setStreet(addressForm.getStreet());
            address.setStreetNumber(addressForm.getStreetNumber());
            address.setFlatNumber(addressForm.getFlatNumber());
            address.setPostCode(addressForm.getPostCode());
            address.setCity(addressForm.getCity());
            address.setUser(user);
            addressRepository.save(address);
            ModelAndView modelAndView = new ModelAndView(EDIT_ADDRESS_VIEW_JSP);
            return modelAndView;
        }
    }
}
