package com.parcel.distribution.webapp.admin.service.impl;

import com.parcel.distribution.db.entity.Courier;
import com.parcel.distribution.db.entity.User;
import com.parcel.distribution.db.repository.CourierRepository;
import com.parcel.distribution.db.repository.UserRepository;
import com.parcel.distribution.webapp.admin.form.AdminForm;
import com.parcel.distribution.webapp.admin.form.CourierForm;
import com.parcel.distribution.webapp.admin.service.AdminService;
import com.parcel.distribution.webapp.admin.validator.AdminValidator;
import com.parcel.distribution.webapp.admin.validator.CourierValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
@ConfigurationProperties(prefix = "application")
public class AdminServiceImpl implements AdminService {

    private final static String ADMIN_ADD_COURIER_VIEW_JSP = "admin/addcourier";
    private final static String ADMIN_ADD_ADMIN_VIEW_JSP = "admin/addadmin";
    private final static String ADMIN_COURIER_LIST_VIEW_JSP = "admin/courierlist";

    @Autowired
    private CourierValidator courierValidator;

    @Autowired
    private AdminValidator adminValidator;

    @Autowired
    private CourierRepository courierRepository;

    @Autowired
    private UserRepository userRepository;

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

    @Override
    public ModelAndView addAdmin(AdminForm adminForm) {
        ModelAndView modelAndView = new ModelAndView(ADMIN_ADD_ADMIN_VIEW_JSP);
        modelAndView.addObject("adminForm", adminForm);
        return modelAndView;
    }

    @Override
    public ModelAndView addAdmin(AdminForm adminForm, BindingResult bindingResult){
        adminValidator.validate(adminForm, bindingResult);
        if(bindingResult.hasErrors())
        {
            return addAdmin(adminForm);
        }else
        {
            User user = new User();
            user.setRole("ROLE_ADMIN");
            user.setActive(true);
            user.setLogin(adminForm.getLogin());
            user.setPassword(adminForm.getPassword());
            user.setEmail(adminForm.getEmail());
            user.setPhoneNumber(adminForm.getPhoneNumber());
            user.setName(adminForm.getName());
            user.setSurname(adminForm.getSurname());
            userRepository.save(user);
            return addAdmin(adminForm);
        }
    }

    @Override
    public ModelAndView courierList() {
        ModelAndView modelAndView = new ModelAndView(ADMIN_COURIER_LIST_VIEW_JSP);
        List<Courier> courierList = courierRepository.findAll();
        modelAndView.addObject("courierList", courierList);
        return modelAndView;
    }
}
