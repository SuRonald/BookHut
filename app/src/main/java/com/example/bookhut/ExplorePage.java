package com.example.bookhut;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bookhut.data.BookData;
import com.example.bookhut.helper.BookDataHelper;
import com.example.bookhut.recyclerview.BookAdapter;
import com.example.bookhut.recyclerview.ExploreAdapter;

import java.util.Vector;

import static com.example.bookhut.DataVault.bookData;

public class ExplorePage extends AppCompatActivity {

    TextView filAuth;
    TextView filName;
    static TextView noDataFound;
    EditText searchField;
    RecyclerView recyclerView;
    BookDataHelper BHelper;

    Integer flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_page);

        init();

        filAuth.setOnClickListener(v -> {
            filAuth.setTextColor(ContextCompat.getColor(this, R.color.dark_green));
            filAuth.setTypeface(ResourcesCompat.getFont(this, R.font.lato_bold));
            flag = 1;
        });

        filName.setOnClickListener(v -> {
            filName.setTextColor(ContextCompat.getColor(this, R.color.dark_green));
            filName.setTypeface(ResourcesCompat.getFont(this, R.font.lato_bold));
            flag = 2;
        });

        Vector<BookData> result = new Vector<>();
        searchField.setFocusableInTouchMode(true);
        searchField.requestFocus();
        searchField.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)){
                    String inpSearch = searchField.getText().toString();
                    if (flag == 1){
                        ExploreAdapter adapter = new ExploreAdapter(ExplorePage.this, BHelper.authSearch(inpSearch), new ExploreAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BookData books, int position) {
                                Intent moveDetail = new Intent(ExplorePage.this, DetailPage.class);
                                bookData = books;
                                startActivity(moveDetail);
                            }
                        });
                        recyclerView.setAdapter(adapter);
                        LinearLayoutManager manager = new LinearLayoutManager(ExplorePage.this, LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(manager);
                    }
                }
                return false;
            }

        });

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
    public static void noDataHide(){
        noDataFound.setVisibility(View.INVISIBLE);
    }
}