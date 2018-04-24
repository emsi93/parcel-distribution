package com.parcel.distribution.webapp.parcel.service;

import com.parcel.distribution.webapp.parcel.form.ParcelForm;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

public interface ParcelService {

    ModelAndView newParcel(Principal principal, ParcelForm parcelForm);

    ModelAndView newParcel(Principal principal, ParcelForm parcelForm, BindingResult bindingResult);

    ModelAndView getParcelList(Principal principal);
}

