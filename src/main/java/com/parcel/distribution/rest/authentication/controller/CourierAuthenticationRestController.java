package com.parcel.distribution.rest.authentication.controller;

import com.parcel.distribution.db.entity.Courier;
import com.parcel.distribution.db.repository.CourierRepository;
import com.parcel.distribution.rest.authentication.request.RequestAuthentication;
import com.parcel.distribution.rest.authentication.request.RequestCheckToken;
import com.parcel.distribution.rest.authentication.response.ResponseAuthentication;
import com.parcel.distribution.utils.TokenUtil;
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
    public ResponseAuthentication authenticationCourier(@RequestBody RequestAuthentication request) {

        ResponseAuthentication response = buildResponse("error", false);

        Courier courier = courierRepository.findByLoginAndPasswordAndActive(request.getLogin(), request.getPassword(), true);

        if (courier != null){
            response = buildResponse("success", true);
            String token = TokenUtil.generateToken();
            courier.setToken(token);
            courierRepository.save(courier);
            response.setToken(token);
        }

        return response;
    }

    @RequestMapping(value = "/check_token", method = RequestMethod.POST)
    public ResponseAuthentication checkToken(@RequestBody RequestCheckToken request) {

        ResponseAuthentication response = buildResponse("error", false);

        Courier courier = courierRepository.findByLoginAndTokenAndActive(request.getLogin(), request.getToken(), true);

        if (courier != null)
            response = buildResponse("success", true);

        return response;
    }


    private ResponseAuthentication buildResponse(String msg, boolean success) {
        ResponseAuthentication response = new ResponseAuthentication();
        response.setMsg(msg);
        response.setSuccess(success);
        return response;
    }


}
