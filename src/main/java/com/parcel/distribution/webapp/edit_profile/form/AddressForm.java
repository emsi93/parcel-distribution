package com.parcel.distribution.webapp.edit_profile.form;

import lombok.Data;

@Data
public class AddressForm {

    private String street;
    private String streetNumber;
    private String flatNumber;
    private String postCode;
    private String city;

    public AddressForm(){

    }
    public AddressForm(String street, String streetNumber, String flatNumber, String postCode, String city) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.flatNumber = flatNumber;
        this.postCode = postCode;
        this.city = city;
    }
}
