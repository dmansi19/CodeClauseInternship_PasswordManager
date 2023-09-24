package com.example.passwordmanagerapp.models.generators;

import com.example.passwordmanagerapp.models.helper.Helper;

import java.util.ArrayList;

public abstract class PasswordGenerator {
    private static ArrayList<PasswordGenerator> generators;

    public static void clear(){
        if(generators != null) {generators.clear();}
        else{generators=new ArrayList<>();}
    }

    public static boolean isEmpty(){
    return generators.isEmpty();
    }

    public static void add(PasswordGenerator pasgen){
        generators.add(pasgen);
    }

    public abstract String getChar();

    public static PasswordGenerator getRandomPassGen(){
        if(generators == null){
            generators = new ArrayList<>();
            add(new UpperCaseGenerator());//Default value
        }

        if(generators.size() == 1)
            return generators.get(0);
        int randomIndex = Helper.randomVal(generators.size());
        return generators.get(randomIndex);
    }

    public static String genPassword(int sizeOfPassword){
        StringBuilder builder = new StringBuilder();

        while(sizeOfPassword !=0){
            builder.append(getRandomPassGen().getChar());
            sizeOfPassword--;
        }
        return builder.toString();
    }

}
