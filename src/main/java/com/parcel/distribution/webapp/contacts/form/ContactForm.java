package com.parcel.distribution.webapp.contacts.form;

import lombok.Data;

@Data
public class ContactForm {


    private String name;
    private String surname;
    private String phoneNumber;
    private String email;

    private String street;
    private String streetNumber;
    private String flatNumber;
    private String postCode;
    private String city;

}
