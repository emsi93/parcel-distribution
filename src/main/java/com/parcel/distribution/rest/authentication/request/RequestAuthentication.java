package com.parcel.distribution.rest.authentication.request;

import lombok.Data;

@Data
public class RequestAuthentication {

    private String login;
    private String password;
}
