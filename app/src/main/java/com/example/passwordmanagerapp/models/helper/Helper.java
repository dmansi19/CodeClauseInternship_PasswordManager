package com.example.passwordmanagerapp.models.helper;

public class Helper {

    public static int randomVal(int size){
        double rand =Math.random();
        return(int) (rand * size);
    }

    public static int randomChar(int min, int max){
        return randomVal(max-min + 1) + min;
    }


}
