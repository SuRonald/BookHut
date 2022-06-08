package com.example.bookhut;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    String query;

    private static final String database_name = "InSorma";
    private static final SQLiteDatabase.CursorFactory factory = null;
    private static final Integer version = 1;

    public DatabaseHelper(Context context) {
        super(context, database_name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        query = "CREATE TABLE UserDatas (UserID, UserEmail, UserName, UserPassword, PurchaseCount)";
        sqLiteDatabase.execSQL(query);

        query = "CREATE TABLE TransactionDatas (TransID, UserID, BookID, TransDate, TransQnty)";
        sqLiteDatabase.execSQL(query);

        query = "CREATE TABLE BookDatas (BookID, BookName, BookAuthor, BookRating, BookPrice, BookImage, BookDesc)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        query = "DROP TABLE IF EXISTS Users";
        sqLiteDatabase.execSQL(query);
        query = "DROP TABLE IF EXISTS Transactions";
        sqLiteDatabase.execSQL(query);
        query = "DROP TABLE IF EXISTS Products";
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }
}
