package com.example.edejesus1097.innaflash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by edejesus1097 on 12/12/16.
 */

public class BaseSub extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Subject_name";

    public String CREATE_STRING = "CREATE TABLE " + Subject.Subject_info.TABLE_NAME + "(" + Subject.Subject_info.Subject + " TEXT);";

    public BaseSub(Context context) {
        super(context, Subject.Subject_info.DATEABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STRING);
        Log.d("Database operations", "Database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int a, int b) {
        db.execSQL("DROP TABLE IF EXISTS " + Subject.Subject_info.TABLE_NAME);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    public void addSub(String sub) {
        SQLiteDatabase SQ = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Subject.Subject_info.Subject, sub);
        long k = SQ.insert(Subject.Subject_info.TABLE_NAME, null, cv);
        Log.d("Database operations", "Subject inserted");

    }


    public Cursor getSub() {
        SQLiteDatabase SQ = this.getReadableDatabase();
        Cursor CR = SQ.rawQuery("select * from " + Subject.Subject_info.TABLE_NAME, null);
        Log.d("Database operations", "Getting Subject");
        return CR;
    }
}




