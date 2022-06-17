package com.example.bookhut.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bookhut.data.UserData;
import com.example.bookhut.database.DatabaseHelper;

public class UserDataHelper {
    String query;
    Cursor cursor;

    private Context ctx;
    private DatabaseHelper helper;
    private SQLiteDatabase db;

    public UserDataHelper(Context ctx) {
        this.ctx = ctx;
    }

    public void open() throws SQLException {
        helper = new DatabaseHelper(ctx);
        db = helper.getWritableDatabase();
    }

    public void close() throws SQLException{
        helper.close();
    }

    public UserData viewUser(String email, String password) {
        query = "SELECT * FROM UserDatas";
        cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        UserData tempList = null;
        int userID;
        String userEmail;
        String userName;
        String userPassword;
        int purchaseCount;

        if (cursor.getCount() > 0){
            do {
                userEmail = cursor.getString(cursor.getColumnIndexOrThrow("UserEmail"));
                userPassword = cursor.getString(cursor.getColumnIndexOrThrow("UserPassword"));
                if (userEmail.equals(email) && userPassword.equals(password)){
                    userID = cursor.getInt(cursor.getColumnIndexOrThrow("UserID"));
                    userName = cursor.getString(cursor.getColumnIndexOrThrow("UserName"));
                    purchaseCount = cursor.getInt(cursor.getColumnIndexOrThrow("PurchaseCount"));

                    tempList = new UserData(userID, userEmail, userName, userPassword, purchaseCount);
                }

                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }

        cursor.close();
        return tempList;
    }

    public int countUsers() {
        query = "SELECT * FROM UserDatas";
        cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    public void insertUser(int userID, String userEmail, String userName, String userPassword, int purchaseCount){
        query = "INSERT INTO UserDatas VALUES (" + userID + ", '" + userEmail + "', '" + userName + "', '" +  userPassword + "', " + purchaseCount + ")";
        db.execSQL(query);
        Log.d("CekUser", userID + " " + userEmail + " " + userName + " " + userPassword + " " + purchaseCount);
    }

    public void updateCount(int userID, int purchaseCount){
        query = "UPDATE UserDatas SET PurchaseCount = " + purchaseCount + " WHERE UserID = " + userID;
        cursor = db.rawQuery(query, null);

        if (cursor != null){
            db.execSQL(query);
        }
    }

    public void deleteUser(int userID){
        query = "DELETE FROM UserDatas WHERE UserID = " + userID;
        cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        while (cursor != null && !cursor.isAfterLast()){
            db.execSQL(query);
            cursor.moveToNext();
        }
    }
}
