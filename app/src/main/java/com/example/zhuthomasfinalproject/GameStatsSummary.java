/**
Jessica Zhu and Sydney Thomas
January 9 2019
Window that extends the AppCompatActivity class
Allows user to view the statistics of saved teams, for specific games.
These stats can also be written to data files for later use.
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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.DecimalFormat;

public class GameStatsSummary extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    // Team object to store the currently selected Team
    private Team currentTeam;
    // Game object to store currently selected Game
    private Game currentGame;
    // Spinner to store the value of the Team selection spinner
    private Spinner teamSelector;
    // ArrayList of Games to store the games that the Team has played
    private ArrayList<Game> games;
    // Spinner to store value of Game selection spinner
    private Spinner gameSelector;
    // Table layout to store the table of statistics
    private TableLayout tblStats;
    // Row object to store the properties of a default row
    private TableRow row;
    // Row object to represent the table's heading
    private TableRow header;
    // Button object to store value of viewStats button
    private Button btnViewStats;

    /**
     * called when SeasonStatsSummary window starts up
     * @param savedInstanceState - used by the system
     */
    protected void onCreate(final Bundle savedInstanceState){
        // setup screen from XML
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_stats);

        // instantiate objects to control widgets in layout
        btnViewStats = findViewById(R.id.btn_view_game_stats); // View Stats button
        tblStats = findViewById(R.id.game_stats_table); // statistic table layout
        row = findViewById(R.id.stat_row); // default row of player stats
        header = findViewById(R.id.game_stat_summ_header); // header displaying stats categories
        gameSelector = findViewById(R.id.game_selector); // Spinner for game selection
        teamSelector = findViewById(R.id.summ_team_selector); // Spinner for team selection

        // instantiate ArrayList of userTeams
        // ArrayList of Teams to store existing, saved teams
        ArrayList<Team> userTeams = new ArrayList<>();

        // check to make sure there are existing saved teams
        if (StatsManager.getTeams().size() >= 2) { // saved teams exist
            // loop through saved teams
            for (int i = 1; i < StatsManager.getTeams().size(); i++) {
                // add them to the arraylist of user teams
                userTeams.add(StatsManager.getTeams().get(i));
            }
        } else { // no saved teams exist
            // instead display a prompt to create a team in MANAGE TEAMS
            userTeams.add(new Team("You must create a new team in MANAGE TEAMS"));
        }

        // declare and instantiate array of team names, with size of team ArrayList()
        String[] sUserTeams = new String[userTeams.size()];

        // loop through the ArrayList of teams
        for (int i = 0; i < userTeams.size(); i++) {
            // add the name of each team to the String array
            sUserTeams[i] = userTeams.get(i).getName();
        }

        // instantiate array adapter to display team names in Spinner
        ArrayAdapter<String> teamAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sUserTeams);
        // make the dropdown items have consistent formatting to other Spinners
        teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teamSelector.setAdapter(teamAdapter); // display teams in Spinner

        // set a listener for when the state of the team selection Spinner's selected item changes
        // method definition taken from OnItemSelectedListener interface
        // overridden in separate method
        teamSelector.setOnItemSelectedListener(this);

        // set a listener for when the Season selection Spinner's selected item changes
        // new listener
        gameSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            /**
             * runs when an item is selected
             */
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                btnViewStats.setEnabled(true);
            }

            @Override
            /**
             * required to override in Interface (occurs when nothing is selected)
             */
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    /**
     * Method that executes when the user has selected something in a Spinner (specifically for Team selection)
     * @param parent - spinner
     * @param view - comes with event
     * @param pos - row of selected item in table
     * @param id - comes with event
     */
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
        // when a new team is selected, clear the statistic table
        clearTable();
        // enable the view stats button
        btnViewStats.setEnabled(true);
        // declare and initialize a String that holds the name of the currently selected team
        String sCurrentTeam = teamSelector.getSelectedItem().toString();

        if (sCurrentTeam.equals("You must create a new team in MANAGE TEAMS")) { // check that there are teams to view stats for
            // no teams
            // tell the user to make one
            Toast.makeText(this, "No teams have been created. Head to MANAGE TEAMS to create a new one!", Toast.LENGTH_LONG).show();
            btnViewStats.setEnabled(false);
        } else { // teams to view stats for
            // use built in method in StatsManager to find the team among saved teams that corresponds with the selected name
            currentTeam = StatsManager.findTeam(sCurrentTeam);

            // declare and instantiate ArrayList of seasons with the current team's list of seasons
            ArrayList<Season> seasons = currentTeam.getSeasons();
            // instantiate ArrayList of games
            games = new ArrayList<>();
            // declare and instantiate ArrayList of Strings to store String representations of each game
            ArrayList<String> sGames = new ArrayList<>();

            // loop through the current team's list of seasons
            for (int i = 0; i < seasons.size(); i++) {
                // get the games from that season
                games = seasons.get(i).getGames();
                // loop through those games
                for (int j = 0; j < games.size(); j++) {
                    // create a date object to represent the date of the jth game
                    Date d = new Date(games.get(j).getGameDateTime());
                    // variable to store the String representation of that date
                    String sDate;
                    // date format pattern to display the date of the jth game
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy");
                    // format the date of the game and store as a String
                    sDate = dateFormat.format(d);
                    // concatenate date of game and opponent, and store as the String representation of this game
                    sGames.add("" + sDate + " vs. " + games.get(j).getOpponent());
                }
            }

            // if no games are recorded for the team, then add a display message to inform the user
            if (sGames.size() == 0) {
                sGames.add("no games recorded");
            }

            // must store ALL the games now for other purposes, in the games array (not just from the last season)
            // variable to store the number of games played in a given seasons by the selected team
            int numGames;
            // instantiate ArrayList of Games to store all the games played by the selected team
            games = new ArrayList<>();

            // loop through the seasons
            for (int i = 0; i < seasons.size(); i++) {
                // get the number of games played in the ith season
                numGames = seasons.get(i).getGames().size();
                // loop that number of times (through all the games in the season)
                for (int j = 0; j < numGames; j++) {
                    // add every game to the ArrayList of games
                    games.add(seasons.get(i).getGames().get(j));
                }
            }
            // declare and instantiate adapter for the game Selection spinner to display the String versions of each Game
            ArrayAdapter<String> gameAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, sGames);
            // make the dropdown items have consistent formatting to other Spinners
            gameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // set adapter of Game selector to the adapter containing the String representations of each Game
            gameSelector.setAdapter(gameAdapter);
        }


    }

    /**
     * method required for the interface for when nothing is selected
     * @param parent - window
     */
    public void onNothingSelected(AdapterView<?> parent){

    }

    /**
     * method that executes when the "View Stats" button is selected
     * displays stats of the currently selected team and game
     * @param v - the current Window
     */
    public void onViewStats(View v) {
        // formats for percentages and rounding
        DecimalFormat pct = new DecimalFormat("##.0%");
        DecimalFormat whole = new DecimalFormat("#,##0");


        // clear stats currently displayed on the table
        clearTable();
        // array list that stores the Players on the currently selected Team
        ArrayList<Player> roster;
        // get the index of the game currently selected
        int indexOfGame = gameSelector.getSelectedItemPosition();
        // gets the selected game as a String
        String sCurrentGame = gameSelector.getSelectedItem().toString();

        // PlayerStats object to temporarily store a Player's set of stats for a given game
        PlayerStats temp;

        // check to make sure there are games for the current team
        if (!sCurrentGame.equals("no games recorded")) { // games exist
            // get the currently selected game in the Spinner
            currentGame = games.get(indexOfGame);
            // get the roster of the currently selected team
            roster = currentTeam.getPlayers();

            // checks which playerstats matches the selected game
            // sets the matching playerstats as the current
            for (int i = 0; i < roster.size(); i++) { // loop through the roster
                for (int j = 0; j < roster.get(i).getStats().size(); j++) { // loop through all the player stats recorded for the current player
                    // store the loop counter's current stat set temporarily
                    temp = roster.get(i).getStats().get(j);

                    // convert the date the stat set was recorded and the date of the game to Date objects for comparison
                    Date statDate = new Date(temp.getGameDateTime());
                    Date gameDate = new Date(currentGame.getGameDateTime());

                    // check if the dates are the same
                    // indicating that they correspond to one another
                    if (statDate.equals(gameDate)) {
                        System.out.println(true);
                        // if so, set this stat set as the current stat set for this player
                        roster.get(i).setCurrentStats(temp);

                    }
                }
            }

            // loop through the roster again, after all the PlayerStat sets have been collected
            for (int i = 0; i < roster.size(); i++) {
                // create a new Table Row to store the current (ith) player's stat line
                TableRow r = new TableRow(this);
                Player thisPlayer = roster.get(i); // store the current (ith) player's attributes in a Player object
                // get the current (ith) player's stats and store
                PlayerStats currentStats = thisPlayer.getCurrentStats();
                // Text Views for non-statistical information
                TextView name, num;

                // arrays to store each statistic and display (more conveniently)
                TextView[] textBoxes = new TextView[23];
                double[] stats = new double[23];

                // brute force each stat element to a different stat of the current player's
                stats[0] = currentStats.getPlayingTime() / 60000;
                stats[1] = currentStats.getPoints();
                stats[2] = currentStats.getAssists();
                stats[3] = currentStats.getTtlRebs();
                stats[4] = currentStats.getDefRebs();
                stats[5] = currentStats.getOffRebs();
                stats[6] = currentStats.getFGs();
                stats[7] = currentStats.getFGAttempts();
                stats[8] = currentStats.getFGPct();
                stats[9] = currentStats.getTwoPtMakes();
                stats[10] = currentStats.getTwoPtAttempts();
                stats[11] = currentStats.getTwoPtPct();
                stats[12] = currentStats.getEFGPct();
                stats[13] = currentStats.getThreePtMakes();
                stats[14] = currentStats.get3Attempts();
                stats[15] = currentStats.get3Pct();
                stats[16] = currentStats.getFtMakes();
                stats[17] = currentStats.getFTAttempts();
                stats[18] = currentStats.getFTPct();
                stats[19] = currentStats.getTurnovers();
                stats[20] = currentStats.getSteals();
                stats[21] = currentStats.getBlocks();
                stats[22] = currentStats.getFouls();

                // create new TextView, using the formatting from the control/default (i.e. column formatting)
                // for the name of this Player
                name = newStat(0);
                // set the text of the TextView to the currentPlayer's name
                name.setText(thisPlayer.getName());
                r.addView(name); // add the view to the row for this player

                // do the same with the jersey number of the Player
                num = newStat(1);
                num.setText(Integer.toString(thisPlayer.getJerseyNum())); // convert their jersey number to a String to display
                r.addView(num);

                // variable that holds the String version of a given int or double statistic
                String formattedString;
                // loop through all statistical categories
                for (int j = 0; j < 23; j++) {
                    // instantiate the corresponding textView for this stat to the same formatting as the column it will be displayed in
                    textBoxes[j] = newStat(j + 2);

                    // check the category of the stat for formatting
                    if (j == 8 || j== 11 || j == 12 || j == 15 || j == 18) { // stat is a percentage
                        formattedString = pct.format(stats[j]); // use percent formatting
                    } else { // stat should be a whole number
                        // use rounded, whole number formatting
                        formattedString = whole.format(stats[j]);
                    }
                    // set the TextView for this stat to display the formatted average
                    textBoxes[j].setText(formattedString);
                    // add the TextView to the Player's row of stats
                    r.addView(textBoxes[j]);
                }
                // add the player's stat line to the table
                tblStats.addView(r, row.getLayoutParams());
            }
            // disable the view stats button so that the stats don't appear multiple times
            btnViewStats.setEnabled(false);
        } else { // no games for the selected team
            // prompt user to track a new game in order to view stats
            Toast.makeText(this, "No games are saved for the " + currentTeam.getName() + ". Try tracking a new game to view their statistics.", Toast.LENGTH_LONG).show();
        }

    }

    /**
     * Clears table of players, except for the header
     */
    private void clearTable() {
        tblStats.removeAllViews();
        tblStats.addView(header);
    }

    /**
     * Method that generates a TextView control for any statistic, given the column it belongs in
     * @param index the column that the statistic belongs to
     * @return the TextView created
     */
    public TextView newStat(int index) {
        // create a text view
        TextView txt = new TextView(this);
        // get a control version from the header (using index/column)
        TextView orig = (TextView)(header.getVirtualChildAt(index));
        // set the padding of the TextView for formatting
        txt.setPadding(3, 3, 3, 3);
        // set the parameters of the new TextView to the same as the header control
        txt.setLayoutParams(header.getVirtualChildAt(index).getLayoutParams());
        // set font and text format to same as header/header control
        txt.setFontFeatureSettings(orig.getFontFeatureSettings());
        txt.setTypeface(orig.getTypeface());
        txt.setTextSize(orig.getTextSize());
        return txt; // return the textView
    }

    /**
     * method that launches the Instructions window
     * @param v - the current window
     */
    public void launchInstructions(View v) {
        Intent i = new Intent(this, Instructions.class);
        Instructions.setIndex(4); // info about game stats on 4th slide
        startActivity(i);
    }

    /**
     * Exports user's game stats to comma separated data file
     * @param v the current Window
     */
    public void onExportStats(View v) {
        // tell the user about export
        Toast.makeText(this, "File exported.", Toast.LENGTH_LONG).show();
        currentGame.exportToCSV();
    }

}
