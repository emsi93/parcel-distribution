package com.parcel.distribution.webapp.parcel.controller;

import com.parcel.distribution.webapp.parcel.form.DescriptionForm;
import com.parcel.distribution.webapp.parcel.form.ParcelForm;
import com.parcel.distribution.webapp.parcel.service.ParcelService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/parcel/distribution/parcel")
public class ParcelController {

    @Autowired
    private ParcelService parcelService;

    @RequestMapping(value = "/newparcel", method = RequestMethod.GET)
    public ModelAndView newParcel(Principal principal, ParcelForm parcelForm) {
        return parcelService.newParcel(principal, parcelForm);
    }

    @RequestMapping(value = "/newparcel", method = RequestMethod.POST)
    public ModelAndView newParcel(Principal principal, ParcelForm parcelForm, BindingResult bindingResult) {
        return parcelService.newParcel(principal, parcelForm, bindingResult);
    }

    @RequestMapping(value = "/newparcel/{id}", method = RequestMethod.GET)
    public ModelAndView newParcelWithContact(@PathVariable("id") int idRecipient, Principal principal, DescriptionForm descriptionForm) {
        return parcelService.newParcelWithContact(idRecipient, principal, descriptionForm);
    }

    @RequestMapping(value = "/newparcel/{id}", method = RequestMethod.POST)
    public ModelAndView newParcelWithContact(@PathVariable("id") int idRecipient, Principal principal,  DescriptionForm descriptionForm, BindingResult bindingResult) {
        return parcelService.newParcelWithContact(idRecipient, principal, descriptionForm, bindingResult);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView getParcelList(Principal principal){
        return parcelService.getParcelList(principal);
    }

    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public ModelAndView getDetails(@PathVariable("id") Integer id, Principal principal){
        return parcelService.getDetails(id, principal);
    }



}
