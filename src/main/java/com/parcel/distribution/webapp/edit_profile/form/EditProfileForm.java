package com.parcel.distribution.webapp.edit_profile.form;

import lombok.Data;

@Data
public class EditProfileForm {

    private String name;
    private String surname;
    private String phoneNumber;

    private String street;
    private String streetNumber;
    private String flatNumber;
    private String postCode;
    private String city;

    public EditProfileForm(){

    }

    public EditProfileForm(String name, String surname, String phoneNumber, String street, String streetNumber, String flatNumber, String postCode, String city) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.street = street;
        this.streetNumber = streetNumber;
        this.flatNumber = flatNumber;
        this.postCode = postCode;
        this.city = city;
    }
}
