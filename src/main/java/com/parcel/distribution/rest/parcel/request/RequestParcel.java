package com.parcel.distribution.rest.parcel.request;

import lombok.Data;

@Data
public class RequestParcel {

    private String login;
    private String token;
    private String idParcel;
    private String code;
}
