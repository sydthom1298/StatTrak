/*
Jessica Zhu
January 6 2019
Window that extends the Activity Class
Allows user to set up the attributes of a new game to track.
When the user chooses to continue, all attributes selected are saved and applied to the tracker.
 */
package com.example.zhuthomasfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.TextView;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

public class GameSetup extends AppCompatActivity implements Serializable {
    // master arraylist of possible jersey numbers for the selected team
    private ArrayList<Integer> jerseyNums;
    // list of saved teams that the user can choose from
    private ArrayList<Team> userTeams;

    // object to represent Spinner widget to select user's team from
    private Spinner teamSelector;
    // stores the current selected team (in the Spinner) as an object
    private Team currentTeam;
    // ArrayAdapter to display entries for jersey number spinner
    private ArrayAdapter jerseyAdapter;

    // object to represent the opponent team input textbox
    private EditText oppTeam;

    // objects to represent the Spinners for jersey number selector and the textViews for jersey number display
    private Spinner jNumSelector1;
    private TextView jNumDisplay1;

    private Spinner jNumSelector2;
    private TextView jNumDisplay2;

    private Spinner jNumSelector3;
    private TextView jNumDisplay3;

    private Spinner jNumSelector4;
    private TextView jNumDisplay4;

    private Spinner jNumSelector5;
    private TextView jNumDisplay5;

    // Array of Spinners to more efficiently set (all display same values)
    private Spinner[] numSelectors;

    private int season = 2020;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        // TODO consider putting in a try/catch to prevent errors from team sizes being too small

        // setup screen from xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup);

        // initialize the team input widget
        teamSelector = findViewById(R.id.team_input);

        // initializes jersey number spinner and display text box objects to their corresponding widgets
        jNumSelector1 = findViewById(R.id.num1_input);
        jNumDisplay1 = findViewById(R.id.jersey_num1);

        jNumSelector2 = findViewById(R.id.num2_input);
        jNumDisplay2 = findViewById(R.id.jersey_num2);

        jNumSelector3 = findViewById(R.id.num3_input);
        jNumDisplay3 = findViewById(R.id.jersey_num3);

        jNumSelector4 = findViewById(R.id.num4_input);
        jNumDisplay4 = findViewById(R.id.jersey_num4);

        jNumSelector5 = findViewById(R.id.num5_input);
        jNumDisplay5 = findViewById(R.id.jersey_num5);

        // stores saved team names for display in spinner
        ArrayList<String> sUserTeams = new ArrayList<>();
        currentTeam = new Team(); // initialize currently selected

        // initialize array of jersey number spinners to hold the five spinners on the form
        numSelectors = new Spinner[]{jNumSelector1, jNumSelector2, jNumSelector3, jNumSelector4, jNumSelector5};


        userTeams = new ArrayList<>();

        //TODO - read data file on user teams, load as teams into array, then display team names in text box
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


