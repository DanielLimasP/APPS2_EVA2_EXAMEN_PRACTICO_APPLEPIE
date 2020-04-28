package com.example.apps2_eva2_examen_practico_applepie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UsersActivity extends AppCompatActivity {

    Intent usersListActivity;
    final int CODE = 1000;
    DatabaseHelper myDB;
    SQLiteDatabase db;

    EditText edtLastname, edtName, edtUsername, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        myDB = new DatabaseHelper(this);

        edtLastname = findViewById(R.id.edtLastName);
        edtName = findViewById(R.id.edtName);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
    }

    public void insertOne(View v){
        String lastname = edtLastname.getText().toString();
        String name = edtName.getText().toString();
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();

        if(edtLastname.length()!= 0 && edtName.length() != 0 && edtName.length() != 0 && edtPassword.length() != 0){
            AddData(lastname, name, username, password);
            edtLastname.setText("");
            edtName.setText("");
            edtUsername.setText("");
            edtPassword.setText("");
        }else{
            Toast.makeText(UsersActivity.this, "You must type something into every textfield", Toast.LENGTH_LONG).show();
        }
    }

    public void listUsers(View view){
        usersListActivity = new Intent(this, UsersListActivity.class);
        startActivityForResult(usersListActivity, CODE);
    }

    public void AddData(String lastname, String name, String username, String password) {
        boolean insertData = myDB.addDataUsers(lastname, name, username, password);

        if(insertData==true){
            Toast.makeText(this, "Entry added succesfully", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }

}
