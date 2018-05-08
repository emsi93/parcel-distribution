package com.parcel.distribution.rest.authentication.controller;

import com.parcel.distribution.db.entity.Courier;
import com.parcel.distribution.db.repository.CourierRepository;
import com.parcel.distribution.rest.authentication.request.RequestAuthentication;
import com.parcel.distribution.rest.authentication.response.ResponseAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class CourierAuthenticationRestController {

    @Autowired
    private CourierRepository courierRepository;

    @RequestMapping(value = "/authentication_courier", method = RequestMethod.POST)
    public ResponseAuthentication authentication_courier(@RequestBody RequestAuthentication request) {

        ResponseAuthentication response = new ResponseAuthentication();
        response.setMsg("error");
        response.setSuccess(false);

        Courier courier = courierRepository.findByLoginAndPasswordAndActive(request.getLogin(), request.getPassword(), true);

        if(courier != null){
            response.setMsg("success");
            response.setSuccess(true);
        }

        return response;
    }
}
