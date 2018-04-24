package com.parcel.distribution.webapp.parcel.controller;

import com.parcel.distribution.webapp.parcel.form.ParcelForm;
import com.parcel.distribution.webapp.parcel.service.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
}
