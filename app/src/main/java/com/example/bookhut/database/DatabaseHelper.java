package com.example.bookhut.database;

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
        query = "CREATE TABLE UserDatas (UserID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "UserEmail TEXT not null," +
                "UserName TEXT not null," +
                "UserPassword TEXT not null," +
                "PurchaseCount INTEGER)";
        sqLiteDatabase.execSQL(query);

        query = "CREATE TABLE TransactionDatas (TransID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "UserID INTEGER not null," +
                "BookID TEXT not null," +
                "TransDate DATE not null," +
                "TransQnty INTEGER not null," +
                "FOREIGN KEY (UserID) REFERENCES UserDatas (UserID)," +
                "FOREIGN KEY (BookID) REFERENCES BookDatas (BookID))";
        sqLiteDatabase.execSQL(query);

        query = "CREATE TABLE BookDatas (BookID TEXT PRIMARY KEY," +
                "BookName TEXT not null," +
                "BookAuthor TEXT not null," +
                "BookRating REAL not null," +
                "BookPrice INTEGER not null," +
                "BookImage TEXT not null," +
                "BookDesc TEXT not null)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        query = "DROP TABLE IF EXISTS UserDatas";
        sqLiteDatabase.execSQL(query);
        query = "DROP TABLE IF EXISTS TransactionDatas";
        sqLiteDatabase.execSQL(query);
        query = "DROP TABLE IF EXISTS BookDatas";
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }
}
