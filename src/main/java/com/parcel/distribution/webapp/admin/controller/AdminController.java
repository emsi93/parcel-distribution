package com.parcel.distribution.webapp.admin.controller;

import com.parcel.distribution.webapp.admin.form.CourierForm;
import com.parcel.distribution.webapp.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/parcel/distribution/admin/")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/addcourier", method = RequestMethod.GET)
    public ModelAndView addCourier(CourierForm courierForm) {
        return adminService.addCourier(courierForm);
    }

    @RequestMapping(value = "/addcourier", method = RequestMethod.POST)
    public ModelAndView addCourier(CourierForm courierForm, BindingResult bindingResult) {
        return adminService.addCourier(courierForm, bindingResult);
    }

}
