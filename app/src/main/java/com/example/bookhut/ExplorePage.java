package com.example.bookhut;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    Integer flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_page);

        init();

        filAuth.setOnClickListener(v -> {
            if (filAuth.getCurrentTextColor() != 0xFF116557){
                filAuth.setTextColor(ContextCompat.getColor(this, R.color.dark_green));
                filAuth.setTypeface(ResourcesCompat.getFont(this, R.font.lato_bold));
                flag = 1;
                filName.setTextColor(ContextCompat.getColor(this, android.R.color.tab_indicator_text));
                filName.setTypeface(ResourcesCompat.getFont(this, R.font.lato_regular));
            }
            else if (filAuth.getCurrentTextColor() == 0xFF116557){
                filAuth.setTextColor(ContextCompat.getColor(this, android.R.color.tab_indicator_text));
                filAuth.setTypeface(ResourcesCompat.getFont(this, R.font.lato_regular));
                flag = 0;
            }
        });

        filName.setOnClickListener(v -> {
            if (filName.getCurrentTextColor() != 0xFF116557){
                filName.setTextColor(ContextCompat.getColor(this, R.color.dark_green));
                filName.setTypeface(ResourcesCompat.getFont(this, R.font.lato_bold));
                flag = 2;
                filAuth.setTextColor(ContextCompat.getColor(this, android.R.color.tab_indicator_text));
                filAuth.setTypeface(ResourcesCompat.getFont(this, R.font.lato_regular));
            }
            else if (filName.getCurrentTextColor() == 0xFF116557){
                filName.setTextColor(ContextCompat.getColor(this, android.R.color.tab_indicator_text));
                filName.setTypeface(ResourcesCompat.getFont(this, R.font.lato_regular));
                flag = 0;
            }
        });

        searchField.setFocusableInTouchMode(true);
        searchField.requestFocus();
        searchField.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)){
                    String inpSearch = searchField.getText().toString();
                    if (flag == 1){
                        BHelper.open();
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
                        BHelper.close();
                    }
                    else if (flag == 2){
                        BHelper.open();
                        ExploreAdapter adapter = new ExploreAdapter(ExplorePage.this, BHelper.nameSearch(inpSearch), new ExploreAdapter.OnItemClickListener() {
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
                        BHelper.close();
                    }
                    else if (flag == 0){
                        searchField.setError("Please select the search by options!");
                        Toast.makeText(getApplicationContext(),"Please select the search by options first",  Toast.LENGTH_SHORT).show();
                    }
                }
                return false;
            }

        });

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