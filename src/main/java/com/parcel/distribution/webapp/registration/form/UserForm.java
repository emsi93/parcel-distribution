package com.parcel.distribution.webapp.registration.form;

import lombok.Data;

@Data
public class UserForm {

    private String login;
    private String email;
    private String password;
    private String passwordConfirm;

    public UserForm(){

    }

    public UserForm(String login, String email, String password, String passwordConfirm) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }
}
