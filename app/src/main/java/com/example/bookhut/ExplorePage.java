package com.example.bookhut;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookhut.data.BookData;
import com.example.bookhut.helper.BookDataHelper;
import com.example.bookhut.recyclerview.ExploreAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Vector;

import static com.example.bookhut.DataVault.bookData;

public class ExplorePage extends AppCompatActivity {

    TextView filAuth;
    TextView filName;
    static TextView noDataFound;
    EditText searchField;
    RecyclerView recyclerView;
    BottomNavigationView bottomNavigationView;
    BookDataHelper BHelper;
    Intent movePage;

    Integer flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_page);

        init();

        filName.setTextColor(ContextCompat.getColor(this, R.color.dark_green));
        filName.setTypeface(ResourcesCompat.getFont(this, R.font.lato_bold));
        flag = 1;

        BHelper.open();
        ExploreAdapter adapter = new ExploreAdapter(ExplorePage.this, BHelper.viewBooks());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(ExplorePage.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        BHelper.close();

        filAuth.setOnClickListener(v -> {
            if (filAuth.getCurrentTextColor() != 0xFF116557){
                filAuth.setTextColor(ContextCompat.getColor(this, R.color.dark_green));
                filAuth.setTypeface(ResourcesCompat.getFont(this, R.font.lato_bold));
                flag = 2;
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
                flag = 1;
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
                    hideKeyboard();
                    if (flag == 2){
                        BHelper.open();
                        ExploreAdapter adapter = new ExploreAdapter(ExplorePage.this, BHelper.authSearch(inpSearch));
                        recyclerView.setAdapter(adapter);
                        LinearLayoutManager manager = new LinearLayoutManager(ExplorePage.this, LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(manager);
                        BHelper.close();
                    }
                    else if (flag == 1){
                        BHelper.open();
                        ExploreAdapter adapter = new ExploreAdapter(ExplorePage.this, BHelper.nameSearch(inpSearch));
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

    private void hideKeyboard(){
        View v = this.getCurrentFocus();
        if (v != null){
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    public void init(){
        filAuth = findViewById(R.id.filterAuth);
        filName = findViewById(R.id.filterName);
        noDataFound = findViewById(R.id.noResult);

        searchField = findViewById(R.id.searchItem);

        BHelper = new BookDataHelper(this);
        recyclerView = findViewById(R.id.rvResult);

        bottomNavigationView = findViewById(R.id.bottomNavBar);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
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

    public static void noDataShow(){
        noDataFound.setVisibility(View.VISIBLE);
    }
    public static void noDataHide(){
        noDataFound.setVisibility(View.INVISIBLE);
    }

}