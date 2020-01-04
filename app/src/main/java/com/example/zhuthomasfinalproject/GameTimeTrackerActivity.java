package com.example.zhuthomasfinalproject;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameTimeTrackerActivity extends AppCompatActivity {

    TextView txt_points;
    TextView txt_quarter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_time_tracker);
        StatsManager.initStatsManager();
        txt_points = (TextView)findViewById(R.id.team_points);
        txt_quarter = (TextView)findViewById(R.id.quarter);
        txt_points.setText("0");
    }
    public void onTwoPtMakes(View v){
        PlayerStats s = StatsManager.getCurrentPlayer().getCurrentStats();
        s.addTwoPtMakes();
        s.addPoints(2);

        StatsManager.getCurrentGame().addPoints(2);

        txt_points.setText(Integer.toString(StatsManager.getCurrentGame().getPoints()));

    }
    public void onTwoPtMisses(View v) {
        PlayerStats s = StatsManager.getCurrentPlayer().getCurrentStats();
        s.addTwoPtMisses();

    }
    public void onThreePtMakes(View v){
        PlayerStats s = StatsManager.getCurrentPlayer().getCurrentStats();
        s.addThreePtMakes();
        s.addPoints(3);
        StatsManager.getCurrentGame().addPoints(3);

        txt_points.setText(Integer.toString(StatsManager.getCurrentGame().getPoints()));
    }
    public void onThreePtMisses(View v){
        StatsManager.getCurrentPlayer().getCurrentStats().addThreePtMisses();
    }
    public void onFtMakes(View v){
        PlayerStats s = StatsManager.getCurrentPlayer().getCurrentStats();
        s.addFtMakes();

        s.addPoints(1);
        StatsManager.getCurrentGame().addPoints(3);

        txt_points.setText(Integer.toString(StatsManager.getCurrentGame().getPoints()));
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
