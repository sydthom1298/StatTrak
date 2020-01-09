package com.example.zhuthomasfinalproject;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SeasonStatsSummary extends AppCompatActivity {
    private ArrayAdapter <Team> teamAdapter;
    private ArrayList<Team> teams;


    private Spinner teamSelector;
    public void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.season_stats);

        teamSelector = findViewById(R.id.szn_team_selector);

        teams = new ArrayList<>();

        // sample data (remove when manage teams works)
        Player tempPlayer1 = new Player("Kyle", 7);
        Player tempPlayer2 = new Player("Pascal",43);
        Player tempPlayer3 = new Player("Terence", 0);
        Player tempPlayer4 = new Player("Fred", 23);
        Player tempPlayer5 = new Player("OG", 3);
        ArrayList<Player> tempPlayers = new ArrayList<>();
        tempPlayers.add(tempPlayer1);
        tempPlayers.add(tempPlayer2);
        tempPlayers.add(tempPlayer3);
        tempPlayers.add(tempPlayer4);
        tempPlayers.add(tempPlayer5);
        Team team = new Team("Raptors", tempPlayers);

        teams.add(team);

        tempPlayer1 = new Player("Jess", 1);
        tempPlayer2 = new Player("Sydney",2);
        tempPlayer3 = new Player("Emma", 3);
        tempPlayer4 = new Player("Dani", 4);
        tempPlayer5 = new Player("Maddie", 5);
        tempPlayers = new ArrayList<>();
        tempPlayers.add(tempPlayer1);
        tempPlayers.add(tempPlayer2);
        tempPlayers.add(tempPlayer3);
        tempPlayers.add(tempPlayer4);
        tempPlayers.add(tempPlayer5);
        team = new Team("Phoenix", tempPlayers);

        teams.add(team);

        //teamAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, teams);
        teamSelector.setAdapter(teamAdapter);


    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
