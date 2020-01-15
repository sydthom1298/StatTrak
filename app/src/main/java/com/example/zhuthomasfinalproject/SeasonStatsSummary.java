/*
Jessica Zhu
January 9 2019
Class to represent...
 */
package com.example.zhuthomasfinalproject;

import android.content.Intent;
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

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.DecimalFormat;

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
    private ArrayList<ArrayList<PlayerStats>> playerStats;

    protected void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.season_stats);

        teamSelector = findViewById(R.id.szn_team_selector);
        tblStats = findViewById(R.id.szn_stats_table);
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

        sznSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                btnViewStats.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
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

    public void onViewStats(View v) {
        DecimalFormat oneDec = new DecimalFormat("##.0");
        DecimalFormat pct = new DecimalFormat("##.0%");

        clearTable();

        TableRow r;
        Player thisPlayer;
        int currentSznIndex = sznSelector.getSelectedItemPosition();
        Season currentSeason = currentTeam.getSeasons().get(currentSznIndex);
        ArrayList<Player> roster = currentTeam.getPlayers();

        ArrayList<PlayerStats> sznStats;

        PlayerStats temp;
        playerStats = new ArrayList<>();

        for (int i = 0; i < roster.size(); i++) {
            sznStats = new ArrayList<>();
            thisPlayer = roster.get(i);
            for (int j = 0; j < thisPlayer.getStats().size(); j++) {
                temp = thisPlayer.getStats().get(j);

                Calendar statDate = Calendar.getInstance();
                Date sDate = new Date(temp.getGameDateTime());
                statDate.setTime(sDate);

                if (statDate.get(Calendar.YEAR) == currentSeason.getStartYear() || statDate.get(Calendar.YEAR) == currentSeason.getEndYear()) {
                    sznStats.add(temp);
                }

            }
            playerStats.add(sznStats);
        }

        for (int i = 0; i < roster.size(); i++) {
            r = new TableRow(this);

            TextView name, num, games;

            TextView[] textBoxes = new TextView[23];
            double[] sums = new double[23];

            double perGame = 0;

            thisPlayer = roster.get(i);
            int gamesPlayed = playerStats.get(i).size();
            for (int j = 0; j < gamesPlayed; j++) { // loops for every game this player has played this season
                temp = playerStats.get(i).get(j);

                sums[0] += temp.getPlayingTime();
                sums[1] += temp.getPoints();
                sums[2] += temp.getAssists();
                sums[3] += temp.getTtlRebs();
                sums[4] += temp.getDefRebs();
                sums[5] += temp.getOffRebs();
                sums[6] += temp.getFGs();
                sums[7] += temp.getFGAttempts();
                sums[8] += temp.getFGPct();
                sums[9] += temp.getTwoPtMakes();
                sums[10] += temp.getTwoPtAttempts();
                sums[11] += temp.getTwoPtPct();
                sums[12] += temp.getEFGPct();
                sums[13] += temp.getThreePtMakes();
                sums[14] += temp.get3Attempts();
                sums[15] += temp.get3Pct();
                sums[16] += temp.getFtMakes();
                sums[17] += temp.getFTAttempts();
                sums[18] += temp.getFTPct();
                sums[19] += temp.getTurnovers();
                sums[20] += temp.getSteals();
                sums[21] += temp.getBlocks();
                sums[22] += temp.getFouls();
            }

            name = newStat(0);
            name.setText(thisPlayer.getName());
            r.addView(name);
            num = newStat(1);
            num.setText(Integer.toString(thisPlayer.getJerseyNum()));
            r.addView(num);

            games = newStat(2);
            games.setText(Integer.toString(gamesPlayed));
            r.addView(games);

            String formattedStat;
            for (int j = 0; j < 23; j++) {
                perGame = (double) sums[j] / (double) gamesPlayed;
                textBoxes[j] = newStat(j + 3);

                if (j == 8 || j == 11 || j == 12 || j == 15 || j == 18) {
                    formattedStat = pct.format(perGame);
                } else if (perGame == 0) {
                    formattedStat = "0";
                } else {
                    formattedStat = oneDec.format(perGame);
                }

                textBoxes[j].setText(formattedStat);
                r.addView(textBoxes[j]);
            }

            tblStats.addView(r, row.getLayoutParams());
        }

        btnViewStats.setEnabled(false);
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

    public void launchInstructions(View v) {
        Intent i = new Intent(this, Instructions.class);
        startActivity(i);
    }

    public void exportStats(){

    }
}
