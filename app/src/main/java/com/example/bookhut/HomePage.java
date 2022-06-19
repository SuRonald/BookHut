package com.example.bookhut;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.bookhut.recyclerview.PopularAdapter;
import com.example.bookhut.recyclerview.TrendingAdapter;
import com.example.bookhut.data.BookData;
import com.example.bookhut.helper.BookDataHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Vector;

public class HomePage extends AppCompatActivity {

    BookDataHelper BHelper = new BookDataHelper(this);
    BottomNavigationView bottomNavigationView;
    Intent movePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        BHelper.open();
        Vector<BookData> tempBookList = BHelper.viewBooks();
        BHelper.close();
        Vector<BookData> popularList = new Vector<>();
        Vector<BookData> trendingList = new Vector<>();
        BookData temp;

        for (int i = 0; i < 3; i = i+2){
            temp = tempBookList.get(i);
            popularList.add(temp);
            temp = tempBookList.get(i+1);
            trendingList.add(temp);
        }

        RecyclerView popularrv = findViewById(R.id.popularList);
        PopularAdapter popularAdapter = new PopularAdapter(this);
        popularAdapter.setBooks(popularList);
        popularrv.setAdapter(popularAdapter);
        popularrv.setLayoutManager(new GridLayoutManager(this, 2));

        RecyclerView trendingrv = findViewById(R.id.trendingList);
        TrendingAdapter trendingAdapter = new TrendingAdapter(this);
        trendingAdapter.setBooks(trendingList);
        trendingrv.setAdapter(trendingAdapter);
        trendingrv.setLayoutManager(new GridLayoutManager(this, 1));

        bottomNavigationView = findViewById(R.id.bottomNavBar);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.homePack:
                    movePage = new Intent(this, HomePage.class);
                    startActivity(movePage);
                    break;

                case R.id.historyPack:
                    movePage = new Intent(this, HistoryPage.class);
                    startActivity(movePage);
                    break;

                case R.id.explorePack:
                    movePage = new Intent(this, ExplorePage.class);
                    startActivity(movePage);
                    break;

                case R.id.profilePack:
                    movePage = new Intent(this, ProfilePage.class);
                    startActivity(movePage);
                    break;
            }
            return true;
        });
    }
}