/*
Jessica Zhu
January 9 2019
Window that extends the AppCompatActivity class
Allows user to view the statistics of saved teams, for specific games.
These stats can also be written to data files for later use
 */
package com.example.zhuthomasfinalproject;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.DecimalFormat;

public class GameStatsSummary extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private TextView gameStats;
    private ArrayList<Team> userTeams;
    private String[] sUserTeams;
    private String sCurrentTeam;
    private Team currentTeam;
    private Spinner teamSelector;
    private ArrayAdapter<String> teamAdapter;
    private ArrayList<Season> seasons;
    private ArrayList<Game> games;
    private ArrayList<String> sGames;
    private ArrayAdapter<String> gameAdapter;
    private Game currentGame;
    private Spinner gameSelector;
    private ArrayList<Player> roster;
    private TableLayout tblStats;
    private TableRow row;
    private TableRow header;
    private Button btnViewStats;
    private PlayerStats[] stats;

    protected void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_stats);

        btnViewStats = findViewById(R.id.btn_view_game_stats);
        tblStats = findViewById(R.id.game_stats_table);
        row = findViewById(R.id.stat_row);
        header = findViewById(R.id.game_stat_summ_header);

        gameSelector = findViewById(R.id.game_selector);


        userTeams = new ArrayList<>();
        if (StatsManager.getTeams().size() >= 2) {
            for (int i = 1; i < StatsManager.getTeams().size(); i++) {
                userTeams.add(StatsManager.getTeams().get(i));
            }
        } else {
            userTeams.add(new Team("You must create a new team in MANAGE TEAMS"));
        }

        /*PlayerStats p = new PlayerStats();
        p.setAssists(2);
        p.setBlocks(3);
        p.setDefRebs(4);
        p.setFouls(3);
        p.setFtMakes(5);
        p.setFtMisses(5);
        p.setMinPlayed(20);
        p.setOffRebs(4);
        p.setSteals(2);
        p.setThreePtMakes(0);
        p.setThreePtMisses(3);
        p.setTwoPtMakes(2);
        p.setTwoPtMisses(1);
        p.setTurnovers(4);
        p.setFtMisses(0);
        p.setFtMakes(4);
        userTeams.get(0).getPlayers().get(0).setCurrentStats(p);
        ArrayList<PlayerStats> arr = new ArrayList<>();
        arr.add(p);
        userTeams.get(0).getPlayers().get(0).setStats(arr);*/
        sUserTeams = new String[userTeams.size()];
        for (int i = 0; i < userTeams.size(); i++) {
            sUserTeams[i] = userTeams.get(i).getName();
        }


        teamSelector = findViewById(R.id.summ_team_selector);

        // instantiate array adapter to display team names in Spinner
        teamAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sUserTeams);
        teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teamSelector.setAdapter(teamAdapter); // display teams in Spinner


        teamSelector.setOnItemSelectedListener(this);

        gameSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    public void onViewStats(View v) {
        DecimalFormat pct = new DecimalFormat("##.0%");

        clearTable();
        roster = currentTeam.getPlayers();

        currentGame = games.get(gameSelector.getSelectedItemPosition());

        stats = new PlayerStats[roster.size()];

        PlayerStats temp;

        // checks which playerstats matches the selected game
        for (int i = 0; i < 1/*roster.size()*/; i++) {
            for (int j = 0; j < roster.get(i).getStats().size(); j++) {
                temp = roster.get(i).getStats().get(j);

                Calendar statDate = Calendar.getInstance();
                Date sDate = new Date(temp.getGameDateTime());
                Calendar gameDate = Calendar.getInstance();
                Date gDate = new Date(currentGame.getGameDateTime());

                statDate.setTime(sDate);
                gameDate.setTime(gDate);

                if (statDate.get(Calendar.DAY_OF_YEAR) == gameDate.get(Calendar.DAY_OF_YEAR)) {
                    roster.get(i).setCurrentStats(temp);
                }

            }
        }

        for (int i = 0; i < 1 /*TODO replace with roster size when game time tracker is integrated*/; i++) {
            TableRow r = new TableRow(this);
            Player thisPlayer = roster.get(i);
            PlayerStats currentStats = thisPlayer.getCurrentStats();
            TextView name, num, mins, pts, assts, tRebs, dRebs, oRebs, twoPts, twoPtAtts, fgPct, threePts, threePtAtts, threePtPct, fts, ftAttempts, ftPct, tOvers, stls, blks, fouls;

            name = newStat(0);
            name.setText(thisPlayer.getName());
            r.addView(name);
            num = newStat(1);
            num.setText(Integer.toString(thisPlayer.getJerseyNum()));
            r.addView(num);

            mins = newStat(2);
            mins.setText(Integer.toString(currentStats.getMinPlayed()));
            r.addView(mins);

            pts = newStat(3);
            pts.setText(Integer.toString(currentStats.getPoints()));
            r.addView(pts);

            assts = newStat(4);
            assts.setText(Integer.toString(currentStats.getAssists()));
            r.addView(assts);

            tRebs = newStat(5);
            tRebs.setText(Integer.toString(currentStats.getTtlRebs()));
            r.addView(tRebs);

            dRebs = newStat(6);
            dRebs.setText(Integer.toString(currentStats.getDefRebs()));
            r.addView(dRebs);

            oRebs = newStat(7);
            oRebs.setText(Integer.toString(currentStats.getOffRebs()));
            r.addView(oRebs);

            twoPts = newStat(8);
            twoPts.setText(Integer.toString(currentStats.getTwoPtMakes()));
            r.addView(twoPts);

            twoPtAtts = newStat(9);
            twoPtAtts.setText(Integer.toString(currentStats.getFGAttempts()));
            r.addView(twoPtAtts);

            fgPct = newStat(10);
            fgPct.setText(pct.format(currentStats.getFGPct()));
            r.addView(fgPct);

            threePts = newStat(11);
            threePts.setText(Integer.toString(currentStats.getThreePtMakes()));
            r.addView(threePts);

            threePtAtts = newStat(12);
            threePtAtts.setText(Integer.toString(currentStats.get3Attempts()));
            r.addView(threePtAtts);

            threePtPct = newStat(13);
            threePtPct.setText(pct.format(currentStats.get3Pct()));
            r.addView(threePtPct);

            fts = newStat(14);
            fts.setText(Integer.toString(currentStats.getFtMakes()));
            r.addView(fts);

            ftAttempts = newStat(15);
            ftAttempts.setText(Integer.toString(currentStats.getFTAttempts()));
            r.addView(ftAttempts);

            ftPct = newStat(16);
            ftPct.setText(pct.format(currentStats.getFTPct()));
            r.addView(ftPct);

            tOvers = newStat(17);
            tOvers.setText(Integer.toString(currentStats.getTurnovers()));
            r.addView(tOvers);

            stls = newStat(18);
            stls.setText(Integer.toString(currentStats.getSteals()));
            r.addView(stls);

            blks = newStat(19);
            blks.setText(Integer.toString(currentStats.getBlocks()));
            r.addView(blks);

            fouls = newStat(20);
            fouls.setText(Integer.toString(currentStats.getFouls()));
            r.addView(fouls);

            tblStats.addView(r, row.getLayoutParams());

            btnViewStats.setEnabled(false);
        }


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
