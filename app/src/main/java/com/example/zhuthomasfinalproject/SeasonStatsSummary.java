/*
Jessica Zhu
January 9 2019
Class to represent...
 */
package com.example.zhuthomasfinalproject;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SeasonStatsSummary extends AppCompatActivity {
    private ArrayAdapter<String> teamAdapter;
    private ArrayAdapter<String> sznAdapter;
    private ArrayList<Team> userTeams;
    private ArrayList<String> sUserTeams;
    private ArrayList<String> seasons;

    private Spinner teamSelector;

    protected void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.season_stats);

        teamSelector = findViewById(R.id.szn_team_selector);
        // initialize a new arraylist to store existing, saved teams
        userTeams = new ArrayList<>();
        sUserTeams = new ArrayList<>();
        seasons = new ArrayList<>();

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

        userTeams.add(team);

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

        userTeams.add(team);

        // loops through saved teams, saves team names as Strings in an ArrayList
        for(int i = 0; i < userTeams.size(); i++) {
            sUserTeams.add(i, userTeams.get(i).getName());
        }

        teamAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sUserTeams);
        teamSelector.setAdapter(teamAdapter);

        teamSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                sznAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, seasons);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
