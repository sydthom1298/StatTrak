package com.example.zhuthomasfinalproject;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TeamManager extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
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
    private TableLayout tbl_players;
    private TableRow tbl_header;
    private TableRow tbl_row;
    private int playersCount;
    private int activePlayersCount;

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
        txt_players = new TextView[15];
        txt_jerseyNums = new TextView[15];
        txt_activePlayers = new TextView[15];
        //get a control object from the xml id of the control
        spn_team = (Spinner)findViewById(R.id.manage_team_selector);
        txt_numPlayers = (TextView)findViewById(R.id.num_players);
        txt_numActivePlayers = (TextView)findViewById(R.id.act_players);
        txt_newTeam = (TextView)findViewById(R.id.txt_new_team_name);
        txt_numPlayers.setText("0");
        txt_numActivePlayers.setText("0");
        ArrayList<Team> teamList;


/* DON't NEED THESE
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
*/
        btn_addNewTeam = (Button)findViewById(R.id.btn_add_new_team);
        btn_saveTeam = (Button)findViewById(R.id.btn_save);
        btn_newPlayer = (Button)findViewById(R.id.btn_new_player);
        tbl_players = (TableLayout)findViewById(R.id.roster_table);
        tbl_row = (TableRow)findViewById(R.id.roster_row);
        tbl_header = (TableRow)findViewById(R.id.roster_header);

        clearTable();

        teamList = StatsManager.getTeams();
        teamList.add(new Team("Test1"));
        teamList.add(new Team("Test2"));
        Team[] array = teamList.toArray(new Team[teamList.size()]);

        // Create an ArrayAdapter using the string array and a default spinner layout
    //    ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,
     //           R.layout.team_manager, R.id.manage_team_selector, array);
      //  spn_team.setAdapter(adapter);
    }

    public void clearTable() {
        tbl_players.removeAllViews();
        tbl_players.addView(tbl_header);
    }
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){

    }
    public void onNothingSelected(AdapterView<?> parent){

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
        clearTable();
        txt_numPlayers.setText(Integer.toString(0));
        playersCount = 0; 
    }

    EditText newName() {
        EditText e = new EditText(this);
        EditText orig = (EditText)(tbl_row.getVirtualChildAt(0));
        e.setLayoutParams(tbl_row.getVirtualChildAt(0).getLayoutParams());
        e.setFontFeatureSettings(orig.getFontFeatureSettings());
        e.setTypeface(orig.getTypeface());
        e.setTextSize(orig.getTextSize());
        return e;
    }
    EditText newNumber() {
        EditText e = new EditText(this);
        EditText orig = (EditText)(tbl_row.getVirtualChildAt(1));
        e.setFontFeatureSettings(orig.getFontFeatureSettings());
        e.setTypeface(orig.getTypeface());
        e.setTextSize(orig.getTextSize());
        return e;
    }
    EditText newPos() {
        EditText e = new EditText(this);
        EditText orig = (EditText)(tbl_row.getVirtualChildAt(2));
        e.setFontFeatureSettings(orig.getFontFeatureSettings());
        e.setTypeface(orig.getTypeface());
        e.setTextSize(orig.getTextSize());
        return e;
    }
    CheckBox newCheck() {
        CheckBox c = new CheckBox(this);
        c.setLayoutParams(tbl_row.getVirtualChildAt(3).getLayoutParams());
        return c;
    }
    /**
     * method to create new player when the new player button is pressed
     * @param v - current window
     */
    public void onNewPlayer(View v){
        TableRow r = new TableRow(this);
        r.addView(newName());
        r.addView(newNumber());
        r.addView(newPos());
        r.addView(newCheck());
        tbl_players.addView(r,tbl_row.getLayoutParams());
        playersCount++;
        txt_numPlayers.setText(Integer.toString(playersCount));


    }

    // TODO Add way to scroll player list so that the cell being edited is above the popup keyboard

    /**
     * method to save changes to team when the save changes button is pressed
     * @param v - current window
     */
    public void onSaveChanges(View v){
        Player p;
        Team t;
        t = new Team(txt_newTeam.getText().toString());
        for(int i = 0; i < tbl_players.getChildCount(); i++){
            TableRow r = (TableRow)tbl_players.getChildAt(i);


            EditText name = (EditText)r.getVirtualChildAt(0);
            EditText number = (EditText)r.getVirtualChildAt(1);
            EditText position = (EditText)r.getVirtualChildAt(2);
            CheckBox active = (CheckBox)r.getVirtualChildAt(3);

            name.getText();
            number.getText();
            position.getText();
            active.isChecked();

            p = new Player(name.getText().toString(), Integer.parseInt(number.getText().toString()));
            p.setPosition(position.getText().toString());
            p.setActive(active.isChecked());
            t.addPlayer(p);

        }



    }
    //if user has selected a pre existing team
    /*
    if(!spn_team.equals("n/a")){

    }*/

}
