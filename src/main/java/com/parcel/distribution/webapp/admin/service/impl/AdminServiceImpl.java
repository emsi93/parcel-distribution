package com.parcel.distribution.webapp.admin.service.impl;

import com.parcel.distribution.db.entity.Courier;
import com.parcel.distribution.db.repository.CourierRepository;
import com.parcel.distribution.webapp.admin.form.CourierForm;
import com.parcel.distribution.webapp.admin.service.AdminService;
import com.parcel.distribution.webapp.admin.validator.CourierValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
@ConfigurationProperties(prefix = "application")
public class AdminServiceImpl implements AdminService {

    private final static String ADMIN_ADD_COURIER_VIEW_JSP = "admin/addcourier";

    @Autowired
    private CourierValidator courierValidator;

    @Autowired
    private CourierRepository courierRepository;

    @Override
    public ModelAndView addCourier(CourierForm courierForm) {
        ModelAndView modelAndView = new ModelAndView(ADMIN_ADD_COURIER_VIEW_JSP);
        modelAndView.addObject("courierForm", courierForm);
        return modelAndView;
    }

    @Override
    public ModelAndView addCourier(CourierForm courierForm, BindingResult bindingResult) {
        courierValidator.validate(courierForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return addCourier(courierForm);
        } else {
            Courier courier = new Courier();
            courier.setActive(true);
            courier.setLogin(courierForm.getLogin());
            courier.setPassword(courierForm.getPassword());
            courier.setName(courierForm.getName());
            courier.setSurname(courierForm.getSurname());
            courier.setPhoneNumber(courierForm.getPhoneNumber());
            courier.setEmail(courierForm.getEmail());
            courierRepository.save(courier);
            return addCourier(courierForm);
        }
    }
}
