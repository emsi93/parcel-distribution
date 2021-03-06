package com.parcel.distribution.webapp.parcel.service;

import com.parcel.distribution.webapp.parcel.form.DescriptionForm;
import com.parcel.distribution.webapp.parcel.form.ParcelForm;
import org.json.JSONException;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

public interface ParcelService {

    ModelAndView newParcel( Principal principal, ParcelForm parcelForm);

    ModelAndView newParcel(Principal principal, ParcelForm parcelForm, BindingResult bindingResult) throws JSONException;

    ModelAndView newParcelWithContact(int idRecipient, Principal principal, DescriptionForm descriptionForm);

    ModelAndView newParcelWithContact(int idRecipient, Principal principal, DescriptionForm descriptionForm, BindingResult bindingResult) throws JSONException;

    ModelAndView getParcelList(Principal principal);

    ModelAndView getDetails(Integer id, Principal principal);
}

