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
import java.security.Principal;
import java.util.List;

@Slf4j
@Service
@Transactional
@ConfigurationProperties(prefix = "application")
public class AdminServiceImpl implements AdminService {

    private final static String ADMIN_ADD_COURIER_VIEW_JSP = "admin/addcourier";
    private final static String ADMIN_ADD_ADMIN_VIEW_JSP = "admin/addadmin";
    private final static String ADMIN_COURIER_LIST_VIEW_JSP = "admin/courierlist";
    private static final String ADMIN_ADD_ADMIN_SUCCESS_VIEW_JSP = "admin/addadmin_success";
    private static final String ADMIN_ADD_COURIER_SUCCESS_VIEW_JSP = "admin/addcourier_success";
    private static final String ADMIN_USER_LIST_VIEW_JSP = "admin/userlist";

    @Autowired
    private CourierValidator courierValidator;

    @Autowired
    private AdminValidator adminValidator;

    @Autowired
    private CourierRepository courierRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ModelAndView addCourier(Principal principal, CourierForm courierForm) {
        ModelAndView modelAndView = new ModelAndView(ADMIN_ADD_COURIER_VIEW_JSP);
        String role = userRepository.findByLogin(principal.getName()).getRole();
        modelAndView.addObject("role", role);
        modelAndView.addObject("username", principal.getName());
        modelAndView.addObject("courierForm", courierForm);
        return modelAndView;
    }

    @Override
    public ModelAndView addCourier(Principal principal, CourierForm courierForm, BindingResult bindingResult) {
        courierValidator.validate(courierForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return addCourier(principal, courierForm);
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
            return addCourierSuccess(principal);
        }
    }

    private ModelAndView addCourierSuccess(Principal principal){
        ModelAndView modelAndView = new ModelAndView(ADMIN_ADD_COURIER_SUCCESS_VIEW_JSP);
        String role = userRepository.findByLogin(principal.getName()).getRole();
        modelAndView.addObject("role", role);
        modelAndView.addObject("username", principal.getName());
        return modelAndView;
    }

    @Override
    public ModelAndView addAdmin(Principal principal, AdminForm adminForm) {
        ModelAndView modelAndView = new ModelAndView(ADMIN_ADD_ADMIN_VIEW_JSP);
        String role = userRepository.findByLogin(principal.getName()).getRole();
        modelAndView.addObject("role", role);
        modelAndView.addObject("username", principal.getName());
        modelAndView.addObject("adminForm", adminForm);
        return modelAndView;
    }

    @Override
    public ModelAndView addAdmin(Principal principal, AdminForm adminForm, BindingResult bindingResult) {
        adminValidator.validate(adminForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return addAdmin(principal, adminForm);
        } else {
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
            return addAdminSuccess(principal);
        }
    }

    private ModelAndView addAdminSuccess(Principal principal){
        ModelAndView modelAndView = new ModelAndView(ADMIN_ADD_ADMIN_SUCCESS_VIEW_JSP);
        String role = userRepository.findByLogin(principal.getName()).getRole();
        modelAndView.addObject("role", role);
        modelAndView.addObject("username", principal.getName());
        return modelAndView;
    }

    @Override
    public ModelAndView courierList(Principal principal) {
        ModelAndView modelAndView = new ModelAndView(ADMIN_COURIER_LIST_VIEW_JSP);
        String role = userRepository.findByLogin(principal.getName()).getRole();
        modelAndView.addObject("role", role);
        modelAndView.addObject("username", principal.getName());
        List<Courier> courierList = courierRepository.findAll();
        modelAndView.addObject("courierList", courierList);
        return modelAndView;
    }

    @Override
    public ModelAndView userList(Principal principal) {
        ModelAndView modelAndView = new ModelAndView(ADMIN_USER_LIST_VIEW_JSP );
        String role = userRepository.findByLogin(principal.getName()).getRole();
        modelAndView.addObject("role", role);
        modelAndView.addObject("username", principal.getName());
        List<User> userList = userRepository.findAll();
        modelAndView.addObject("userList", userList);
        return modelAndView;
    }
}
