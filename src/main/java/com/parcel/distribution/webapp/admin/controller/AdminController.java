package com.parcel.distribution.webapp.admin.controller;

import com.parcel.distribution.webapp.admin.form.AdminForm;
import com.parcel.distribution.webapp.admin.form.CourierForm;
import com.parcel.distribution.webapp.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/parcel/distribution/admin/")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/addcourier", method = RequestMethod.GET)
    public ModelAndView addCourier(Principal principal, CourierForm courierForm) {
        return adminService.addCourier(principal, courierForm);
    }

    @RequestMapping(value = "/addcourier", method = RequestMethod.POST)
    public ModelAndView addCourier(Principal principal, CourierForm courierForm, BindingResult bindingResult) {
        return adminService.addCourier(principal, courierForm, bindingResult);
    }

    @RequestMapping(value = "/addadmin", method = RequestMethod.GET)
    public ModelAndView addAdmin(Principal principal, AdminForm adminForm) {
        return adminService.addAdmin(principal, adminForm);
    }

    @RequestMapping(value = "/addadmin", method = RequestMethod.POST)
    public ModelAndView addAdmin(Principal principal, AdminForm adminForm, BindingResult bindingResult) {
        return adminService.addAdmin(principal, adminForm, bindingResult);
    }

    @RequestMapping(value = "/courierlist", method = RequestMethod.GET)
    public ModelAndView courierList(Principal principal) {
        return adminService.courierList(principal);
    }

}
