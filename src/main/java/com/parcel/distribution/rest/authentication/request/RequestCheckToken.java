package com.parcel.distribution.rest.authentication.request;

import lombok.Data;

@Data
public class RequestCheckToken {

    private String login;
    private String token;
}
