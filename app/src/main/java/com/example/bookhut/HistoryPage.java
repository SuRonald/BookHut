package com.example.bookhut;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.bookhut.data.TransactionData;
import com.example.bookhut.helper.TransactionDataHelper;
import com.example.bookhut.recyclerview.TransactionAdapter;

import java.util.Vector;

import static com.example.bookhut.DataVault.currUser;

public class HistoryPage extends AppCompatActivity implements View.OnClickListener {

    TransactionDataHelper THelper = new TransactionDataHelper(this);
    LinearLayout home, history, profile;
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