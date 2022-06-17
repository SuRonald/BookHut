package com.example.bookhut;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bookhut.data.BookData;
import com.example.bookhut.helper.BookDataHelper;
import com.example.bookhut.recyclerview.BookAdapter;
import com.example.bookhut.recyclerview.ExploreAdapter;

import static com.example.bookhut.DataVault.bookData;

public class ExplorePage extends AppCompatActivity {

    TextView filAuth;
    TextView filName;
    static TextView noDataFound;
    EditText searchField;
    RecyclerView recyclerView;
    BookDataHelper BHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_page);

        init();
        ExploreAdapter adapter = new ExploreAdapter(this, BHelper.viewBooks(), new ExploreAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BookData books, int position) {
                Intent moveDetail = new Intent(ExplorePage.this, DetailPage.class);
                //declare selected book to bookData
                startActivity(moveDetail);
            }
        });
        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

    }
    public void init(){
        filAuth = findViewById(R.id.filterAuth);
        filName = findViewById(R.id.filterName);
        noDataFound = findViewById(R.id.noResult);

        searchField = findViewById(R.id.searchItem);

        BHelper = new BookDataHelper(this);
        recyclerView = findViewById(R.id.rvResult);
    }

    public static void noDataShow(){
        noDataFound.setVisibility(View.VISIBLE);
    }
}