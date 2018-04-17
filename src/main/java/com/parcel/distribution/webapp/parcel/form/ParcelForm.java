package com.parcel.distribution.webapp.parcel.form;

import lombok.Data;

@Data
public class ParcelForm {

    //dane odbiorcy

    private String name;
    private String surname;
    private String phoneNumber;
    private String email;

    //adres dostawy

    private String street;
    private String streetNumber;
    private String flatNumber;
    private String postCode;
    private String city;

    //info paczki

    private String description;
    private Double x;
    private Double y;
    private Double z;
    private Double weight;

}
