package com.example.passwordmanagerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.passwordmanagerapp.models.database.DatabaseHelper;
import com.example.passwordmanagerapp.models.generators.LowerCaseGenerator;
import com.example.passwordmanagerapp.models.generators.NumericGenerator;
import com.example.passwordmanagerapp.models.generators.PasswordGenerator;
import com.example.passwordmanagerapp.models.generators.SpecialCharGenerator;
import com.example.passwordmanagerapp.models.generators.UpperCaseGenerator;
import com.example.passwordmanagerapp.models.password.Password;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText edPasswordSize;
    private TextView textPasswordGenerated,textError;
    private CheckBox checkLower,checkUpper,checkNumeric,checkSpecialChar;
    private Button btnCopy,btnGenerate,btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        clickListener();

       // displaySavedPassword();

        btnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager manager = (ClipboardManager)  getSystemService(Context.CLIPBOARD_SERVICE);
                manager.setPrimaryClip(ClipData.newPlainText("password",textPasswordGenerated.getText().toString()));
                Toast.makeText(MainActivity.this, "Password is Copied", Toast.LENGTH_SHORT).show();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String genPsd = textPasswordGenerated.getText().toString();
               Intent i=new Intent(MainActivity.this,SavePassword.class);
               i.putExtra("pwd",genPsd);
               startActivity(i);
            }
        });


    }

    /*private void displaySavedPassword() {
        DatabaseHelper dbh=new DatabaseHelper(this);
        List<Password> passwordList=dbh.getPasswordList();
        Log.e("PWD_LIST",passwordList.toString());
    }*/

    private void clickListener(){
    btnGenerate.setOnClickListener(view ->{
        int passSize= Integer.parseInt(edPasswordSize.getText().toString());
        if(passSize < 8 || passSize >20){
            textError.setText("Password Size must be greater than 8 and less than 20");
            return;
        }else textError.setText(" ");
        PasswordGenerator.clear();
        if(checkLower.isChecked()) PasswordGenerator.add(new LowerCaseGenerator());
        if(checkUpper.isChecked()) PasswordGenerator.add(new UpperCaseGenerator());
        if(checkNumeric.isChecked()) PasswordGenerator.add(new NumericGenerator());
        if(checkSpecialChar.isChecked()) PasswordGenerator.add(new SpecialCharGenerator());

        if(PasswordGenerator.isEmpty()){
            textError.setText("Please select at-least one of the Password Content");
        }

        String password = PasswordGenerator.genPassword(passSize);
        textPasswordGenerated.setText(password);

        btnSave.setEnabled(true);
    });
    }
   private void initViews(){
        btnSave=findViewById(R.id.btn_save);
        textError=findViewById(R.id.text_error);
        edPasswordSize=findViewById(R.id.edit_size);
        textPasswordGenerated=findViewById(R.id.text_Password_Result);
        checkLower=findViewById(R.id.check_lower);
        checkUpper=findViewById(R.id.check_upper);
        checkNumeric=findViewById(R.id.check_num);
        checkSpecialChar=findViewById(R.id.check_spec_char);
        btnCopy=findViewById(R.id.btn_copy);
        btnGenerate=findViewById(R.id.btn_generate);

        btnSave.setEnabled(false);
    }

}