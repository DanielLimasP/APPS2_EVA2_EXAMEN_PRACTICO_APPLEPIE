package com.example.apps2_eva2_examen_practico_applepie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "UsersFiles.db";

    // Users table and fields
    public static final String TABLE_NAME1 = "Users";
    public static final String UC1 = "ID";
    public static final String UC2 = "LASTNAME";
    public static final String UC3 = "NAME";
    public static final String UC4 = "USERNAME";
    public static final String UC5 = "PASSWORD";

    // Files table and fields
    public static final String TABLE_NAME2 = "Files";
    public static final String FC1 = "ID";
    public static final String FC2 = "USERNAME";
    public static final String FC3 = "FILENAME";
    public static final String FC4 = "PATH";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableUsers = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME1 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " LASTNAME TEXT, NAME TEXT, USERNAME TEXT, PASSWORD TEXT)";
        db.execSQL(createTableUsers);

        String createTableFiles = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME2 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " USERNAME TEXT, FILENAME TEXT)";
        db.execSQL(createTableFiles);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);
    }

    public boolean addDataUsers(String item1, String item2, String item3, String item4) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UC2, item1);
        contentValues.put(UC3, item2);
        contentValues.put(UC4, item3);
        contentValues.put(UC5, item4);

        long result = db.insert(TABLE_NAME1, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getListContents(String table){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + table, null);
        return data;
    }

    public int updateUser(String oldLastname, String newLastname, String newName, String newUsername, String newPassword){
        SQLiteDatabase db = this.getWritableDatabase();
        String whereArgs[] = {oldLastname};
        ContentValues contentValues = new ContentValues();
        contentValues.put(UC2, newLastname);
        contentValues.put(UC3, newName);
        contentValues.put(UC4, newUsername);
        contentValues.put(UC5, newPassword);
        int recAffected = db.update(TABLE_NAME1, contentValues, "LASTNAME = ?", whereArgs);
        return recAffected;
    }

    public int deleteUser(String lastname){
        SQLiteDatabase db = this.getWritableDatabase();
        String whereArgs[] = {lastname};
        int affectedRow = db.delete(TABLE_NAME1, "LASTNAME = ?", whereArgs);
        return affectedRow;
    }

    public boolean insertFile(String userName, String fileName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FC2, userName);
        contentValues.put(FC3, fileName);

        long result = db.insert(TABLE_NAME2, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public int deleteFile(String userName, String fileName){
        SQLiteDatabase db = this.getWritableDatabase();
        String whereArgs[] = {userName, fileName};
        int affectedRow = db.delete(TABLE_NAME2, "USERNAME = ? AND FILENAME = ?", whereArgs);
        return affectedRow;
    }

        //Pulls all the files related to the username
    public Cursor queryFileName(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"*"};
        String[] conditionArgs = {username};
        Cursor c = db.query (TABLE_NAME2,
                columns,
                "USERNAME = ?",
                conditionArgs,
                "",
                "",
                ""
        );
        return c;
    }

    // Here we query data using the username
    public Cursor queryData(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"*"};
        String[] conditionArgs = {username};
        Cursor c = db.query (TABLE_NAME1,
                columns,
                "USERNAME = ?",
                conditionArgs,
                "",
                "",
                ""
        );
        return c;
    }
}
