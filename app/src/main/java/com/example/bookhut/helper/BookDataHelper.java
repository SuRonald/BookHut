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
        Double bookRating;
        int bookPrice;
        String bookImage;
        String bookDesc;

        if (cursor.getCount() > 0){
            do {
                bookID = cursor.getInt(cursor.getColumnIndexOrThrow("BookID"));
                bookName = cursor.getString(cursor.getColumnIndexOrThrow("BookName"));
                bookAuthor = cursor.getString(cursor.getColumnIndexOrThrow("BookAuthor"));
                bookRating = cursor.getDouble(cursor.getColumnIndexOrThrow("BookRating"));
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

    public Vector<BookData> authSearch(String author) {
        Vector<BookData> search = new Vector<>();
        db = helper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM BookDatas WHERE BookAuthor LIKE '%" + author + "%'", null);
        cursor.moveToFirst();

        BookData temp;
        String tempName, tempAuthor, tempDesc, tempImg;
        Integer tempID, tempPrice; Double tempRating;

        if (cursor != null && cursor.getCount() > 0){
            do {
                tempImg = cursor.getString(cursor.getColumnIndexOrThrow("BookImage"));
                tempID = cursor.getInt(cursor.getColumnIndexOrThrow("BookID"));
                tempName = cursor.getString(cursor.getColumnIndexOrThrow("BookName"));
                tempAuthor = cursor.getString(cursor.getColumnIndexOrThrow("BookAuthor"));
                tempDesc = cursor.getString(cursor.getColumnIndexOrThrow("BookDesc"));
                tempPrice = cursor.getInt(cursor.getColumnIndexOrThrow("BookPrice"));
                tempRating = cursor.getDouble(cursor.getColumnIndexOrThrow("BookRating"));

                temp = new BookData(tempID, tempName, tempAuthor, tempRating, tempPrice, tempImg, tempDesc);
                search.add(temp);

                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        return search;
    }

    public Vector<BookData> nameSearch(String name) {
        Vector<BookData> search = new Vector<>();
        db = helper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM BookDatas WHERE BookName LIKE '%" + name + "%'", null);
        cursor.moveToFirst();

        BookData temp;
        String tempName, tempAuthor, tempDesc, tempImg;
        Integer tempID, tempPrice; Double tempRating;

        if (cursor != null && cursor.getCount() > 0){
            do {
                tempImg = cursor.getString(cursor.getColumnIndexOrThrow("BookImage"));
                tempID = cursor.getInt(cursor.getColumnIndexOrThrow("BookID"));
                tempName = cursor.getString(cursor.getColumnIndexOrThrow("BookName"));
                tempAuthor = cursor.getString(cursor.getColumnIndexOrThrow("BookAuthor"));
                tempDesc = cursor.getString(cursor.getColumnIndexOrThrow("BookDesc"));
                tempPrice = cursor.getInt(cursor.getColumnIndexOrThrow("BookPrice"));
                tempRating = cursor.getDouble(cursor.getColumnIndexOrThrow("BookRating"));

                temp = new BookData(tempID, tempName, tempAuthor, tempRating, tempPrice, tempImg, tempDesc);
                search.add(temp);

                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }

        return search;
    }

    public Vector<BookData> allSearch(String input) {
        Vector<BookData> search = new Vector<>();
        db = helper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM BookDatas " +
                "WHERE BookName LIKE '%" + input + "%' || BookAuthor LIKE '%" + input + "%'" , null);
        cursor.moveToFirst();

        BookData temp;
        String tempName, tempAuthor, tempDesc, tempImg;
        Integer tempID, tempPrice; Double tempRating;

        if (cursor != null && cursor.getCount() > 0){
            do {
                tempImg = cursor.getString(cursor.getColumnIndexOrThrow("BookImage"));
                tempID = cursor.getInt(cursor.getColumnIndexOrThrow("BookID"));
                tempName = cursor.getString(cursor.getColumnIndexOrThrow("BookName"));
                tempAuthor = cursor.getString(cursor.getColumnIndexOrThrow("BookAuthor"));
                tempDesc = cursor.getString(cursor.getColumnIndexOrThrow("BookDesc"));
                tempPrice = cursor.getInt(cursor.getColumnIndexOrThrow("BookPrice"));
                tempRating = cursor.getDouble(cursor.getColumnIndexOrThrow("BookRating"));

                temp = new BookData(tempID, tempName, tempAuthor, tempRating, tempPrice, tempImg, tempDesc);
                search.add(temp);

                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }

        return search;
    }
}
