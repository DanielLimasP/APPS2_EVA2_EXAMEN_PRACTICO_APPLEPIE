package com.example.apps2_eva2_examen_practico_applepie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Intent usersIntent, filesIntent, loginIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchUsersActivity(View view){
        usersIntent = new Intent(this, UsersActivity.class);
        startActivity(usersIntent);
    }

    public void launchFilesLoginActivity(View view){
        loginIntent = new Intent(this, FilesLogin.class);
        startActivity(loginIntent);
    }

    public void closeApp(View view){
        System.exit(1);
    }

}
