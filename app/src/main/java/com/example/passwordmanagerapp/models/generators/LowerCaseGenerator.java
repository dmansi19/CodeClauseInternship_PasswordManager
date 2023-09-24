package com.example.passwordmanagerapp.models.generators;

import com.example.passwordmanagerapp.models.helper.Helper;

public class LowerCaseGenerator extends PasswordGenerator {
    private static final char CHAR_A='a';
    private static final char CHAR_Z='z';

    @Override
    public String getChar() {
       return String.valueOf((char) (Helper.randomChar(CHAR_A,CHAR_Z)));
    }
}
