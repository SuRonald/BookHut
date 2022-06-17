package com.example.bookhut;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.bookhut.data.BookData;
import com.example.bookhut.helper.BookDataHelper;

import java.util.Vector;

public class HomePage extends AppCompatActivity implements View.OnClickListener {

    BookDataHelper BHelper = new BookDataHelper(this);
    LinearLayout home, history, profile;
    Intent movePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        BHelper.open();
        Vector<BookData> tempBookList = BHelper.viewBooks();
        BHelper.close();
        Vector<BookData> poopularList = new Vector<>();
        Vector<BookData> trendingList = new Vector<>();
        BookData temp;

        for (int i = 0; i < 3; i = i+2){
            temp = tempBookList.get(i);
            poopularList.add(temp);
            temp = tempBookList.get(i+1);
            trendingList.add(temp);
        }

        RecyclerView popularrv = findViewById(R.id.popularList);
        PopularAdapter popularAdapter = new PopularAdapter(this);
        popularAdapter.setBooks(poopularList);
        popularrv.setAdapter(popularAdapter);
        popularrv.setLayoutManager(new GridLayoutManager(this, 2));

        RecyclerView trendingrv = findViewById(R.id.trendingList);
        TrendingAdapter trendingAdapter = new TrendingAdapter(this);
        trendingAdapter.setBooks(trendingList);
        trendingrv.setAdapter(trendingAdapter);
        trendingrv.setLayoutManager(new GridLayoutManager(this, 1));

        home = findViewById(R.id.homePack);
        home.setOnClickListener(this);
        history = findViewById(R.id.historyPack);
        history.setOnClickListener(this);
        profile = findViewById(R.id.userPack);
        profile.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.homePack){
            movePage = new Intent(view.getContext(), HomePage.class);
            startActivity(movePage);
        }
        else if (view.getId() == R.id.historyPack){
            movePage = new Intent(view.getContext(), HistoryPage.class);
            startActivity(movePage);
        }
        else if (view.getId() == R.id.userPack){
            movePage = new Intent(view.getContext(), ProfilePage.class);
            startActivity(movePage);
        }
    }
}