package com.example.eleksayon;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {

    public static final String databaseName_1 = "Students.db";
    public static final String databaseName_2 = "Admins.db";
    public static final String databaseName_3 = "Participants.db";

    public DBHandler(@Nullable Context context) {
        super(context, databaseName_1, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE allusers(email TEXT PRIMARY KEY, password TEXT, id TEXT, course TEXT)");
        db.execSQL("CREATE TABLE adminusers(email TEXT PRIMARY KEY, password TEXT, id TEXT, course TEXT)");
        db.execSQL("CREATE TABLE participants(id INTEGER PRIMARY KEY AUTOINCREMENT, first_name TEXT, last_name TEXT, year_level TEXT, course TEXT, position TEXT, platform TEXT)");
        ContentValues adminValues = new ContentValues();
        adminValues.put("email", "jawhara.amirol@g.msuiit.edu.ph");
        adminValues.put("password", "12345");
        adminValues.put("id", "2021-0000");
        db.insert("adminusers", null, adminValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS allusers");
        db.execSQL("DROP TABLE IF EXISTS adminusers");
        db.execSQL("DROP TABLE IF EXISTS participants");
        onCreate(db);
    }

    public Boolean insertData(String email, String password, String id, String course) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("id", id);
        contentValues.put("course", course);
        long result = db.insert("allusers", null, contentValues);
        return result != -1;
    }

    public Boolean checkEmail(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * FROM allusers WHERE email = ?", new String[]{email});
        return cursor.getCount() > 0;
    }

    public Boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * FROM allusers WHERE email = ? AND password = ?", new String[]{email, password});
        return cursor.getCount() > 0;
    }

    public Boolean checkEmailAdmin(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * FROM adminusers WHERE email = ?", new String[]{email});
        return cursor.getCount() > 0;
    }

    public Boolean checkEmailPasswordAdmin(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * FROM adminusers WHERE email = ? AND password = ?", new String[]{email, password});
        return cursor.getCount() > 0;
    }

    public Boolean insertParticipant(String firstName, String lastName, String yearLevel, String course, String position, String platform) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("first_name", firstName);
        contentValues.put("last_name", lastName);
        contentValues.put("year_level", yearLevel);
        contentValues.put("course", course);
        contentValues.put("position", position);
        contentValues.put("platform", platform);
        long result = db.insert("participants", null, contentValues);
        return result != -1;
    }

}