package com.parcel.distribution.rest.location.controller;

import com.parcel.distribution.db.entity.Courier;
import com.parcel.distribution.db.repository.CourierRepository;
import com.parcel.distribution.rest.location.request.RequestUpdateLocation;
import com.parcel.distribution.rest.location.response.ResponseUpdateLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class UpdateLocationRestController {

    @Autowired
    private CourierRepository courierRepository;

    @RequestMapping(value = "/update_location_courier", method = RequestMethod.POST)
    public ResponseUpdateLocation updateLocationCoutier(@RequestBody RequestUpdateLocation request){
        ResponseUpdateLocation response = new ResponseUpdateLocation();
        response.setMsg("error");
        response.setSuccess(false);

        Courier courier = courierRepository.findByLoginAndTokenAndActive(request.getLogin(), request.getToken(), true);
        if (courier != null){
            courier.setLat(request.getLat());
            courier.setLng(request.getLng());
            courierRepository.save(courier);
            response.setSuccess(true);
            response.setMsg("error");
        }

        return response;
    }

}
