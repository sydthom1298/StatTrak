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
    private ArrayList<String> jerseyNums;
    private ArrayList<Team> userTeams;
    private Spinner teamSelector;
    private Team currentTeam; // stores the current selected team as an object
    private boolean teamInit;
    private ArrayAdapter jerseyAdapter;
    ;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup);


        Spinner jerseyNumSelect1 = findViewById(R.id.num1_input);
        Spinner jerseyNumSelect2 = findViewById(R.id.num2_input);
        Spinner jerseyNumSelect3 = findViewById(R.id.num3_input);
        Spinner jerseyNumSelect4 = findViewById(R.id.num4_input);
        Spinner jerseyNumSelect5 = findViewById(R.id.num5_input);


        // stores saved team names for display in spinner
        ArrayList<String> sUserTeams = new ArrayList<>();
        currentTeam = new Team(); // initialize currently selected
        // stores the current selected team as a String
        String sCurrentTeam;

        // arrays to store the 5 spinners and 5 jersey number displays
        final Spinner[] numSelectors = {jerseyNumSelect1, jerseyNumSelect2,
                jerseyNumSelect3, jerseyNumSelect4, jerseyNumSelect5};



        //TODO - read data file on user teams, load as teams into array, then display team names in text box
        userTeams = new ArrayList<>();


        Player tempPlayer1 = new Player("Jessica", 8);
        Player tempPlayer2 = new Player("Sydney",11);
        Player tempPlayer3 = new Player("Emma", 5);
        Player tempPlayer4 = new Player("Dani", 6);
        Player tempPlayer5 = new Player("Maddie", 9);
        ArrayList<Player> tempPlayers = new ArrayList<>();
        tempPlayers.add(tempPlayer1);
        tempPlayers.add(tempPlayer2);
        tempPlayers.add(tempPlayer3);
        tempPlayers.add(tempPlayer4);
        tempPlayers.add(tempPlayer5);
        Team team = new Team("Gryphs", tempPlayers);

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
                if (!teamInit) {
                    teamInit = true;
                    return;
                }

                String sCurrentTeam = teamSelector.getSelectedItem().toString();
                jerseyNums = new ArrayList<>();

                for (int i = 0; i < userTeams.size(); i++) {
                    if (sCurrentTeam.equals(userTeams.get(i).getName())) {
                        currentTeam = userTeams.get(i);
                        System.out.println(currentTeam);
                    }
                }

                for (int i = 0; i < currentTeam.getNumPlayers(); i++ ) {
                    jerseyNums.add(i, Integer.toString(currentTeam.getPlayers().get(i).getJerseyNum()));
                }
                jerseyAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, jerseyNums);

                for (int i = 0; i < 5; i++) {
                    numSelectors[i].setAdapter(jerseyAdapter);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

    }



    public void onContinue(View v) { // user wants to continue to tracker
        // objects to represent the input for the user's team and the opponent's team
        //TextView txtOppTeam = findViewById(R.id.opp_input);
        //String opp = txtOppTeam.getText().toString();
        //Team sCurrentTeam = txtCurrentTeam.getText();

        //StatsManager.getCurrentGame().setTeam();
        //StatsManager.getCurrentGame().setOpponent(opp);
        launchGameTracker(v); // launch the tracker*/

    }

    public void launchGameTracker(View v) {
        Intent i = new Intent(this, GameTimeTrackerActivity.class);
        startActivity(i);
    }
}
