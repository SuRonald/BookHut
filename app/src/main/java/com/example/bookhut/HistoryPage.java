package com.example.bookhut;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.bookhut.data.TransactionData;
import com.example.bookhut.helper.TransactionDataHelper;
import com.example.bookhut.recyclerview.TransactionAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Vector;

import static com.example.bookhut.DataVault.currUser;

public class HistoryPage extends AppCompatActivity {

    TransactionDataHelper THelper = new TransactionDataHelper(this);
    BottomNavigationView bottomNavigationView;
    Intent movePage;
    Vector<TransactionData> tempTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_page);

        THelper.open();
        tempTransaction = THelper.viewTransactions(currUser.getUserID());
        THelper.close();

        RecyclerView transactionrv = findViewById(R.id.transList);
        TransactionAdapter transactionAdapter = new TransactionAdapter(this);
        transactionAdapter.setTransactions(tempTransaction);
        transactionrv.setAdapter(transactionAdapter);
        transactionrv.setLayoutManager(new GridLayoutManager(this, 1));

        bottomNavigationView = findViewById(R.id.bottomNavBar);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
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