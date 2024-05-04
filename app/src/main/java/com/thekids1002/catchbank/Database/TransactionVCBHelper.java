package com.thekids1002.catchbank.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thekids1002.catchbank.DTO.Vietcombank;

import java.util.ArrayList;

public class TransactionVCBHelper extends SQLiteHelper {

    public static final int TABLE_VERSION = 1;
    public static final String TABLE_NAME = "vcb";

    public TransactionVCBHelper(Context context) {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "account_number TEXT, " +
                "deposit_amount INTEGER, " +
                "transaction_time TEXT, " +
                "account_balance TEXT, " +
                "reference_number TEXT, " +
                "transaction_content TEXT" +
                ")";
        db.execSQL(createTable);
    }

    public ArrayList<Vietcombank> getAllVCBData() {
        ArrayList<Vietcombank> vcbList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME  + " ORDER BY id DESC", null);
        if (cursor.moveToFirst()) {
            do {
                Vietcombank vcb = new Vietcombank();
                vcb.setAccountNumber(cursor.getString(1));
                vcb.setDepositAmount(cursor.getLong(2));
                vcb.setTransactionTime(cursor.getString(3));
                vcb.setAccountBalanceAfterTransaction(cursor.getString(4));
                vcb.setReferenceNumber(cursor.getString(5));
                vcb.setTransactionContent(cursor.getString(6));
                vcbList.add(vcb);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return vcbList;
    }
}
