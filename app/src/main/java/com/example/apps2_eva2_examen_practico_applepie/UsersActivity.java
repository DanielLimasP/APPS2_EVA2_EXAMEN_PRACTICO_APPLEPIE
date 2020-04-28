package com.example.apps2_eva2_examen_practico_applepie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UsersActivity extends AppCompatActivity {

    Intent usersListActivity;
    final int CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
    }

    public void listUsers(View view){
        usersListActivity = new Intent(this, UsersListActivity.class);
        startActivityForResult(usersListActivity, CODE);
    }

}
