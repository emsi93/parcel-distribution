package com.parcel.distribution.webapp.contacts.controller;

import com.parcel.distribution.webapp.contacts.form.ContactForm;
import com.parcel.distribution.webapp.contacts.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/parcel/distribution/contacts")
public class ContactsController {

    @Autowired
    private ContactsService service;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView contactsList(Principal principal){
       return service.contactsList(principal);
    }

    @RequestMapping(value = "newcontact", method = RequestMethod.GET)
    public ModelAndView newContact(Principal principal, ContactForm contactForm){
        return service.newContact(principal, contactForm);
    }

    @RequestMapping(value = "newcontact", method = RequestMethod.POST   )
    public ModelAndView newContact(Principal principal, ContactForm contactForm,  BindingResult bindingResult){
      return service.newContact(principal, contactForm, bindingResult);
    }

}
