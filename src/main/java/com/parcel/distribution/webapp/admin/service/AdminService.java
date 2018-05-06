package com.parcel.distribution.webapp.admin.service;

import com.parcel.distribution.webapp.admin.form.AdminForm;
import com.parcel.distribution.webapp.admin.form.CourierForm;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

public interface AdminService {

    ModelAndView addCourier(Principal principal, CourierForm courierForm);

    ModelAndView addCourier(Principal principal, CourierForm courierForm, BindingResult bindingResult);

    ModelAndView addAdmin(Principal principal, AdminForm adminForm);

    ModelAndView addAdmin(Principal principal, AdminForm adminForm, BindingResult bindingResult);

    ModelAndView courierList(Principal principal);

    ModelAndView userList(Principal principal);
}