        // ArrayAdapter for the list of team names (to be displayed in team selection Spinner)
        ArrayAdapter<String> teamAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sUserTeams);
        teamSelector.setAdapter(teamAdapter);

        // checks to see if the state of the team selector Spinner has changed
        teamSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            /**
             * runs when the item selected has changed
            */
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String sCurrentTeam = teamSelector.getSelectedItem().toString(); // get the team name selected in the
                jerseyNums = new ArrayList<>(); // initialize jersey number arraylist

                currentTeam = StatsManager.findTeam(sCurrentTeam);

                //loop through the arraylist of teams available (saved)
                for (int i = 0; i < userTeams.size(); i++) {
                    // check if the name selected in the team selection spinner matches the Team at i in the arraylist
                    if (sCurrentTeam.equals(userTeams.get(i).getName())) {
                        // if the String and team name match, set the currently selected team to the Team at i in the arrayList
                        currentTeam = userTeams.get(i);
                    }
                }

                System.out.println(currentTeam);

                // loop for the number of players on the currently selected team
                for (int i = 0; i < currentTeam.getNumPlayers(); i++ ) {
                    // add the jersey number of each player on that team to an arrayList of Integers
                    jerseyNums.add(i, currentTeam.getPlayers().get(i).getJerseyNum());
                }



                // sort the numbers in order for better display
                // TODO consider sorting at a different stage to prevent redundance (i.e. in ManageTeams)
                sortJerseyNums(jerseyNums, 0, jerseyNums.size() - 1);

                // initializes an ArrayAdapter for the jersey numbers
                jerseyAdapter = new ArrayAdapter<Integer>(getApplicationContext(), android.R.layout.simple_spinner_item, jerseyNums);

                // loop 5 times (five Spinners for jersey numbers)
                for (int i = 0; i < 5; i++) {
                    // set every array to display the jersey numbers available to select from on the given team
                    numSelectors[i].setAdapter(jerseyAdapter);
                    // set the selected item to a default value (first selector is set to first available number, etc.)
                    numSelectors[i].setSelection(i);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });


        // TODO error-check jersey numbers (i.e. each number can only be selected one time)

        // listeners for state change for each of the jersey number selector spinners
        jNumSelector1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            /**
             * Runs when the selected item changes
             */
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // sets the corresponding jersey number to the number selected in the Spinner
                jNumDisplay1.setText(jNumSelector1.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // repeat for the other four Spinners
        jNumSelector2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // sets the corresponding jersey number to the number selected in the Spinner
                jNumDisplay2.setText(jNumSelector2.getSelectedItem().toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        jNumSelector3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                jNumDisplay3.setText(jNumSelector3.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        jNumSelector4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                jNumDisplay4.setText(jNumSelector4.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        jNumSelector5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                jNumDisplay5.setText(jNumSelector5.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * Method that uses the quiksort algorithm to sort jersey numbers so they are in ascending order for display
     * @param nums - an Integer ArrayList of jersey numbers
     * @param left the leftmost ("start") index of the array
     * @param right the rightmost ("end") index of the array
     */
    public void sortJerseyNums(ArrayList<Integer> nums, int left, int right) {
        if (left >= right) {
            return;
        } else {
            // indices of the elements at the left/right of the array are stored as i and j
            int i = left;
            int j = right;

            // locate value in the middle of the array --> the pivot
            int pivot = nums.get((left + right) / 2);

            while (i < j) { // loop while i and j are on left/right side of array
                // while the value at i is less than the pivot
                // increment i (don't have to sort that element, it's already at the left of the pivot)
                while (nums.get(i) < pivot) {
                    i++;
                }
                // while the value at j is more than the pivot
                // decrement j (don't need to sort this element, it's at the right of the pivot already)
                while (pivot < nums.get(j)) {
                    j--;
                }
                if (i <= j) { // ensure that the indices are still on the left and right side of the array (otherwise, leave the loop)
                    // swap i and j (because they belong on opposite sides of where they are)
                    // e.g. if element at i was 11 and j was 7 and the pivot was 8, swap their positions
                    int temp = nums.get(i);
                    nums.set(i, nums.get(j));
                    nums.set(j, temp);

                    // move on to subsequent elements
                    i++;
                    j--;
                }
            }

            // then call the sort on a partition from the left half of the array (exclude pivot, as it is sorted correctly)
            sortJerseyNums(nums, left, j);
            // and the right half of the array
            sortJerseyNums(nums, i, right);
        }
    }

    public void onContinue(View v) { // user wants to continue to tracker
        // array of Players to store the five starting players
        Player[] startingLineup;
        // objects to represent the input for the user's team and the opponent's team
        oppTeam = findViewById(R.id.opp_input);
        // stores the value of that text input as a String
        String opp = oppTeam.getText().toString();

        // initialize the array of Players
        startingLineup = new Player[5];

        Game g = new Game(currentTeam, opp);  //needed before loop to create playerStat
        for(Player p:currentTeam.getPlayers()){
            p.addPlayerStat(g.getGameDateTime()); //create player stat with game time
        }


        // loop 5 times (through each number selection spinner)
        for(int i = 0; i < 5; i++) {
            // loop through every player on the chosen team's roster
            for (int j = 0; j < currentTeam.getNumPlayers(); j++) {
                // check if the number selected (in the selector at i in the array of selectors)
                // matches the Player at j in the ArrayList that represents a team's roster
                if(numSelectors[i].getSelectedItem().equals(currentTeam.getPlayers().get(j).getJerseyNum())) {
                    // add that player to the starting lineup
                    startingLineup[i] = currentTeam.getPlayers().get(j);
                }
            }
        }

        // TODO starts timer to track minutes
        // TODO add player stats when the game clock starts so minutes played are more accurate
        for (int i = 0; i < 5; i++) {
            startingLineup[i].addPlayerStat(System.currentTimeMillis());
        }


        Season s;
        if (currentTeam.getSeason(season) == null) { // if the current team doesn't have any seasons, make a new one
            s = new Season(currentTeam, season, season + 1);
        } else {
            s = currentTeam.getSeason(season);
        }

        if(Calendar.getInstance().get(Calendar.YEAR) > season){
            season++;
            s = new Season(currentTeam, season, season + 1);
        }
        currentTeam.addSeason(s);
        StatsManager.addSeason(s);

        StatsManager.setCurrentSeason(s);
        StatsManager.getCurrentSeason().addGame(g);
        // create a new game with attributes of the user's team, and their inputted opponent
        StatsManager.setCurrentGame(g);
        // set the Playing players in the game to the starting lineup to start (in the tracker)
        StatsManager.getCurrentGame().setPlaying(startingLineup);

        // sets the current player as a default to the first player listed in the starting lineup
        StatsManager.setCurrentPlayer(startingLineup[0]);

        launchGameTracker(v); // launch the tracker
    }

    /**
     * Method that launches the game tracker window
     * @param v
     */
    public void launchGameTracker(View v) {
        Intent i = new Intent(this, GameTimeTrackerActivity.class);
        startActivity(i);
    }
}
