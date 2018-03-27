package com.parcel.distribution.webapp.edit_profile.form;

import lombok.Data;

@Data
public class FirstForm {

    private String name;
    private String surname;
    private String phoneNumber;

    public FirstForm(){

    }

    public FirstForm(String name, String surname, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }
}
