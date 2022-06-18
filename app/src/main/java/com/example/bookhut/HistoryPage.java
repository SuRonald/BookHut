package com.example.bookhut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class HistoryPage extends AppCompatActivity implements View.OnClickListener {

    LinearLayout home, history, profile;
    Intent movePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_page);

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