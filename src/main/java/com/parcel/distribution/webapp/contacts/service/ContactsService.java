package com.parcel.distribution.webapp.contacts.service;

import com.parcel.distribution.webapp.contacts.form.ContactForm;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

public interface ContactsService {

    ModelAndView contactsList(Principal principal);

    ModelAndView newContact(Principal principal, ContactForm contactForm);

    ModelAndView newContact(Principal principal, ContactForm contactForm, BindingResult bindingResult);
}
