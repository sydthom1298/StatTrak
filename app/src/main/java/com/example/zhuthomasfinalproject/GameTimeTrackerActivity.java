package com.example.zhuthomasfinalproject;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class GameTimeTrackerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_time_tracker);
        StatsManager.initStatsManager();
    }
    public void onTwoPtMakes(View v){
        StatsManager.getCurrentPlayer().getCurrentStats().addTwoPtMakes();
    }
    public void onTwoPtMisses(View v) {
        StatsManager.getCurrentPlayer().getCurrentStats().addTwoPtMisses();
    }
    public void onThreePtMakes(View v){
        StatsManager.getCurrentPlayer().getCurrentStats().addThreePtMakes();
    }
    public void onThreePtMisses(View v){
        StatsManager.getCurrentPlayer().getCurrentStats().addThreePtMisses();
    }
    public void onFtMakes(View v){
        StatsManager.getCurrentPlayer().getCurrentStats().addFtMakes();
    }
    public void onFtMisses(View v){
        StatsManager.getCurrentPlayer().getCurrentStats().addFtMisses();
    }
    public void onAssists(View v){
        StatsManager.getCurrentPlayer().getCurrentStats().addAssists();
    }
    public void onOffRebs(View v){
        StatsManager.getCurrentPlayer().getCurrentStats().addOffRebs();
    }
    public void onDefRebs(View v){
        StatsManager.getCurrentPlayer().getCurrentStats().addDefRebs();
    }
    public void onSteals(View v){
        StatsManager.getCurrentPlayer().getCurrentStats().addSteals();
    }
    public void onTurnovers(View v){
        StatsManager.getCurrentPlayer().getCurrentStats().addTurnovers();
    }
    public void onBlocks(View v){
        StatsManager.getCurrentPlayer().getCurrentStats().addBlocks();
    }
    public void onFouls(View v){
        StatsManager.getCurrentPlayer().getCurrentStats().addFouls();
        System.out.println(StatsManager.getCurrentPlayer().getCurrentStats().toString());
    }


}
