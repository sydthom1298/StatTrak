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
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SeasonStatsSummary extends AppCompatActivity /*implements AdapterView.OnItemSelectedListener*/{
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

        userTeams = StatsManager.getTeams();

        // loops through saved teams, saves team names as Strings in an ArrayList
        for(int i = 0; i < userTeams.size(); i++) {
            sUserTeams.add(i, userTeams.get(i).getName());
        }

        teamAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sUserTeams);
        teamSelector.setAdapter(teamAdapter);

        //teamSelector.setOnItemSelectedListener(this);

    }

    /*public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
        clearTable();
        btnViewStats.setEnabled(true);
        sCurrentTeam = teamSelector.getSelectedItem().toString();

        for (int i = 0; i < userTeams.size(); i++) {
            // check if the name selected in the team selection spinner matches the Team at i in the arraylist
            if (sCurrentTeam.equals(userTeams.get(i).getName())) {
                // if the String and team name match, set the currently selected team to the Team at i in the arrayList
                currentTeam = userTeams.get(i);
            }
        }
        seasons = currentTeam.getSeasons();
        games = new ArrayList<>();
        sGames = new ArrayList<>();
        for (int i = 0; i < seasons.size(); i++) {
            games = seasons.get(i).getGames();
            for (int j = 0; j < games.size(); j++) {
                Date d = new Date(games.get(j).getGameDateTime());
                String sDate;
                SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy");
                sDate = dateFormat.format(d);
                sGames.add("" + sDate + " vs. " + games.get(j).getOpponent());
            }
        }

        int numGames;
        games = new ArrayList<>();
        for (int i = 0; i < seasons.size(); i++) {
            numGames = seasons.get(i).getGames().size();
            for (int j = 0; j < numGames; j++) {
                games.add(seasons.get(i).getGames().get(j));
            }
        }

        gameAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, sGames);
        gameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gameSelector.setAdapter(gameAdapter);
    }

    public void onNothingSelected(AdapterView<?> parent){

    }

    private void clearTable() {
        tblStats.removeAllViews();
        tblStats.addView(header);
    }

    public TextView newStat(int index) {
        TextView txt = new TextView(this);
        TextView orig = (TextView)(header.getVirtualChildAt(index));
        txt.setPadding(3, 3, 3, 3);
        txt.setLayoutParams(header.getVirtualChildAt(index).getLayoutParams());
        txt.setFontFeatureSettings(orig.getFontFeatureSettings());
        txt.setTypeface(orig.getTypeface());
        txt.setTextSize(orig.getTextSize());
        return txt;
    }*/
}
