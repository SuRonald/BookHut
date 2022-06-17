package com.example.bookhut.data;

public class BookData {
    int bookID;
    String bookName;
    String bookAuthor;
    int bookRating;
    int bookPrice;
    String bookImage;
    String bookDesc;

    public BookData(int bookID, String bookName, String bookAuthor, int bookRating, int bookPrice, String bookImage, String bookDesc) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookRating = bookRating;
        this.bookPrice = bookPrice;
        this.bookImage = bookImage;
        this.bookDesc = bookDesc;
    }

    public int getBookID() {
        return bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public int getBookRating() {
        return bookRating;
    }

    public int getBookPrice() {
        return bookPrice;
    }

    public String getBookImage() {
        return bookImage;
    }

    public String getBookDesc() {
        return bookDesc;
    }
}
