package com.example.zhuthomasfinalproject;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class GameTimeTrackerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_time_tracker);
        StatsManager.initStatsManager();
    }
    public void onTwoPtMake(View v){
        StatsManager.getCurrentPlayer().getCurrentStats().addTwoPtMakes();
    }
    public void onTwoPtMiss(View v) {
        StatsManager.getCurrentPlayer().getCurrentStats().addTwoPtAttempts();
    }

}
