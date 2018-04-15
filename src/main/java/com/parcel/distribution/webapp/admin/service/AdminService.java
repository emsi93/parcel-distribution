package com.parcel.distribution.webapp.admin.service;

import com.parcel.distribution.webapp.admin.form.CourierForm;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

public interface AdminService {

    ModelAndView addCourier(CourierForm courierForm);

    ModelAndView addCourier(CourierForm courierForm, BindingResult bindingResult);
}
