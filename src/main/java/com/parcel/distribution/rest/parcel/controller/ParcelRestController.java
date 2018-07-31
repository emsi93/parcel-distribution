package com.parcel.distribution.rest.parcel.controller;

import com.parcel.distribution.db.entity.Courier;
import com.parcel.distribution.db.entity.Parcel;
import com.parcel.distribution.db.repository.CourierRepository;
import com.parcel.distribution.db.repository.ParcelRepository;
import com.parcel.distribution.rest.parcel.request.RequestParcel;
import com.parcel.distribution.rest.parcel.response.ResponseParcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class ParcelRestController {

    @Autowired
    private CourierRepository courierRepository;

    @Autowired
    private ParcelRepository parcelRepository;

    @RequestMapping(value = "/receiving_parcel", method = RequestMethod.POST)
    public ResponseParcel receivingParcel(@RequestBody RequestParcel request) {

        ResponseParcel response = new ResponseParcel();
        response.setMsg("error");
        response.setSuccess(false);

        Courier courier = courierRepository.findByLoginAndTokenAndActive(request.getLogin(), request.getToken(), true);

        if (courier != null) {

            Parcel parcel = parcelRepository.findById(request.getIdParcel());
            //parcel.setStatus(true);
            parcelRepository.save(parcel);
            response.setSuccess(true);
            response.setMsg("success");

        }
        return response;
    }

    @RequestMapping(value = "/spending_parcel", method = RequestMethod.POST)
    public ResponseParcel spendingParcel(@RequestBody RequestParcel request) {
        ResponseParcel response = new ResponseParcel();
        response.setMsg("error");
        response.setSuccess(false);

        Courier courier = courierRepository.findByLoginAndTokenAndActive(request.getLogin(), request.getToken(), true);

        if (courier != null) {

            Parcel parcel = parcelRepository.findById(request.getIdParcel());
            if (parcel != null) {
                if (parcel.getCode().equals(request.getCode())) {
                    //parcel.setStatus(true);
                    parcelRepository.save(parcel);
                    response.setSuccess(true);
                    response.setMsg("success");
                }
            }
        }
        return response;
    }

    @RequestMapping(value = "/transfer_parcel", method = RequestMethod.POST)
    public ResponseParcel transferParcel(@RequestBody RequestParcel request) {
        ResponseParcel response = new ResponseParcel();
        response.setMsg("error");
        response.setSuccess(false);

        Courier courier = courierRepository.findByLoginAndTokenAndActive(request.getLogin(), request.getToken(), true);

        if (courier != null) {

            Parcel parcel = parcelRepository.findById(request.getIdParcel());
            if (parcel != null) {
                if (parcel.getCode().equals(request.getCode())) {
                    parcel.setCourier(courier);
                    parcelRepository.save(parcel);
                    response.setSuccess(true);
                    response.setMsg("success");
                }
            }
        }
        return response;
    }

}
