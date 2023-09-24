package com.example.passwordmanagerapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.passwordmanagerapp.models.database.DatabaseHelper;
import com.example.passwordmanagerapp.models.password.Password;

public class SavePassword extends AppCompatActivity {
    private EditText PwdName, PwdValue, PwdLogin;
    Button btn_save,btn_del,btn_upd,btn_view;
    DatabaseHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_password);
        initViews();
    }

    public void initViews() {
        PwdName = findViewById(R.id.edit_name);
        PwdLogin = findViewById(R.id.edit_pwd_login);
        PwdValue = findViewById(R.id.edit_pwd_value);
        btn_save = findViewById(R.id.btn_save11);
        btn_del = findViewById(R.id.btn_delete);
        btn_upd = findViewById(R.id.btn_update);
        btn_view = findViewById(R.id.btn_view);

        String generatedPass = getIntent().getStringExtra("pwd");
        PwdValue.setText(generatedPass);

        btn_upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = PwdName.getText().toString();
                String login = PwdLogin.getText().toString();
                String pass= PwdValue.getText().toString();

                Boolean checkupdateData = dbh.updateUserData(name,login,pass);

                if(checkupdateData == true){
                    Toast.makeText(SavePassword.this, "New Entry updated", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SavePassword.this, "New Entry not updated", Toast.LENGTH_SHORT).show();
                }
               // btn_upd.setBackgroundColor(getResources().getColor(R.color.green));
            }
        });
        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = PwdName.getText().toString();
                
                Boolean deleteData = dbh.deleteData(name);
                if(deleteData == true){
                    Toast.makeText(SavePassword.this, "Entry deleted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SavePassword.this, "Entry not deleted", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = dbh.getData();
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name : "+res.getString(1)+"\n");
                    buffer.append("Login : "+res.getString(3)+"\n");
                    buffer.append("Password : "+res.getString(2)+"\n\n");

                }
                AlertDialog.Builder builder=new AlertDialog.Builder(SavePassword.this);
                builder.setCancelable(true);
                builder.setTitle("Password Details ");
                builder.setMessage(buffer.toString());
                builder.show();

            }

        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 dbh = new DatabaseHelper(SavePassword.this);
                Password password = new Password(PwdName.getText().toString(), PwdLogin.getText().toString(), PwdValue.getText().toString());
                boolean saved = dbh.insertData(password);
                Toast.makeText(SavePassword.this, "Password Details are Saved", Toast.LENGTH_SHORT).show();

            }
        });
    }


}
