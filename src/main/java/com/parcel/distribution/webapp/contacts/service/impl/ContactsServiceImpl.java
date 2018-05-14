package com.parcel.distribution.webapp.contacts.service.impl;


import com.parcel.distribution.db.entity.Recipient;
import com.parcel.distribution.db.entity.User;
import com.parcel.distribution.db.repository.RecipientRepository;
import com.parcel.distribution.db.repository.UserRepository;
import com.parcel.distribution.webapp.contacts.form.ContactForm;
import com.parcel.distribution.webapp.contacts.service.ContactsService;
import com.parcel.distribution.webapp.contacts.validator.ContactFormValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;

@Slf4j
@Service
@Transactional
@ConfigurationProperties(prefix = "application")
public class ContactsServiceImpl implements ContactsService{

    private static final String CONTACTS_LIST_VIEW_JSP = "contacts/contactslist";
    private static final String NEW_CONTACT_VIEW_JSP = "contacts/newcontact";
    private static final String NEW_CONTACT_SUCCESS_VIEW_JSP = "contacts/addcontact_success";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipientRepository recipientRepository;

    @Autowired
    private ContactFormValidator contactFormValidator;

    @Override
    public ModelAndView contactsList(Principal principal){
        ModelAndView modelAndView = new ModelAndView(CONTACTS_LIST_VIEW_JSP);
        User user = userRepository.findByLogin(principal.getName());
        modelAndView.addObject("role", user.getRole());
        modelAndView.addObject("username", principal.getName());
        List<Recipient> recipientList = recipientRepository.findAllByUser(user);
        modelAndView.addObject("recipientList", recipientList);
        return modelAndView;
    }

    @Override
    public ModelAndView newContact(Principal principal, ContactForm contactForm){
        ModelAndView modelAndView = new ModelAndView(NEW_CONTACT_VIEW_JSP);
        String role = userRepository.findByLogin(principal.getName()).getRole();
        modelAndView.addObject("role", role);
        modelAndView.addObject("username", principal.getName());
        modelAndView.addObject("contactForm", contactForm);
        return modelAndView;
    }

    @Override
    public ModelAndView newContact(Principal principal, ContactForm contactForm,  BindingResult bindingResult){
        contactFormValidator.validate(contactForm, bindingResult);

        if(bindingResult.hasErrors()){
            return newContact(principal, contactForm);
        }else{
            User user = userRepository.findByLogin(principal.getName());
            Recipient recipient = buildRecipient(contactForm);
            recipient.setUser(user);
            recipientRepository.save(recipient);
            return newContactSuccess(principal);
        }
    }



    private ModelAndView newContactSuccess(Principal principal){
        ModelAndView modelAndView = new ModelAndView(NEW_CONTACT_SUCCESS_VIEW_JSP);
        String role = userRepository.findByLogin(principal.getName()).getRole();
        modelAndView.addObject("role", role);
        modelAndView.addObject("username", principal.getName());
        return modelAndView;
    }

    private Recipient buildRecipient(ContactForm contactForm){
        Recipient recipient = new Recipient();
        recipient.setName(contactForm.getName());
        recipient.setSurname(contactForm.getSurname());
        recipient.setPhoneNumber(contactForm.getPhoneNumber());
        recipient.setEmail(contactForm.getEmail());

        recipient.setStreet(contactForm.getStreet());
        recipient.setStreetNumber(contactForm.getStreetNumber());
        recipient.setFlatNumber(contactForm.getFlatNumber());
        recipient.setPostCode(contactForm.getPostCode());
        recipient.setCity(contactForm.getCity());
        return recipient;
    }

}
