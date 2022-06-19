package com.example.bookhut;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bookhut.helper.TransactionDataHelper;
import com.example.bookhut.helper.UserDataHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.bookhut.DataVault.bookData;
import static com.example.bookhut.DataVault.currUser;

public class DetailPage extends AppCompatActivity implements View.OnClickListener {

    TransactionDataHelper THelper = new TransactionDataHelper(this);
    UserDataHelper UHelper = new UserDataHelper(this);
    CardView backBtn;
    Button buyBtn;
    ImageView bookImage, minBtn, plusBtn;
    TextView bookTitle, bookAuthor, bookRating, bookPrice, bookDesc, qnty;
    Intent movePage;
    int quantity = 0;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(this);
        buyBtn = findViewById(R.id.buyBtn);
        buyBtn.setOnClickListener(this);
        minBtn = findViewById(R.id.minBtn);
        minBtn.setOnClickListener(this);
        plusBtn = findViewById(R.id.plusBtn);
        plusBtn.setOnClickListener(this);
        bookImage = findViewById(R.id.bookImage);
        Glide.with(this).load(bookData.getBookImage()).into(bookImage);
        bookTitle = findViewById(R.id.bookTitle);
        bookTitle.setText(bookData.getBookName());
        bookAuthor = findViewById(R.id.bookAuthor);
        bookAuthor.setText(bookData.getBookAuthor());
        bookRating = findViewById(R.id.bookRating);
        bookRating.setText(bookData.getBookRating().toString());
        bookPrice = findViewById(R.id.bookPrice);
        bookPrice.setText("Rp. " + bookData.getBookPrice());
        bookDesc = findViewById(R.id.bookDesc);
        bookDesc.setText(bookData.getBookDesc());
        qnty = findViewById(R.id.qnty);
        qnty.setText("" + quantity);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.backBtn){
//            movePage = new Intent(this, HomePage.class);
//            startActivity(movePage);
            onBackPressed();
        }
        else if (view.getId() == R.id.minBtn && quantity > 0){
            quantity--;
            qnty.setText("" + quantity);
        }
        else if (view.getId() == R.id.plusBtn){
            quantity++;
            qnty.setText("" + quantity);
        }
        else if (view.getId() == R.id.buyBtn){
            if (quantity != 0 ){
                date = simpleDateFormat.format(new Date());
                THelper.open();
                THelper.insertTransaction(currUser.getUserID(), bookData.getBookID(), date, quantity);
                THelper.close();
                currUser.setPurchaseCount(currUser.getPurchaseCount() + 1);
                UHelper.open();
                UHelper.updateCount(currUser.getUserID(), currUser.getPurchaseCount());
                UHelper.close();
                Toast.makeText(this, "Book(s) added to Transaction", Toast.LENGTH_SHORT).show();
                movePage = new Intent(this, HomePage.class);
                startActivity(movePage);
            }
            else {
                Toast.makeText(this, "Quantity can't be 0!!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}