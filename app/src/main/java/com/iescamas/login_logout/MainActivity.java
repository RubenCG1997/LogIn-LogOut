package com.iescamas.login_logout;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    private MaterialToolbar materialToolbar;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private TextView lbl_Info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String user;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);

        initComponent();
        user = leerPreferencias();
        lbl_Info.setText("Hola "+user+"\nBienvenido/a!");

    }

    private void initComponent(){

        materialToolbar =findViewById(R.id.materialToolbar);
        setSupportActionBar(materialToolbar);
        lbl_Info = findViewById(R.id.lbl_Info);
        sharedPreferences = getSharedPreferences("My Preferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    private String leerPreferencias(){
        String user;
        sharedPreferences = getSharedPreferences("My Preferences",Context.MODE_PRIVATE);
        user = sharedPreferences.getString("User","");
        return user;
    }

    private void borrarPreferencias(){
        editor.clear();
        editor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.output){
            Intent intent = new Intent(getApplicationContext(),LogIn.class);
            startActivity(intent);
            borrarPreferencias();
            finish();
        }
        return true;
    }

}