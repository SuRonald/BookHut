package com.example.bookhut.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.bookhut.data.TransactionData;
import com.example.bookhut.database.DatabaseHelper;

import java.util.Vector;

public class TransactionDataHelper {
    String query;
    Cursor cursor;

    private Context ctx;
    private DatabaseHelper helper;
    private SQLiteDatabase db;

    public TransactionDataHelper(Context ctx) {
        this.ctx = ctx;
    }

    public void open() throws SQLException {
        helper = new DatabaseHelper(ctx);
        db = helper.getWritableDatabase();
    }

    public void close() throws SQLException {
        helper.close();
    }

    public Vector<TransactionData> viewTransactions(int userid) {
        query = "SELECT * FROM TransactionDatas";
        cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        Vector<TransactionData> tempList = new Vector<>();
        int transID;
        int userID;
        int bookID;
        String transDate;
        int transQnty;

        if (cursor.getCount() > 0){
            do {
                userID = cursor.getInt(cursor.getColumnIndexOrThrow("UserID"));
                if (userID == userid){
                    transID = cursor.getInt(cursor.getColumnIndexOrThrow("TransID"));
                    bookID = cursor.getInt(cursor.getColumnIndexOrThrow("BookID"));
                    transDate = cursor.getString(cursor.getColumnIndexOrThrow("TransDate"));
                    transQnty = cursor.getInt(cursor.getColumnIndexOrThrow("TransQnty"));

                    tempList.add(new TransactionData(transID, userID, bookID, transDate, transQnty));
                }

                cursor.moveToNext();
            } while (!cursor.isAfterLast());

        }

        cursor.close();
        return tempList;
    }

    public void insertTransaction(String transID, int userID, int bookID, String transDate, int transQnty){
        query = "INSERT INTO TransactionDatas VALUES ('" + transID + "', " + userID + ", " + bookID + ", '" + transDate + "', " + transQnty + ")";
        db.execSQL(query);
    }

    public void deleteTransaction(int userID){
        query = "DELETE FROM TransactionDatas WHERE UserID = " + userID;
        cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        while (cursor != null && !cursor.isAfterLast()){
            db.execSQL(query);
            cursor.moveToNext();
        }
    }
}