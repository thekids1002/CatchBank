package com.thekids1002.catchbank.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "bank.db";
    private static final int DATABASE_VERSION = 1;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    // Phương thức chèn dữ liệu
    public long insert(String tableName, ContentValues values) {
       try{
           SQLiteDatabase db = getWritableDatabase();
           long rowId = db.insert(tableName, null, values);
           db.close();
           return rowId;
       }
       catch (Exception e){
           e.printStackTrace();
           return -1;
       }
    }
}