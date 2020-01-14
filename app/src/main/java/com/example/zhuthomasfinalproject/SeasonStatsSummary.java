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
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SeasonStatsSummary extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private ArrayAdapter<String> teamAdapter;
    private ArrayAdapter<String> sznAdapter;
    private ArrayList<Team> userTeams;
    private ArrayList<String> sUserTeams;
    private ArrayList<Season> seasons;
    private ArrayList<String> sSeasons;

    private Spinner teamSelector;
    private Spinner sznSelector;
    private TableLayout tblStats;
    private TableRow row;
    private TableRow header;
    private Team currentTeam;
    private String sCurrentTeam;
    private Button btnViewStats;

    protected void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.season_stats);

        teamSelector = findViewById(R.id.szn_team_selector);
        header = findViewById(R.id.szn_stat_summ_header);
        row = findViewById(R.id.szn_stat_row);
        btnViewStats = findViewById(R.id.btn_view_szn_stats);
        sznSelector = findViewById(R.id.szn_selector);

        // initialize a new arraylist to store existing, saved teams
        userTeams = new ArrayList<>();
        sUserTeams = new ArrayList<>();
        seasons = new ArrayList<>();

        userTeams = new ArrayList<>();
        if (StatsManager.getTeams().size() >= 2) {
            for (int i = 1; i < StatsManager.getTeams().size(); i++) {
                userTeams.add(StatsManager.getTeams().get(i));
            }
        } else {
            userTeams.add(new Team("You must create a new team in MANAGE TEAMS"));
        }

        // loops through saved teams, saves team names as Strings in an ArrayList
        for(int i = 0; i < userTeams.size(); i++) {
            sUserTeams.add(i, userTeams.get(i).getName());
        }

        teamAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sUserTeams);
        teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teamSelector.setAdapter(teamAdapter);
        teamSelector.setOnItemSelectedListener(this);

    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
        //clearTable();
        btnViewStats.setEnabled(true);
        sCurrentTeam = teamSelector.getSelectedItem().toString();

        for (int i = 0; i < userTeams.size(); i++) {
            // check if the name selected in the team selection spinner matches the Team at i in the arraylist
            if (sCurrentTeam.equals(userTeams.get(i).getName())) {
                // if the String and team name match, set the currently selected team to the Team at i in the arrayList
                currentTeam = userTeams.get(i);
            }
        }

        sSeasons = new ArrayList<>();
        seasons = currentTeam.getSeasons();
        for (int i = 0; i < seasons.size(); i++) {
            sSeasons.add(seasons.get(i).toString());
        }

        sznAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, sSeasons);
        sznAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sznSelector.setAdapter(sznAdapter);
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
    }
}
