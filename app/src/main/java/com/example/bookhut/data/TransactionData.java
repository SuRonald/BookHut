package com.example.bookhut.data;

public class TransactionData {
    int transID;
    int userID;
    int bookID;
    String transDate;
    int transQnty;

    public TransactionData(int transID, int userID, int bookID, String transDate, int transQnty) {
        this.transID = transID;
        this.userID = userID;
        this.bookID = bookID;
        this.transDate = transDate;
        this.transQnty = transQnty;
    }

    public void setTransID(int transID) {
        this.transID = transID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public void setTransQnty(int transQnty) {
        this.transQnty = transQnty;
    }

    public int getTransID() {
        return transID;
    }

    public int getUserID() {
        return userID;
    }

    public int getBookID() {
        return bookID;
    }

    public String getTransDate() {
        return transDate;
    }

    public int getTransQnty() {
        return transQnty;
    }
}
