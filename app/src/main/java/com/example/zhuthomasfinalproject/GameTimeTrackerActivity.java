package com.example.zhuthomasfinalproject;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class GameTimeTrackerActivity extends AppCompatActivity {

    TextView txt_points;
    TextView txt_quarter;
    TextView txt_fouls;
    ToggleButton btn_p1;
    ToggleButton btn_p2;
    ToggleButton btn_p3;
    ToggleButton btn_p4;
    ToggleButton btn_p5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_time_tracker);
        StatsManager.initStatsManager();
        txt_points = (TextView)findViewById(R.id.team_points);
        txt_quarter = (TextView)findViewById(R.id.quarter);
        txt_fouls = (TextView)findViewById(R.id.team_fouls);
        btn_p1 = (ToggleButton)findViewById(R.id.btn_player1);
        btn_p2 = (ToggleButton)findViewById(R.id.btn_player2);
        btn_p3 = (ToggleButton)findViewById(R.id.btn_player3);
        btn_p4 = (ToggleButton)findViewById(R.id.btn_player4);
        btn_p5 = (ToggleButton)findViewById(R.id.btn_player5);
        txt_points.setText("0");
        txt_quarter.setText("q1");
        txt_fouls.setText("0");
        btn_p1.setBackgroundColor(0xFF245300);
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
        StatsManager.getCurrentGame().addPoints(1);

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
        StatsManager.getCurrentGame().addTeamFouls();

        txt_fouls.setText(Integer.toString(StatsManager.getCurrentGame().getTeamFouls()));

        // TEST
        System.out.println(StatsManager.getCurrentPlayer().getCurrentStats().toString());
    }
    public void onQuarter(View v) {
        String current = txt_quarter.getText().toString();
        if( current.equals("q1")) {
            txt_quarter.setText("q2");
        } else if( current.equals("q2")) {
            txt_quarter.setText("q3");
        } else if( current.equals("q3")) {
            txt_quarter.setText("q4");
        } else if( current.equals("q4")) {
            txt_quarter.setText("q1");
        }
    }

    public void onPlayer1(View v) {
        //TODO set currentplayer StatsManager.setCurrentPlayer(StatsManager.getCurrentGame().Playing[0]);
        btn_p1.setBackgroundColor(0xff245300);
        btn_p2.setBackgroundColor(0xFF245354);
        btn_p3.setBackgroundColor(0xFF245354);
        btn_p4.setBackgroundColor(0xFF245354);
        btn_p5.setBackgroundColor(0xFF245354);
    }
    public void onPlayer2(View v) {
        //StatsManager.setCurrentPlayer(StatsManager.getCurrentGame().Playing[0]);
        btn_p1.setBackgroundColor(0xFF245354);
        btn_p2.setBackgroundColor(0xff245300);
        btn_p3.setBackgroundColor(0xFF245354);
        btn_p4.setBackgroundColor(0xFF245354);
        btn_p5.setBackgroundColor(0xFF245354);
    }
    public void onPlayer3(View v) {
        //StatsManager.setCurrentPlayer(StatsManager.getCurrentGame().Playing[0]);
        btn_p1.setBackgroundColor(0xFF245354);
        btn_p2.setBackgroundColor(0xFF245354);
        btn_p3.setBackgroundColor(0xff245300);
        btn_p4.setBackgroundColor(0xFF245354);
        btn_p5.setBackgroundColor(0xFF245354);
    }
    public void onPlayer4(View v) {
        //StatsManager.setCurrentPlayer(StatsManager.getCurrentGame().Playing[0]);
        btn_p1.setBackgroundColor(0xFF245354);
        btn_p2.setBackgroundColor(0xFF245354);
        btn_p3.setBackgroundColor(0xFF245354);
        btn_p4.setBackgroundColor(0xFF245300);
        btn_p5.setBackgroundColor(0xFF245354);
    }
    public void onPlayer5(View v) {
        //StatsManager.setCurrentPlayer(StatsManager.getCurrentGame().Playing[0]);
        btn_p1.setBackgroundColor(0xFF245354);
        btn_p2.setBackgroundColor(0xFF245354);
        btn_p3.setBackgroundColor(0xFF245354);
        btn_p4.setBackgroundColor(0xFF245354);
        btn_p5.setBackgroundColor(0xFF245300);
    }

}
