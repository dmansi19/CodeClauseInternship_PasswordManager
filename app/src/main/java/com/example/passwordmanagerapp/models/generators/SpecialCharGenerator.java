package com.example.passwordmanagerapp.models.generators;

import com.example.passwordmanagerapp.models.helper.Helper;

public class SpecialCharGenerator extends PasswordGenerator{
    private static final char[] specialChar_Array="?./<>,']{}[!~@#$)%^&*(".toCharArray();

    @Override
    public String getChar() {
        return String.valueOf(specialChar_Array[Helper.randomChar(0,specialChar_Array.length-1)]);
    }
}
