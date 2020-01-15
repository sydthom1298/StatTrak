/**
Jessica Zhu and Sydney Thomas
January 9 2019
Window that extends AppCompat Activity Class
Allows user to view the statistics of saved teams, for specific seasons. (per game statistics)
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.DecimalFormat;

public class SeasonStatsSummary extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    // Spinner objects to store the Team selection and Season selection spinners
    private Spinner teamSelector;
    private Spinner sznSelector;
    // Table Layout object to store the statistic layout
    private TableLayout tblStats;
    // Row object to access properties of a default row
    private TableRow row;
    // Row object that represents the table header
    private TableRow header;
    // Team object that stores the currently selected Team
    private Team currentTeam;
    // Button object that stores the value of the View Stats button
    private Button btnViewStats;

    /**
     * called when SeasonStatsSummary window starts up
     * @param savedInstanceState - used by the system
     */
    protected void onCreate(final Bundle savedInstanceState){
        // setup screen from XML
        super.onCreate(savedInstanceState);
        setContentView(R.layout.season_stats);

        // instantiate objects to control widgets in layout
        teamSelector = findViewById(R.id.szn_team_selector); // for Team selection spinner
        tblStats = findViewById(R.id.szn_stats_table); // statistic table layout
        header = findViewById(R.id.szn_stat_summ_header); // header displaying stat categories
        row = findViewById(R.id.szn_stat_row); // a default row of player stats
        btnViewStats = findViewById(R.id.btn_view_szn_stats); // the button to view stats
        sznSelector = findViewById(R.id.szn_selector); // Season selection spinner

        // declare and instantiate an ArrayList to store String representation of each Team
        ArrayList<String> sUserTeams = new ArrayList<>();
        // declare and instantiate a new ArrayList to store existing, saved teams
        ArrayList<Team> userTeams = new ArrayList<>();

        // check to make sure there are saved teams
        if (StatsManager.getTeams().size() >= 2) { // saved teams exist (not the default prompt)
            // loop through saved teams
            for (int i = 1; i < StatsManager.getTeams().size(); i++) {
                // add them to the ArrayList of teams
                userTeams.add(StatsManager.getTeams().get(i));
            }
        } else { // no saved teams exist
            // prompt the user to go to MANAGE TEAMS in order to create teams to view stats for
            userTeams.add(new Team("You must create a new team in MANAGE TEAMS"));
        }

        // loops through saved teams, saves team names as Strings in an ArrayList
        for(int i = 0; i < userTeams.size(); i++) {
            // save each Team name as a String
            sUserTeams.add(i, userTeams.get(i).getName());
        }

        // declare and instantiate adapter for team selection Spinner using ArrayList of team names
        ArrayAdapter<String> teamAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sUserTeams);
        // make the dropdown items have consistent formatting to other Spinners
        teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teamSelector.setAdapter(teamAdapter); // set adapter of Spinner to the above adapter

        // set a listener for when the state of the team selection Spinner's selected item changes
        // method definition taken from OnItemSelectedListener interface
        // overridden in separate method
        teamSelector.setOnItemSelectedListener(this);

        // set a listener for when the Season selection Spinner's selected item changes
        // new listener
        sznSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            /**
             * occurs when an item is selected
             */
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                btnViewStats.setEnabled(true); // re-enable view stats button
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
        // when a new team is selected, clear the table
        clearTable();
        // enable the view stats button
        btnViewStats.setEnabled(true);
        // declare and instantiate a new ArrayList to store saved seasons
        ArrayList<Season> seasons;
        // declare and initialize a String that holds the name of the currently selected team
        String sCurrentTeam = teamSelector.getSelectedItem().toString();

        // use built in method in StatsManager to find the team among saved teams that corresponds with the selected name
        currentTeam = StatsManager.findTeam(sCurrentTeam);

        // declare and instantiate an ArrayList to store the current team's seasons as Strings
        ArrayList<String> sSeasons = new ArrayList<>();
        // instantiate ArrayList of stored seasons with the current team's saved seasons
        seasons = currentTeam.getSeasons();
        // loop through the ArrayList of seasons
        for (int i = 0; i < seasons.size(); i++) {
            // store every season as a String in the String ArrayList
            sSeasons.add(seasons.get(i).toString());
        }

        // declare and instantiate an array adapter for Season selection Spinner with the list of seasons as Strings
        ArrayAdapter<String> sznAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, sSeasons);
        // make the dropdown items have consistent formatting to other Spinners
        sznAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // set the Adapter of the Season spinner to the ArrayAdapter object above
        sznSelector.setAdapter(sznAdapter);
    }

    /**
     * method required for the interface for when nothing is selected
     * @param parent - window
     */
    public void onNothingSelected(AdapterView<?> parent){
    }

    /**
     * method that executes when the "View Stats" button is selected
     * displays stats of the currently selected team and season
     * @param v - the current Window
     */
    public void onViewStats(View v) {
        // formats for percentage and rounding
        DecimalFormat oneDec = new DecimalFormat("##.0");
        DecimalFormat pct = new DecimalFormat("##.0%");

        // clear table in order to display updated stats
        clearTable();

        // declare a row to temporarily hold the stats of the current Player (when looping)
        TableRow r;
        Player thisPlayer; // Player object to store the current Player (when looping)
        // get the index of the selected item in the Season spinner
        int currentSznIndex = sznSelector.getSelectedItemPosition();
        Season currentSeason; // stores the currently selected season
        ArrayList<Player> roster; // stores the roster of the currently selected team

        // stores the player stats for the season of the current Player (when looping)
        ArrayList<PlayerStats> sznStats;

        // holds the playerStats of the current player for an individual game
        PlayerStats temp;
        // ArrayList of ArrayLists of PlayerStats that stores every player's playerStats for the season
        // each player has one ArrayList of PlayerStats
        ArrayList<ArrayList<PlayerStats>> playerStats;

        if (currentSznIndex != -1) { // make sure that there are seasons for the current Team
            // get the selected Season to view stats for in the Season spinner
            currentSeason = currentTeam.getSeasons().get(currentSznIndex);
            // get the roster of the currently selected Team
            roster = currentTeam.getPlayers();
            // initialize 2D ArrayList
            playerStats = new ArrayList<>();

            // loop through the roster
            for (int i = 0; i < roster.size(); i++) {
                // initialize arraylist to store the ith player's season Stats
                sznStats = new ArrayList<>();
                // get the ith player and store temporarily
                thisPlayer = roster.get(i);

                // loop through this all of this Player's stats for games
                for (int j = 0; j < thisPlayer.getStats().size(); j++) {
                    temp = thisPlayer.getStats().get(j); // get the stats of this loop iteration

                    // use Calendar to access the year of the day the statistic set was recorded
                    Calendar statDate = Calendar.getInstance();
                    Date sDate = new Date(temp.getGameDateTime());
                    statDate.setTime(sDate);

                    // see if the date the statistic was recorded is within the season that the user
                    // wants to view stats for
                    if (statDate.get(Calendar.YEAR) == currentSeason.getStartYear() || statDate.get(Calendar.YEAR) == currentSeason.getEndYear()) {
                        sznStats.add(temp); // if so add the statistic set to the arraylist of statistics for the season
                    }
                }

                // once done sorting out which of the player's stat sets are in this season
                // add the player's stats for the season to the ArrayList of team/season stats
                playerStats.add(sznStats);
            }

            // once done retrieving every player's season stats
            // loop through the roster again, this time to compile the statistics into the table
            for (int i = 0; i < roster.size(); i++) {
                // instantiate a new Table Row to store all the current player's statistics
                r = new TableRow(this);

                // instantiate text views for information that averages won't be calculated for
                TextView name, num, games;

                // all other statistic categories will be averaged
                // make arrays of textViews and sums to conveniently calculate and store averages
                TextView[] textBoxes = new TextView[23];
                double[] sums = new double[23];

                // a variable to temporarily hold the perGame value of each statistic category
                double perGame;

                // get the ith player (loop iteration)
                thisPlayer = roster.get(i);

                // store the number of games this player has played (i.e. the number of stat sets they have for this season)
                int gamesPlayed = playerStats.get(i).size();
                for (int j = 0; j < gamesPlayed; j++) { // loops for every game this player has played this season
                    // temporarily retrieve the stats from the ith game the Player has played
                    temp = playerStats.get(i).get(j);

                    // add the statistic from that game to an accumulator storing the sum of each statistical category
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

                // convert milliseconds to minutes for playing time
                sums[0] = sums[0] / 60000;

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

                // also, for total games played
                games = newStat(2);
                games.setText(Integer.toString(gamesPlayed)); // convert int to string for number of games
                r.addView(games);

                // variable that holds the String version of a given int or double statistic
                String formattedStat;
                for (int j = 0; j < 23; j++) { // loop through all the stats categories
                    // calculate the per game value of that category by dividing the sum of the statistic by the games played
                    perGame = (double) sums[j] / (double) gamesPlayed;
                    // instantiate the corresponding textView for this stat to the same formatting as the column it will be displayed in
                    textBoxes[j] = newStat(j + 3);

                    // check the category of the stat for formatting
                    if (j == 8 || j == 11 || j == 12 || j == 15 || j == 18) { // if the stat is a percentage
                        // use the percent format
                        formattedStat = pct.format(perGame);
                    } else if (perGame == 0) { // value of stat is 0
                        // don't format
                        formattedStat = "0";
                    } else { // any other normal average
                        // format to round
                        formattedStat = oneDec.format(perGame);
                    }

                    // set the TextView for this stat to display the formatted average
                    textBoxes[j].setText(formattedStat);
                    // add the TextView to the Player's row of stats
                    r.addView(textBoxes[j]);
                }
                // add the Player's stat row to the table
                tblStats.addView(r, row.getLayoutParams());
            }
            // disable view stats button to prevent stats from displaying multiple times
            btnViewStats.setEnabled(false);
        } else { // no seasons stored for the current team
            // prompt user to track a new game
            Toast.makeText(this, "No seasons are saved to the " + currentTeam.getName() + ". Try tracking a new game to view their statistics", Toast.LENGTH_LONG).show();
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
        return txt; // return the formatted textView
    }

    /**
     * Method that launches the instructions window when the Help button is clicked
     * @param v - the current window
     */
    public void launchInstructions(View v) {
        Intent i = new Intent(this, Instructions.class);
        startActivity(i);
    }

    public void exportStats(){

    }
}
