package com.example.bookhut.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    String query;

    private static final String database_name = "BookHut";
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
                "BookID INTEGER not null," +
                "TransDate TEXT not null," +
                "TransQnty INTEGER not null," +
                "FOREIGN KEY (UserID) REFERENCES UserDatas (UserID)," +
                "FOREIGN KEY (BookID) REFERENCES BookDatas (BookID))";
        sqLiteDatabase.execSQL(query);

        query = "CREATE TABLE BookDatas (BookID INTEGER PRIMARY KEY," +
                "BookName TEXT not null," +
                "BookAuthor TEXT not null," +
                "BookRating REAL not null," +
                "BookPrice INTEGER not null," +
                "BookImage TEXT not null," +
                "BookDesc TEXT not null)";
        sqLiteDatabase.execSQL(query);

        query = "INSERT INTO BookDatas VALUES(1, 'The Hobbit', 'J. R. R. Tolkein', 4, 400000, 'https://upload.wikimedia.org/wikipedia/id/b/b3/The_Hobbit-_An_Unexpected_Journey.jpeg', 'The Hobbit is set within Tolkiens fictional universe and follows the quest of home-loving Bilbo Baggins, the titular hobbit, to win a share of the treasure guarded by a dragon named Smaug. Bilbos journey takes him from his light-hearted, rural surroundings into more sinister territory.')";
        sqLiteDatabase.execSQL(query);

        query = "INSERT INTO BookDatas VALUES(2, 'Wingit', 'Sara Wijayanto', 5, 100000, 'https://ebooks.gramedia.com/ebook-covers/64894/image_highres/BLK_W2021854429.jpg', 'The search for an abandoned old housing complex in the East Jakarta area that night was initially pleasant. Before entering the complex area, I and Wisnu, Fadi, and Demian opened a vlog with fun gimmicks to lighten the mood. However, when I arrived at a terraced house location surrounded by trees and shrubs, I saw more and more invisible creatures that took me by surprise.')";
        sqLiteDatabase.execSQL(query);

        query = "INSERT INTO BookDatas VALUES(3, 'Mantappu Jiwa', 'Jerome Polin Sijabat', 5, 100000, 'https://ebooks.gramedia.com/ebook-covers/48730/image_highres/ID_MJ2019MTH08MJ.jpg', 'Mantappu Jiwa berisi kisah hidup Jerome sampai bisa kuliah di Jepang dan menjadi Youtuber terkenal. Rupanya keinginan Jerome untuk kuliah sudah tercetus sejak dia SD, karena alasan yang sangat sederhana: dia ingin pergi ke luar negeri seperti teman-temannya.')";
        sqLiteDatabase.execSQL(query);

        query = "INSERT INTO BookDatas VALUES(4, 'The Hunger Game', 'Suzanne Collins', 3, 100000, 'https://images-na.ssl-images-amazon.com/images/I/51SwRcAjzhL.jpg', 'In a time when North America was wiped out, and the former state of Panem was founded with the Capitol as the city center surrounded by 12 districts, a game called The Hunger Games was held which must be followed by 24 teenagers from all districts. The Hunger Games are named because the winning participants and the districts they represent will receive prizes in the form of abundant food and guarantees of a better life.')";
        sqLiteDatabase.execSQL(query);

        query = "INSERT INTO BookDatas VALUES(5, 'Crazy Rich Asians', 'Kevin Kwan', 5, 100000, 'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1526735379l/18158562._SY475_.jpg', 'Crazy Rich Asians is a funny fiction novel written by Kevin Kwan that highlights and ridicules the lifestyle of upper-class Asian families and society. The book is about three wealthy, upper-class Chinese families, and how they undermine those who are not as rich and influential as them.')";
        sqLiteDatabase.execSQL(query);

        query = "INSERT INTO BookDatas VALUES(6, 'Harry Potter and the Philosophers Stone', 'J. K. Rowling', 4, 400000, 'https://kbimages1-a.akamaihd.net/93affabc-5161-421e-80d5-4477a07b8cee/1200/1200/False/harry-potter-and-the-philosopher-s-stone-3.jpg', 'One night, Albus Dumbledore and Minerva McGonagall, professors at Hogwarts, with Rubeus Hagrid deliver orphaned infant named Harry Potter to his remaining relatives, the Dursleys. Ten years later, Harry Potter (Daniel Radcliffe) begins receiving strange letters by owls. He and his relatives escape to an island from intrusive letters.')";
        sqLiteDatabase.execSQL(query);

        query = "INSERT INTO BookDatas VALUES(7, 'Charlie and The Chocolate Factory', 'Roald Dahl', 4, 300000, 'https://m.media-amazon.com/images/M/MV5BNjcxMjg1Njg2NF5BMl5BanBnXkFtZTcwMjQ4NzMzMw@@._V1_.jpg', 'Charlie Bucket lives on the outskirts of town with his poverty-stricken family: his parents and all four grandparents. Each day on his way to school, Charlie passes the best and biggest chocolate factory in the world, run by the secretive Willy Wonka. When Charlies father loses his job, things go from bad to worse.')";
        sqLiteDatabase.execSQL(query);

        query = "INSERT INTO BookDatas VALUES(8, 'Pinokio Adventure', 'Carlo Collodi', 3, 100000, 'https://4.bp.blogspot.com/-SXEMqGXBzuw/WZmfrcS4-0I/AAAAAAAAJdg/njNIGnr9r8sWrVxVGRZaoGjNzndePiKdgCLcBGAs/s1600/the%2Badventures%2Bof%2Bpinocchio%2B-%2Bcarlo%2Bcollodi.jpg', 'Seorang pria miskin bernama Geppetto ingin mengukir boneka untuk mencari nafkah sebagai dalang. Dia diberi sepotong kayu ajaib, dan segera setelah Geppetto mengukir boneka itu, yang dia beri namaPinokio , itu mulai melecehkan orang tua itu. Setelah kakinya dibuat, Pinokio melarikan diri, dan Geppetto ditangkap saat dia merebut boneka itu.')";
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
