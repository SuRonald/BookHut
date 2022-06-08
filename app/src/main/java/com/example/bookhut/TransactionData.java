package com.example.bookhut;

public class TransactionData {
    String transID;
    int userID;
    int bookID;
    String transDate;
    int transQnty;

    public TransactionData(String transID, int userID, int bookID, String transDate, int transQnty) {
        this.transID = transID;
        this.userID = userID;
        this.bookID = bookID;
        this.transDate = transDate;
        this.transQnty = transQnty;
    }

    public String getTransID() {
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
