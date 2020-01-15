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

            TextView name, num, games, mins, pts, assts, tRebs, dRebs, oRebs, fg, fgAtts, fgPct, twoPts, twoPtAtts, twoPtPct, eFGPct,
                    threePts, threePtAtts, threePtPct, fts, ftAttempts, ftPct, tOvers, stls, blks, fouls;

            //TextView[] textBoxes = new TextView[24];
            //double[] sums;

            int sumPts = 0, sumAssts = 0, sumTRebs = 0, sumDRebs = 0, sumORebs = 0, sumFGs = 0, sumFGAtts = 0, sumTwoPts = 0,
                    sumTwoPtAtts = 0, sumThreePts = 0, sumThreePtAtts = 0, sumFTs = 0,
                    sumFTAtts = 0, sumTOvers = 0, sumStls = 0, sumBlks = 0, sumFouls = 0;

            double sumFGPct = 0, sumTwoPtPct = 0, sumeFGPct = 0, sumThreePtPct = 0, sumFTPct = 0;
            long sumMins = 0;

            double perGame = 0;

            thisPlayer = roster.get(i);
            int gamesPlayed = playerStats.get(i).size();
            for (int j = 0; j < gamesPlayed; j++) { // loops for every game this player has played this season
                temp = playerStats.get(i).get(j);
                sumMins += temp.getPlayingTime();
                sumPts += temp.getPoints();
                sumAssts += temp.getAssists();
                sumTRebs += temp.getTtlRebs();
                sumDRebs += temp.getDefRebs();
                sumORebs += temp.getOffRebs();
                sumFGs += temp.getFGs();
                sumFGAtts += temp.getFGAttempts();
                sumFGPct += temp.getFGPct();
                sumTwoPts += temp.getTwoPtMakes();
                sumTwoPtAtts += temp.getTwoPtAttempts();
                sumTwoPtPct += temp.getTwoPtPct();
                sumeFGPct += temp.getEFGPct();
                sumThreePts += temp.getThreePtMakes();
                sumThreePtAtts += temp.get3Attempts();
                sumThreePtPct += temp.get3Pct();
                sumFTs += temp.getFtMakes();
                sumFTAtts += temp.getFTAttempts();
                sumFTPct += temp.getFTPct();
                sumTOvers += temp.getTurnovers();
                sumStls += temp.getSteals();
                sumBlks += temp.getBlocks();
                sumFouls += temp.getFouls();
            }

            // TODO find a more efficient way of doing this (with arrays perhaps)
            // e.g. array with all the sums, array with all the textViews


            /*for (int j = 0; j < 24; j++) {

            }*/

            name = newStat(0);
            name.setText(thisPlayer.getName());
            r.addView(name);
            num = newStat(1);
            num.setText(Integer.toString(thisPlayer.getJerseyNum()));
            r.addView(num);

            games = newStat(2);
            games.setText(Integer.toString(gamesPlayed));
            r.addView(games);

            perGame = (double) sumMins / (double) gamesPlayed;
            mins = newStat(3);
            mins.setText(oneDec.format(perGame));
            r.addView(mins);

            perGame = (double) sumPts / (double) gamesPlayed;
            pts = newStat(4);
            pts.setText(oneDec.format(perGame));
            r.addView(pts);

            perGame = (double) sumAssts / (double) gamesPlayed;
            System.out.println(perGame);
            assts = newStat(5);
            assts.setText(oneDec.format(perGame));
            r.addView(assts);

            perGame = (double) sumTRebs / (double) gamesPlayed;
            tRebs = newStat(6);
            tRebs.setText(oneDec.format(perGame));
            r.addView(tRebs);

            perGame = (double) sumDRebs / (double) gamesPlayed;
            dRebs = newStat(7);
            dRebs.setText(oneDec.format(perGame));
            r.addView(dRebs);

            perGame = (double) sumORebs / (double) gamesPlayed;
            oRebs = newStat(8);
            oRebs.setText(oneDec.format(perGame));
            r.addView(oRebs);

            perGame = (double) sumFGs / (double) gamesPlayed;
            fg = newStat(9);
            fg.setText(oneDec.format(perGame));
            r.addView(fg);

            perGame = (double) sumFGAtts / (double) gamesPlayed;
            fgAtts = newStat(10);
            fgAtts.setText(oneDec.format(perGame));
            r.addView(fgAtts);

            perGame = sumFGPct / (double) gamesPlayed;
            fgPct = newStat(11);
            fgPct.setText(pct.format(perGame));
            r.addView(fgPct);

            perGame = (double) sumTwoPts / (double) gamesPlayed;
            twoPts = newStat(12);
            twoPts.setText(oneDec.format(perGame));
            r.addView(twoPts);

            perGame = (double) sumTwoPtAtts / (double) gamesPlayed;
            twoPtAtts = newStat(13);
            twoPtAtts.setText(oneDec.format(perGame));
            r.addView(twoPtAtts);

            perGame = sumTwoPtPct / (double) gamesPlayed;
            twoPtPct = newStat(14);
            twoPtPct.setText(pct.format(perGame));
            r.addView(twoPtPct);

            perGame = sumeFGPct / (double) gamesPlayed;
            eFGPct = newStat(15);
            eFGPct.setText(pct.format(perGame));
            r.addView(eFGPct);

            perGame = (double) sumThreePts / (double) gamesPlayed;
            threePts = newStat(16);
            threePts.setText(oneDec.format(perGame));
            r.addView(threePts);

            perGame = (double) sumThreePtAtts / (double) gamesPlayed;
            threePtAtts = newStat(17);
            threePtAtts.setText(oneDec.format(perGame));
            r.addView(threePtAtts);

            perGame = sumThreePtPct / (double) gamesPlayed;
            threePtPct = newStat(18);
            threePtPct.setText(pct.format(perGame));
            r.addView(threePtPct);

            perGame = (double) sumFTs / (double) gamesPlayed;
            fts = newStat(19);
            fts.setText(oneDec.format(perGame));
            r.addView(fts);

            perGame = (double) sumFTAtts / (double) gamesPlayed;
            ftAttempts = newStat(20);
            ftAttempts.setText(oneDec.format(perGame));
            r.addView(ftAttempts);

            perGame = sumFTPct / (double) gamesPlayed;
            ftPct = newStat(21);
            ftPct.setText(pct.format(perGame));
            r.addView(ftPct);

            perGame = (double) sumTOvers / (double) gamesPlayed;
            tOvers = newStat(22);
            tOvers.setText(oneDec.format(perGame));
            r.addView(tOvers);

            perGame = (double) sumStls / (double) gamesPlayed;
            stls = newStat(23);
            stls.setText(oneDec.format(perGame));
            r.addView(stls);

            perGame = (double) sumBlks / (double) gamesPlayed;
            blks = newStat(24);
            blks.setText(oneDec.format(perGame));
            r.addView(blks);

            perGame = (double) sumFouls / (double) gamesPlayed;
            fouls = newStat(25);
            fouls.setText(oneDec.format(perGame));
            r.addView(fouls);

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
