package com.example.passwordmanagerapp.models.password;

import android.content.ContentValues;
import android.database.Cursor;

import androidx.annotation.NonNull;

import java.util.Date;

public class Password {
  //  private int id;
    private String name,login,password;
   // private Date updateDate;

    public Password(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
       // this.updateDate = new Date();
    }

    public Password(){

    }

    public static Password fromCursor(Cursor cursor) {
        Password password1=new Password();
        //password1.id=cursor.getInt(cursor.getColumnIndexOrThrow("id"));
        password1.name=cursor.getString(cursor.getColumnIndexOrThrow("name"));
        password1.login=cursor.getString(cursor.getColumnIndexOrThrow("login"));
        password1.password=cursor.getString(cursor.getColumnIndexOrThrow("password"));
       // password1.updateDate=new Date(Long.parseLong(cursor.getString(cursor.getColumnIndexOrThrow("up"))));

        return password1;
    }


    @NonNull
    @Override
    public String toString() {
        return "Password{" +
                //"id=" +id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' + '}';
                /*+
                ", updateDate='" + updateDate  +
                '}';*/
    }
    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("password",password);
        values.put("login",login);
       // values.put("update_date",updateDate.getTime());
        return values;
    }


    }

