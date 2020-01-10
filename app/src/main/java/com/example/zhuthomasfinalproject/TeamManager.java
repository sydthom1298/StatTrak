package com.example.zhuthomasfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
public class TeamManager extends AppCompatActivity{
    private Spinner spn_team; //team tracking stats for
    private TextView txt_numPlayers; //number of players on team
    private TextView txt_numActivePlayers; //number of active players on team
    private TextView txt_players[]; //array of players on team
    private TextView txt_jerseyNums[];//array of jersey numbers
    private TextView txt_activePlayers[];
    private TextView txt_newTeam;
    private Button btn_addNewTeam;
    private Button btn_saveTeam;
    private Button btn_newPlayer;
    private int num[]; //array of players jersey number of team
    private Team team;
    /**
     * called when TeamManager window starts up
     * @param savedInstanceState - used by the system
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        //set up screen from xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_manager);
        //set size of arrays
        txt_players = new TextView[StatsManager.getCurrentGame().getTeam().getNumPlayers()];
        txt_jerseyNums = new TextView[StatsManager.getCurrentGame().getTeam().getNumPlayers()];
        txt_activePlayers = new TextView[StatsManager.getCurrentGame().getTeam().getNumPlayers()];
        //get a control object from the xml id of the control
        spn_team = (Spinner)findViewById(R.id.manage_team_selector);
        txt_numPlayers = (TextView)findViewById(R.id.num_players);
        txt_numActivePlayers = (TextView)findViewById(R.id.act_players);
        txt_newTeam = (TextView)findViewById(R.id.txt_new_team_name);

        txt_players[0] = (TextView)findViewById(R.id.player1); //first player
        txt_players[1] = (TextView)findViewById(R.id.player2); //second player
        txt_players[2] = (TextView)findViewById(R.id.player3); //third player
        txt_players[3] = (TextView)findViewById(R.id.player4); //fourth player
        txt_players[4] = (TextView)findViewById(R.id.player5); //fifth player
        txt_players[5] = (TextView)findViewById(R.id.player6); //sixth player
        txt_players[6] = (TextView)findViewById(R.id.player7); //seventh player
        txt_players[7] = (TextView)findViewById(R.id.player8); //eighth player
        txt_players[8] = (TextView)findViewById(R.id.player9); //ninth player
        txt_players[9] = (TextView)findViewById(R.id.player10); //tenth player
        txt_players[10] = (TextView)findViewById(R.id.player11); //eleventh player

        txt_jerseyNums[0] = (TextView)findViewById(R.id.jersey1); //first players jersey number
        txt_jerseyNums[1] = (TextView)findViewById(R.id.jersey2); //second players jersey number
        txt_jerseyNums[2] = (TextView)findViewById(R.id.jersey3); //third players jersey number
        txt_jerseyNums[3] = (TextView)findViewById(R.id.jersey4); //fourth players jersey number
        txt_jerseyNums[4] = (TextView)findViewById(R.id.jersey5); //fifth players jersey number
        txt_jerseyNums[5] = (TextView)findViewById(R.id.jersey6); //sixth players jersey number
        txt_jerseyNums[6] = (TextView)findViewById(R.id.jersey7); //seventh players jersey number
        txt_jerseyNums[7] = (TextView)findViewById(R.id.jersey8); //eighth players jersey number
        txt_jerseyNums[8] = (TextView)findViewById(R.id.jersey9); //ninth players jersey number
        txt_jerseyNums[9] = (TextView)findViewById(R.id.jersey10); //tenth players jersey number
        txt_jerseyNums[10] = (TextView)findViewById(R.id.jersey11); //eleventh players jersey number

        txt_activePlayers[0] = (TextView)findViewById(R.id.cb_1); //if player one is playing in game
        txt_activePlayers[1] = (TextView)findViewById(R.id.cb_2); //if player two is playing in game
        txt_activePlayers[2] = (TextView)findViewById(R.id.cb_3); //if player three is playing in game
        txt_activePlayers[3] = (TextView)findViewById(R.id.cb_4); //if player four is playing in game
        txt_activePlayers[4] = (TextView)findViewById(R.id.cb_5); //if player five is playing in game
        txt_activePlayers[5] = (TextView)findViewById(R.id.cb_6); //if player six is playing in game
        txt_activePlayers[6] = (TextView)findViewById(R.id.cb_7); //if player seven is playing in game
        txt_activePlayers[7] = (TextView)findViewById(R.id.cb_8); //if player eight is playing in game
        txt_activePlayers[8] = (TextView)findViewById(R.id.cb_9); //if player nine is playing in game
        txt_activePlayers[9] = (TextView)findViewById(R.id.cb_10); //if player ten is playing in game
        txt_activePlayers[10] = (TextView)findViewById(R.id.cb_11); //if player eleven is playing in game

        btn_addNewTeam = (Button)findViewById(R.id.btn_add_new_team);
        btn_saveTeam = (Button)findViewById(R.id.btn_save);
        btn_newPlayer = (Button)findViewById(R.id.btn_new_player);

    }

    /**
     * method to create new team when the create new team button is pressed
     * @param v - current window
     */
    public void onCreateNewTeam(View v){
        String teamName;
        teamName = txt_newTeam.getText().toString();
        team = new Team(teamName);
        StatsManager.addTeam(team);

    }

    /**
     * method to create new player when the new player button is pressed
     * @param v - current window
     */
    public void onNewPlayer(View v){

        StatsManager.getCurrentGame().getTeam();
    }

    /**
     * method to save changes to team when the save changes button is pressed
     * @param v - current window
     */
    public void onSaveChanges(View v){
        StatsManager.getCurrentGame().getTeam();
    }
    //if user has selected a pre existing team
    /*
    if(!spn_team.equals("n/a")){

    }*/

}
