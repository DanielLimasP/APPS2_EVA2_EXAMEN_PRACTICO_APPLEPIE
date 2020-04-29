package com.example.apps2_eva2_examen_practico_applepie;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class FileList extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_list);
        listView = (ListView) findViewById(R.id.list);
        final ArrayList<String> listItems = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);

        listView.setAdapter(adapter);

        //Get Path where files are located
        String path = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getPath();
        //Get all files from path
        File directory = new File(path);
        final File[] files = directory.listFiles();

        for (int i = 0; i < files.length; i++) {
            listItems.add(files[i].getName().replace(".txt", ""));
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Get item click position
                int itemPosition = position;
                //Get item from item clicked position
                String itemValue = (String) listView.getItemAtPosition(position);
                Toast.makeText(FileList.this, "Position: " + itemValue, Toast.LENGTH_SHORT).show();
                //Set result to get file path and name in order to open the file on previous activity
                Intent intent = new Intent(getApplicationContext(), FilesActivity.class);
                String filePath = files[itemPosition].getPath();
                String fileName = files[itemPosition].getName().replace(".txt", "");

                intent.putExtra("filepath", filePath);
                intent.putExtra("filename", fileName);
                setResult(Activity.RESULT_OK, intent);
                finish();
                ;

            }
        });

    }
}
