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

        query = "INSERT INTO BookDatas VALUES(1, 'Harry Potter and the Philosophers Stone', 'J. K. Rowling', 4, 100000, 'https://kbimages1-a.akamaihd.net/93affabc-5161-421e-80d5-4477a07b8cee/1200/1200/False/harry-potter-and-the-philosopher-s-stone-3.jpg', 'BookDesc')";
        sqLiteDatabase.execSQL(query);

        query = "INSERT INTO BookDatas VALUES(2, 'Wingit', 'Sara Wijayanto', 4, 100000, 'https://ebooks.gramedia.com/ebook-covers/64894/image_highres/BLK_W2021854429.jpg', 'BookDesc')";
        sqLiteDatabase.execSQL(query);

        query = "INSERT INTO BookDatas VALUES(3, 'Mantappu Jiwa', 'Jerome Polin Sijabat', 5, 100000, 'https://ebooks.gramedia.com/ebook-covers/48730/image_highres/ID_MJ2019MTH08MJ.jpg', 'BookDesc')";
        sqLiteDatabase.execSQL(query);

        query = "INSERT INTO BookDatas VALUES(4, 'The Hobbit', 'J. R. R. Tolkein', 4, 100000, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTybJC9ifnxvrDglfvWmBnxKN7rzMsx2vua8O9NFdbKV44iOtEpDZGAHc33eFAu_hfBBKg&usqp=CAU', 'BookDesc')";
        sqLiteDatabase.execSQL(query);

        query = "INSERT INTO BookDatas VALUES(5, 'Crazy Rich Asians', 'Kevin Kwan', 5, 100000, 'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1526735379l/18158562._SY475_.jpg', 'BookDesc')";
        sqLiteDatabase.execSQL(query);

        query = "INSERT INTO BookDatas VALUES(6, 'The Hunger Game', 'Suzanne Collins', 4, 100000, 'https://awsimages.detik.net.id/community/media/visual/2020/07/12/the-hunger-games-mockingjay-part-1_43.jpeg?w=700&q=90', 'BookDesc')";
        sqLiteDatabase.execSQL(query);

        query = "INSERT INTO BookDatas VALUES(7, 'Charlie and The Chocolate Factory', 'Roald Dahl', 5, 100000, 'https://m.media-amazon.com/images/M/MV5BNjcxMjg1Njg2NF5BMl5BanBnXkFtZTcwMjQ4NzMzMw@@._V1_.jpg', 'BookDesc')";
        sqLiteDatabase.execSQL(query);

        query = "INSERT INTO BookDatas VALUES(8, 'Pinokio Adventure', 'Carlo Collodi', 4, 100000, 'https://4.bp.blogspot.com/-SXEMqGXBzuw/WZmfrcS4-0I/AAAAAAAAJdg/njNIGnr9r8sWrVxVGRZaoGjNzndePiKdgCLcBGAs/s1600/the%2Badventures%2Bof%2Bpinocchio%2B-%2Bcarlo%2Bcollodi.jpg', 'BookDesc')";
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
