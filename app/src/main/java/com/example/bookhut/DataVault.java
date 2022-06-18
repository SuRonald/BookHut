package com.example.bookhut;

import com.example.bookhut.data.BookData;
import com.example.bookhut.data.TransactionData;
import com.example.bookhut.data.UserData;

import java.util.Vector;

public class DataVault {
    public static Vector<BookData> bookList = new Vector<>();
    public static BookData bookData = new BookData();
    public static UserData currUser;
}
