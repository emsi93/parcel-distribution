package com.parcel.distribution.webapp.email.service;


import com.parcel.distribution.db.entity.Courier;
import com.parcel.distribution.db.entity.Parcel;
import com.parcel.distribution.webapp.email.email.Email;

public interface EmailService {

    void sendEmail(Email email);

    void sendEmailToCourier(Courier courier, Parcel parcel);
}
