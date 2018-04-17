package com.parcel.distribution.utils;

import java.util.Random;

public class Code {

    private static final String NUMBERS="0123456789";

    private static final Random RN = new Random();
    private static final int MAX = NUMBERS.length()-1;
    private static final int MIN = 0;

    public static String generate(){

        StringBuilder code = new StringBuilder();
        int index;
        for(int i=0; i<4; i++)
        {
            index = RN.nextInt(MAX - MIN + 1) + MIN;
            code = new StringBuilder(code.toString() + NUMBERS.charAt(index) );
        }
        return code.toString();
    }
}
