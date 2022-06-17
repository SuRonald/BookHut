package com.example.bookhut.helper;

import android.content.ContentValues;
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

    public UserData checkEmail(String email){
        cursor = db.rawQuery("SELECT * FROM UserDatas WHERE UserEmail = ?", new String[]{email});
        UserData temp = null;
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            temp = new UserData();
            temp.setUserID(cursor.getInt(0));
            temp.setUserEmail(cursor.getString(1));
            temp.setUserName(cursor.getString(2));
            temp.setUserPassword(cursor.getString(3));
            temp.setPurchaseCount(cursor.getInt(4));
            cursor.close();
        }
        return temp;
    }

    public void insertUser(String userEmail, String userName, String userPassword, int purchaseCount){
        ContentValues cv = new ContentValues();
        cv.put("UserEmail", userEmail);
        cv.put("UserName", userName);
        cv.put("UserPassword", userPassword);
        cv.put("PurchaseCount", purchaseCount);

        db.insert("UserDatas", null, cv);
//        query = "INSERT INTO UserDatas VALUES ('" + userEmail + "', '" + userName + "', '" +  userPassword + "', " + purchaseCount + ")";
//        db.execSQL(query);
        Log.d("CekUser", userEmail + " " + userName + " " + userPassword + " " + purchaseCount);
    }

    public void updateCount(int userID, int purchaseCount){
        query = "UPDATE UserDatas SET PurchaseCount = " + purchaseCount + " WHERE UserID = " + userID;
        cursor = db.rawQuery(query, null);

        if (cursor != null){
            db.execSQL(query);
        }
    }

    public void updateName(int userID, String userName){
        query = "UPDATE UserDatas SET UserName = '" + userName + "' WHERE UserID = " + userID;
        cursor = db.rawQuery(query, null);

        if (cursor != null){
            db.execSQL(query);
        }
    }

    public void updateEmail(int userID, String userEmail){
        query = "UPDATE UserDatas SET UserEmail = '" + userEmail + "' WHERE UserID = " + userID;
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
