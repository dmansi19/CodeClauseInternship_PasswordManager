package com.example.passwordmanagerapp.models.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.passwordmanagerapp.models.generators.PasswordGenerator;
import com.example.passwordmanagerapp.models.password.Password;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(@Nullable Context context) {
        super(context, "PASSWORDS", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE PASSWORDS" + "(name TEXT PRIMARY KEY,login TEXT,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS PASSWORDS");
        onCreate(db);
    }

    public boolean insertData(Password password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = password.getContentValues();
        return db.insert("PASSWORDS", null, values) != -1;
    }

    public Boolean updateUserData(String name, String login, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("login", login);
        values.put("password", password);
        Cursor cursor = db.rawQuery("SELECT * FROM PASSWORDS where name =?", new String[]{name});

        if (cursor.getCount() > 0) {
            long result = db.update("PASSWORDS", values, "name=?", new String[]{name});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    //
    //
    //
    public Boolean deleteData(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PASSWORDS where name =? ", new String[]{name});

        if (cursor.getCount() > 0) {
            long result = db.delete("PASSWORDS", "name=? ", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }


    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PASSWORDS", null);
        return cursor;
    }
}



































