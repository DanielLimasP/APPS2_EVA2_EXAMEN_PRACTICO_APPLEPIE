package com.example.apps2_eva2_examen_practico_applepie;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<User> {

    Context context;
    int resource;
    ArrayList<User> aUsers;

    public UserAdapter(Context context, int resource, ArrayList<User> object) {
        super(context, resource, object);
        this.context = context;
        this.resource = resource;
        this.aUsers = object;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView txtLastname, txtName, txtUsername, txtPassword;

        if(convertView == null){
            LayoutInflater infla = ((Activity)context).getLayoutInflater();
            convertView = infla.inflate(resource, parent, false);
        }

        txtLastname = convertView.findViewById(R.id.txtlayUserLastname);
        txtName = convertView.findViewById(R.id.txtlayUserName);
        txtUsername = convertView.findViewById(R.id.txtlayUserUsername);
        txtPassword = convertView.findViewById(R.id.txtlayUserPassword);

        txtLastname.setText(aUsers.get(position).getLastname());
        txtName.setText(aUsers.get(position).getName());
        txtUsername.setText(aUsers.get(position).getUsername());
        txtPassword.setText(aUsers.get(position).getPassword());

        return convertView;
    }
}
