/*
Jessica Zhu
January 9 2019
Class that...
 */
package com.example.zhuthomasfinalproject;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class GameStatsSummary extends AppCompatActivity {
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
    private Spinner gameSelector;
    private ArrayList<Player> roster;

    protected void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_stats);

        gameSelector = findViewById(R.id.game_selector);

        userTeams = StatsManager.getTeams();

        // sample data (remove when manage teams works)
        Player tempPlayer1 = new Player("Kyle", 7);
        Player tempPlayer2 = new Player("Pascal",43);
        Player tempPlayer3 = new Player("Terence", 0);
        ArrayList<Player> tempPlayers = new ArrayList<>();
        tempPlayers.add(tempPlayer1);
        tempPlayers.add(tempPlayer2);
        tempPlayers.add(tempPlayer3);
        Team team = new Team("Raptors", tempPlayers);

        Season s = new Season(team, 2016, 2017);
        Game g = new Game(team, "Warriors");
        Game g1 = new Game(team, "Tigers");
        ArrayList<Game> tempGames = new ArrayList<>();
        tempGames.add(g);
        tempGames.add(g1);
        s.setGames(tempGames);
        Season s1 = new Season(team, 2017,2018);

        ArrayList<Season> tempSeasons = new ArrayList<>();
        tempSeasons.add(s);
        tempSeasons.add(s1);
        team.setSeasons(tempSeasons);


        userTeams.add(team);

        tempPlayer1 = new Player("Jess", 1);
        tempPlayer2 = new Player("Sydney",2);
        tempPlayer3 = new Player("Emma", 3);
        tempPlayers = new ArrayList<>();
        tempPlayers.add(tempPlayer1);
        tempPlayers.add(tempPlayer2);
        tempPlayers.add(tempPlayer3);
        team = new Team("Phoenix", tempPlayers);


        s = new Season(team, 2014, 2015);
        g = new Game(team, "Hamsters");
        g1 = new Game(team, "Pandas");
        tempGames = new ArrayList<>();
        tempGames.add(g);
        tempGames.add(g1);
        s.setGames(tempGames);
        s1 = new Season(team, 2018,2019);

        tempSeasons = new ArrayList<>();
        tempSeasons.add(s);
        tempSeasons.add(s1);
        team.setSeasons(tempSeasons);

        userTeams.add(team);

        sUserTeams = new String[userTeams.size()];
        for (int i = 0; i < userTeams.size(); i++) {
            sUserTeams[i] = userTeams.get(i).getName();
        }

        teamSelector = findViewById(R.id.summ_team_selector);

        // instantiate array adapter to display team names in Spinner
        teamAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sUserTeams);
        teamSelector.setAdapter(teamAdapter); // display teams in Spinner

        //
        teamSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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

                gameAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, sGames);
                gameSelector.setAdapter(gameAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        gameSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<PlayerStats> tempPlayerStats = new ArrayList<>();
                TableLayout table = findViewById(R.id.game_stats_table);
                TableRow row;
                TextView playerName;

                int indexOfGame = gameSelector.getSelectedItemPosition();
                Game currentGame = games.get(indexOfGame);

                /*
                for (int i = 0; i < games.size(); i++) {
                    row = new TableRow(getApplicationContext());
                    playerName = newStat(row);
                    row.addView(playerName);
                    table.addView(row);
                }*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public TextView newStat(TableRow row) {
        TextView txt = new TextView(this);
        TextView orig = (TextView)(row.getVirtualChildAt(0));
        txt.setLayoutParams(row.getVirtualChildAt(0).getLayoutParams());
        txt.setFontFeatureSettings(orig.getFontFeatureSettings());
        txt.setTypeface(orig.getTypeface());
        txt.setTextSize(orig.getTextSize());
        return txt;
    }
}
