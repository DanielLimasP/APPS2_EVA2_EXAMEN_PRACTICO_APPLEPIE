package com.example.apps2_eva2_examen_practico_applepie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FilesLogin extends AppCompatActivity {

    DatabaseHelper myDB;
    EditText edtUsernameLogin, edtPassLogin;
    Intent filesIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files_login);
        myDB = new DatabaseHelper(this);
        edtUsernameLogin = findViewById(R.id.edtUsernameLogin);
        edtPassLogin = findViewById(R.id.edtPassLogin);
        //fastQuery();
    }

    public void closeLoginActivity(View view){
        finish();
    }

    public void fastQuery(){
        Cursor data = myDB.queryData("McFer");
        while (data.moveToNext()) {
            String qPassword = data.getString(4);
            Toast.makeText(this, qPassword ,Toast.LENGTH_LONG).show();
        }
    }

    public void login(View view){
        String username = edtUsernameLogin.getText().toString();
        String password = edtPassLogin.getText().toString();
        String qPassword = "";
        Cursor data = myDB.queryData(username);
        while (data.moveToNext()) {
            qPassword = data.getString(4);
        }
        if(data.getCount() == 0){
            Toast.makeText(this, "That username doesn't exist in the DB",Toast.LENGTH_LONG).show();
        }else{
            if(password.equals(qPassword)){
                filesIntent = new Intent(this, FilesActivity.class);
                startActivity(filesIntent);
            }else{
                Toast.makeText(this, "Wrong password",Toast.LENGTH_LONG).show();
            }
        }
    }
}
