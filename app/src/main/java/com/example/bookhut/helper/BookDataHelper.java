package com.example.bookhut.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.bookhut.data.BookData;
import com.example.bookhut.database.DatabaseHelper;

import java.util.Vector;

public class BookDataHelper {
    String query;
    Cursor cursor;

    private Context ctx;
    private DatabaseHelper helper;
    private SQLiteDatabase db;

    public BookDataHelper(Context ctx) {
        this.ctx = ctx;
    }

    public void open() throws SQLException {
        helper = new DatabaseHelper(ctx);
        db = helper.getWritableDatabase();
    }

    public void close() throws SQLException {
        helper.close();
    }

    public Vector<BookData> viewBooks() {
        query = "SELECT * FROM BookDatas";
        cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        Vector<BookData> tempList = new Vector<>();
        int bookID;
        String bookName;
        String bookAuthor;
        int bookRating;
        int bookPrice;
        String bookImage;
        String bookDesc;

        if (cursor.getCount() > 0){
            do {
                bookID = cursor.getInt(cursor.getColumnIndexOrThrow("BookID"));
                bookName = cursor.getString(cursor.getColumnIndexOrThrow("BookName"));
                bookAuthor = cursor.getString(cursor.getColumnIndexOrThrow("BookAuthor"));
                bookRating = cursor.getInt(cursor.getColumnIndexOrThrow("BookRating"));
                bookPrice = cursor.getInt(cursor.getColumnIndexOrThrow("BookPrice"));
                bookImage = cursor.getString(cursor.getColumnIndexOrThrow("BookImage"));
                bookDesc = cursor.getString(cursor.getColumnIndexOrThrow("BookDesc"));

                tempList.add(new BookData(bookID, bookName, bookAuthor, bookRating, bookPrice, bookImage, bookDesc));

                cursor.moveToNext();
            } while (!cursor.isAfterLast());

        }

        cursor.close();
        return tempList;
    }

    public int countBooks() {
        query = "SELECT * FROM BookDatas";
        cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    public void insertBook(int bookID, String bookName, String bookAuthor, int bookRating, int bookPrice, String bookImage, String bookDesc){
        query = "INSERT INTO BookDatas VALUES (" + bookID + ", '" + bookName + "', '" + bookAuthor + "', " + bookRating + ", " + bookPrice + ", '" + bookImage + "', '" + bookDesc + "')";
        db.execSQL(query);
    }
}
