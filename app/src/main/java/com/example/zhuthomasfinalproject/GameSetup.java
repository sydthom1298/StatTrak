package com.example.zhuthomasfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.TextView;


import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class GameSetup extends AppCompatActivity {
    private ArrayList<Integer> jerseyNums;
    private ArrayList<Integer> jerseyNums1;
    private ArrayList<Integer> jerseyNums2;
    private ArrayList<Integer> jerseyNums3;
    private ArrayList<Integer> jerseyNums4;
    private ArrayList<Integer> jerseyNums5;
    private ArrayList<Team> userTeams;
    private Spinner teamSelector;
    private Team currentTeam; // stores the current selected team as an object
    private ArrayAdapter jerseyAdapter;

    private Player[] startingLineup;

    private EditText oppTeam;

    Spinner jNumSelector1;
    TextView jNumDisplay1;

    Spinner jNumSelector2;
    TextView jNumDisplay2;

    Spinner jNumSelector3;
    TextView jNumDisplay3;

    Spinner jNumSelector4;
    TextView jNumDisplay4;

    Spinner jNumSelector5;
    TextView jNumDisplay5;

    private Spinner[] numSelectors;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup);

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

        numSelectors = new Spinner[]{jNumSelector1, jNumSelector2, jNumSelector3, jNumSelector4, jNumSelector5};

        //TODO - read data file on user teams, load as teams into array, then display team names in text box
        userTeams = new ArrayList<>();

        // sample data
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


        // store the team_input view widget
        teamSelector = findViewById(R.id.team_input);


        // loops through saved teams, saves team names in ArrayList
        for(int i = 0; i < userTeams.size(); i++) {
            sUserTeams.add(i, userTeams.get(i).getName());
        }

        // ArrayAdapter for the list of team names (to be displayed in combo box)
        ArrayAdapter<String> teamAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sUserTeams);
        teamSelector.setAdapter(teamAdapter);



        teamSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String sCurrentTeam = teamSelector.getSelectedItem().toString();
                jerseyNums = new ArrayList<>();

                for (int i = 0; i < userTeams.size(); i++) {
                    if (sCurrentTeam.equals(userTeams.get(i).getName())) {
                        currentTeam = userTeams.get(i);
                        System.out.println(currentTeam);
                    }
                }

                for (int i = 0; i < currentTeam.getNumPlayers(); i++ ) {
                    jerseyNums.add(i, currentTeam.getPlayers().get(i).getJerseyNum());
                }
                sortJerseyNums(jerseyNums, 0, jerseyNums.size() - 1);
                jerseyAdapter = new ArrayAdapter<Integer>(getApplicationContext(), android.R.layout.simple_spinner_item, jerseyNums);

                for (int i = 0; i < 5; i++) {
                    numSelectors[i].setAdapter(jerseyAdapter);
                    numSelectors[i].setSelection(i);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });



        jNumSelector1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                jNumDisplay1.setText(jNumSelector1.getSelectedItem().toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        jNumSelector2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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
        // objects to represent the input for the user's team and the opponent's team
        oppTeam = findViewById(R.id.opp_input);
        String opp = oppTeam.getText().toString();

        startingLineup = new Player[5];
        for(int i = 0; i < 5; i++) {
            for (int j = 0; j < currentTeam.getNumPlayers(); j++) {
                if(numSelectors[i].getSelectedItem().equals(currentTeam.getPlayers().get(j).getJerseyNum())) {
                    startingLineup[i] = currentTeam.getPlayers().get(j);
                }
            }
        }


        StatsManager.setCurrentGame(new Game(currentTeam, opp));
        StatsManager.getCurrentGame().setPlaying(startingLineup);

        for (int i = 0; i < 5; i++) {
            long t = System.currentTimeMillis();
            StatsManager.getCurrentGame().getTeam().getPlayers().get(i).addPlayerStat(t);
        }
        StatsManager.setCurrentPlayer(startingLineup[0]);




        launchGameTracker(v); // launch the tracker
    }

    public void launchGameTracker(View v) {
        Intent i = new Intent(this, GameTimeTrackerActivity.class);
        startActivity(i);
    }
}
