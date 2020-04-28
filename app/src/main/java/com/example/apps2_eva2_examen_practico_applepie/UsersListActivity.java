package com.example.apps2_eva2_examen_practico_applepie;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class UsersListActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    DatabaseHelper myDB;
    SQLiteDatabase db;
    ListView usersList;
    UserAdapter userAdapter;
    User[] usersArray = new User[100];
    ArrayList<User> userArraysList = new ArrayList<>();
    int cont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        myDB = new DatabaseHelper(this);
        usersList = findViewById(R.id.lstUsersList);

        Cursor data = myDB.getListContents("Users");
        if (data.getCount() == 0) {
            Toast.makeText(this, "No entries in the database", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                userArraysList.add(new User(data.getString(1), data.getString(2), data.getString(3), data.getString(4)));
                userAdapter = new UserAdapter(this,
                        R.layout.user_layout,
                        userArraysList);
                usersList.setAdapter(userAdapter);
            }
        }

        usersList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Bundle bundle = new Bundle();
        Intent inDatos = new Intent();
        int selection = 0;

        bundle.putString("lastname", userArraysList.get(i).getLastname());
        bundle.putString("name", userArraysList.get(i).getName());
        bundle.putString("username", userArraysList.get(i).getUsername());
        bundle.putString("password", userArraysList.get(i).getPassword());

        inDatos.putExtras(bundle);

        setResult(Activity.RESULT_OK, inDatos);
        finish();
    }
}
