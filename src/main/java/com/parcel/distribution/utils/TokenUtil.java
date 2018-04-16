package com.parcel.distribution.utils;

import java.security.SecureRandom;

public class TokenUtil {

    public static String generateToken(){
        SecureRandom random = new SecureRandom();
        long longToken = Math.abs( random.nextLong() );
        return Long.toString( longToken, 16 );
    }
}
