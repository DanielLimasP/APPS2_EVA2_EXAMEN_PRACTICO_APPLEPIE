package com.example.apps2_eva2_examen_practico_applepie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Environment;
import android.transition.ArcMotion;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FilesActivity extends AppCompatActivity {
    private String userReceived;
    EditText editText, editFileName;
    private String ARCHIVO = "";
    String filePath;
    final int PERMISO_ESCRITURA = 1000;
    String pathSD;
    Intent openFilesIntent;
    DatabaseHelper myDb;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files_activity);
        editText = findViewById(R.id.edTxtText);
        editFileName = findViewById(R.id.edTxtFileName);
        //Make DatabaseHelper Instance
        myDb = new DatabaseHelper(this);
        //Gets the username from the previous activity
        Intent intent = getIntent();
        userReceived = intent.getExtras().getString("user");
        //Sets the SD Path
        pathSD = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getPath();
        //Validates the permisions for writing files
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISO_ESCRITURA);

        }
    }


    //OnClick for creating files
    public void createFile(View view) {
        try {
            //Agregar control del nombre del archivo, en caso que el nombre del archivo ya existe en la base de
            //datos, guardar el contenido en el archivo con ese nombre
            if (!editFileName.getEditableText().toString().equals("") && !editText.getEditableText().toString().equals("")) {
                ARCHIVO = editFileName.getEditableText().toString();
                File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), ARCHIVO + ".txt");
                FileOutputStream fos = new FileOutputStream(file);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                BufferedWriter bw = new BufferedWriter(osw);
                String sCade = editText.getEditableText().toString();

                bw.write(sCade);
                bw.close();
                Toast.makeText(this, "File Created", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Fill all the parameters", Toast.LENGTH_SHORT).show();
            }

                //Agregamos el nombre del archivo con su respectivo nombre de usuario en la base de datos
            //Esto sirve para tener un enlace entre el usuario y sus archivos, tenerlos en filas
            String uName = getIntent().getExtras().get("user").toString();
            boolean insertar = myDb.insertFile(uName, editFileName.getText().toString());
            if(insertar == true){
                System.out.println("Added to DB");
                Toast.makeText(this, "Added to DB", Toast.LENGTH_LONG).show();
            }else{
                System.out.println("Could not add to DB");
                Toast.makeText(this, "Could not add to DB", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
                e.printStackTrace();
        }

    }

    //OnClick for Saving/Update Files
    public void saveFile(View view) {

        try {
            //Agregar control del nombre del archivo, en caso que el nombre del archivo no exista en la base de
            //datos, guardar el contenido en un archivo nuevo y guardar el nombre del archivo en la base de datos
            if (!editFileName.getEditableText().toString().equals("") && !editText.getEditableText().toString().equals("")) {

                ARCHIVO = editFileName.getEditableText().toString();
                File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), ARCHIVO + ".txt");

                if (file.exists()) {
                    FileOutputStream fos = new FileOutputStream(file);
                    OutputStreamWriter osw = new OutputStreamWriter(fos);
                    BufferedWriter bw = new BufferedWriter(osw);
                    String sCade = editText.getEditableText().toString();

                    bw.write(sCade);
                    bw.close();
                    Toast.makeText(this, "File Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "File has not been created", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Fill all the parameters", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {

        }
    }

    //OnClick for Opening the list with all the files of the current user
    public void openFiles(View view) {
        openFilesIntent = new Intent(this, FileList.class);
        String uName = getIntent().getExtras().get("user").toString();
        openFilesIntent.putExtra("username", uName);
        startActivityForResult(openFilesIntent, 1034);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1034 && resultCode == RESULT_OK && data != null) {
            String path = data.getStringExtra("filepath");
            String name = data.getStringExtra("filename");
            editText.setText("");
            editFileName.setText(name);
            Toast.makeText(this, path, Toast.LENGTH_SHORT).show();
            openFileFromPath(path);
        }
    }

    public void openFileFromPath(String sPath) {
        try {

            File file = new File(sPath);
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String sCade;
            try {
                while ((sCade = br.readLine()) != null) {
                    editText.append(sCade);
                    editText.append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}