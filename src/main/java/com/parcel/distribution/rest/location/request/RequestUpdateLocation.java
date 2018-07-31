package com.parcel.distribution.rest.location.request;

import lombok.Data;

@Data
public class RequestUpdateLocation {

    private String login;
    private String token;
    private Double lat;
    private Double lng;
}
