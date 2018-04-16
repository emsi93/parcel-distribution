package com.parcel.distribution.webapp.email.email;

import com.parcel.distribution.configuration.Config;
import com.parcel.distribution.utils.TokenUtil;

public class EmailActivation extends Email{

    private static final String MAPPING = "/parcel/distribution/activeuser/active?";
    private static final String ACTIVE_LINK_TYPE = "ACTIVATION";

    public EmailActivation(String emailAddress, String host) {
        url = host + MAPPING + Config.TOKEN_PARAM + "=" + TokenUtil.generateToken();
        topic = "Activation account";
        recipient = emailAddress;
        type = ACTIVE_LINK_TYPE;
    }
}
