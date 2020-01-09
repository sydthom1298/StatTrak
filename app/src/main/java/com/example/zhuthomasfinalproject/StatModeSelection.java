package com.example.zhuthomasfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class StatModeSelection extends AppCompatActivity {
    public void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stat_mode_selection);
    }

    public void onSeasonSelection(View v) {
        launchSeasonSummary(v);
    }

    public void onGameSelection(View v){
        launchGameSummary(v);
    }

    public void launchGameSummary(View v) {
        Intent i = new Intent(this, GameStatsSummary.class);
        startActivity(i);
    }

    public void launchSeasonSummary(View v) {
        Intent i = new Intent(this, SeasonStatsSummary.class);
        startActivity(i);
    }
}

