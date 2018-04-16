package com.parcel.distribution.webapp.admin.form;

import lombok.Data;

@Data
public class AdminForm {

    private String name;
    private String surname;
    private String phoneNumber;
    private String login;
    private String email;
    private String password;
    private String passwordConfirm;
}
