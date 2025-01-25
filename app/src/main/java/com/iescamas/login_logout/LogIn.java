package com.iescamas.login_logout;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class LogIn extends AppCompatActivity {

    private EditText txt_User,txt_Password;
    private Button btn_Send;
    private SharedPreferences.Editor editor;
    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);

        initComponent();
        leerPreferencias();

        btn_Send.setOnClickListener(v -> {
            if(txt_User.getText().toString().trim().isEmpty() ||
            txt_Password.getText().toString().trim().isEmpty()){
                Toast toast = Toast.makeText(
                        getApplicationContext(),msg,Toast.LENGTH_SHORT
                );
                toast.show();
            }
            else{
                editor.putString("User",txt_User.getText().toString());
                editor.putString("Password",txt_Password.getText().toString());
                editor.apply();
                cambiarIntent();
            }
        });



    }

    private void initComponent(){

        txt_User = findViewById(R.id.txt_User);
        txt_Password = findViewById(R.id.txt_Password);
        btn_Send = findViewById(R.id.btn_Send);
        msg =  getResources().getString(R.string.msg_Toast);
        txt_User.requestFocus();

    }

    private void leerPreferencias(){

        String user,password;

        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences("My Preferences",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        user = sharedPreferences.getString("User","");
        password = sharedPreferences.getString("Password","");


        if (!user.isEmpty() && !password.isEmpty()){
            cambiarIntent();
        }
    }

    private void cambiarIntent(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }


}