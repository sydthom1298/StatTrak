/**
 * Sydney Thomas
 * January 14, 2020
 * Class that creates and edits Teams
 */
package com.example.zhuthomasfinalproject;
import android.Manifest;
import android.content.pm.PackageManager;
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
import android.widget.Toast;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class TeamManager extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private Spinner spn_team; //spinner control to choose a team
    private TextView txt_numPlayers; //control that shows the number of players on team
    private TextView txt_numActivePlayers; //control that shows the number of active players on team
    private TextView txt_newTeam; //control for user to enter team name
    private Button btn_saveTeam; //button used to save information about a team
    private Button btn_newPlayer; //button used to add a new player
    private Team team; //team currently creating
    private TableLayout tbl_players; //table layout to show the player list
    private TableRow tbl_header; //titles of the table layout
    private TableRow tbl_row; //row for a player in tbl_players
    private int playersCount; //how many players you currently have on the team
    private boolean firstTime = true; //ignore first selected event for table
    private Team selectedTeam; //team selected from spinner
    /**
     * called when TeamManager window starts up
     * @param savedInstanceState - used by the system
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        int permission;
        //set up screen from xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_manager);

        //get a control object from the xml id of the control
        spn_team = (Spinner)findViewById(R.id.manage_team_selector);
        txt_numPlayers = (TextView)findViewById(R.id.num_players);
        txt_numActivePlayers = (TextView)findViewById(R.id.act_players);
        txt_newTeam = (TextView)findViewById(R.id.txt_new_team_name);
        btn_saveTeam = (Button)findViewById(R.id.btn_save);
        btn_newPlayer = (Button)findViewById(R.id.btn_new_player);
        tbl_players = (TableLayout)findViewById(R.id.roster_table);
        tbl_row = (TableRow)findViewById(R.id.roster_row);
        tbl_header = (TableRow)findViewById(R.id.roster_header);
        //initialize player count to 0
        txt_numPlayers.setText("0");
        txt_numActivePlayers.setText("0");
        //ArrayList of teams for spinner
        ArrayList<Team> teamList;

        //prompt in spinner
        if(StatsManager.getTeams().size() == 0){
            StatsManager.addTeam(new Team("Select team to edit"));
        }
        //Create an ArrayAdapter using the String array and a default spinner layout
        ArrayAdapter<Team> adapter = new ArrayAdapter<Team>(this,
                R.layout.support_simple_spinner_dropdown_item, StatsManager.getTeams());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_team.setAdapter(adapter);
        spn_team.setOnItemSelectedListener(this);
        enableButtons(false);


        //Check if we have write permission
        permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if(permission != PackageManager.PERMISSION_GRANTED){
            //We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }

    /**
     * method that clears the table of players
     */
    private void clearTable() {
        tbl_players.removeAllViews();
        tbl_players.addView(tbl_header);
    }

    /**
     * method that enables the newPlayer and saveTeam buttons
     * @param enable - true if enabled and false if disabled
     */
    private void enableButtons(boolean enable) {
        btn_newPlayer.setEnabled(enable);
        btn_saveTeam.setEnabled(enable);
    }

    /**
     * user has selected an item from the spinner
     * @param parent - spinner
     * @param view - comes with event
     * @param pos - row of the selected item in table
     * @param id - comes with event
     */
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
        Team t; //team selected from spinner control
        EditText n; //name control
        EditText num; //number control
        EditText position; //position control
        CheckBox chk; //active checkbox
        TableRow r; //table row
        Player p;

        //ignore the first selection event
        if(firstTime){
            firstTime = false;
            return;
        }
        //remove any players from player table
        clearTable();
        //get the currently selected team from the spinner
        t = (Team) parent.getItemAtPosition(pos);
        //if the user selects the prompt entry for team instead of a real team
        if(t.getName().equals("Select team to edit")){
            playersCount = 0;
            txt_numPlayers.setText(Integer.toString(playersCount));
            enableButtons(false);
        }else{ //its a real team
            enableButtons(true);
        }
        //saves team as selected team
        selectedTeam = t;
        //add all the player into the player table
        for(int i = 0; i < t.getNumPlayers(); i++){
            //create a new table row
            r = new TableRow(this);
            //create controls for within table row
            n = newName();
            num = newNumber();
            position = newPos();
            chk = newCheck();
            //add controls to table row
            r.addView(n);
            r.addView(num);
            r.addView(position);
            r.addView(chk);
            //get player from team object
            p = t.getPlayers().get(i);
            //set data from player to controls for table row
            n.setText(p.getName());
            num.setText(Integer.toString(p.getJerseyNum()));
            position.setText(p.getPosition());
            chk.setEnabled(p.isActive());
            //add table row to table
            tbl_players.addView(r,tbl_row.getLayoutParams());
            //updates player count
            playersCount++;
            txt_numPlayers.setText(Integer.toString(playersCount));
        }

    }

    /**
     * method required for the interface for when nothing is selected
     * @param parent - window
     */
    public void onNothingSelected(AdapterView<?> parent){

    }
    /**
     * method to create new team when the create new team button is pressed
     * @param v - current window
     */
    public void onCreateNewTeam(View v){
        String teamName; //name of team
        //get name of team from edit text control
        teamName = txt_newTeam.getText().toString();
        //create a new team
        team = new Team(teamName);
        StatsManager.addTeam(team);
        //clears table, set number of players to zero
        clearTable();
        txt_numPlayers.setText(Integer.toString(0));
        playersCount = 0;
        txt_newTeam.setText("");
        //set spinner to new team added
        spn_team.setSelection(spn_team.getAdapter().getCount() - 1);

    }

    /**
     * method that generates a new edit text control for the name
     * @return - new edit text control
     */
    EditText newName() {
        EditText e;
        EditText orig;
        e = new EditText(this);
        //copies layout info from orig to new e control
        orig = (EditText)(tbl_row.getVirtualChildAt(0));
        e.setLayoutParams(tbl_row.getVirtualChildAt(0).getLayoutParams());
        e.setFontFeatureSettings(orig.getFontFeatureSettings());
        e.setTypeface(orig.getTypeface());
        e.setTextSize(orig.getTextSize());
        return e;
    }

    /**
     * method that generates a new edit text control for number
     * @return - new edit text control
     */
    EditText newNumber() {
        EditText e;
        EditText orig;
        e = new EditText(this);
        //copies layout info from orig to new e control
        orig = (EditText)(tbl_row.getVirtualChildAt(1));
        e.setFontFeatureSettings(orig.getFontFeatureSettings());
        e.setTypeface(orig.getTypeface());
        e.setTextSize(orig.getTextSize());
        return e;
    }
    /**
     * method that generates a new edit text control for position
     * @return - new edit text control
     */
    EditText newPos() {
        EditText e;
        EditText orig;
        e = new EditText(this);
        //copies layout info from orig to new e control
        orig = (EditText)(tbl_row.getVirtualChildAt(2));
        e.setFontFeatureSettings(orig.getFontFeatureSettings());
        e.setTypeface(orig.getTypeface());
        e.setTextSize(orig.getTextSize());
        return e;
    }

    /**
     * method that generates a new active player check box
     * @return - new check box control
     */
    CheckBox newCheck() {
        CheckBox c;
        c = new CheckBox(this);
        c.setLayoutParams(tbl_row.getVirtualChildAt(3).getLayoutParams());
        return c;
    }
    /**
     * method to create new player when the new player button is pressed
     * @param v - current window
     */
    public void onNewPlayer(View v){
        TableRow r;
        EditText n;
        EditText num;
        EditText pos;
        CheckBox chk;
        r = new TableRow(this); //create new table row
        //create new control for table row
        n = newName();
        num = newNumber();
        pos = newPos();
        chk = newCheck();
        //add controls to table row
        r.addView(n);
        r.addView(num);
        r.addView(pos);
        r.addView(chk);
        //add table row to table
        tbl_players.addView(r,tbl_row.getLayoutParams());
        //update player count on screen
        playersCount++;
        txt_numPlayers.setText(Integer.toString(playersCount));
    }


    /**
     * method to save changes to team when the save changes button is pressed
     * @param v - current window
     */
    public void onSaveChanges(View v){
        Player p; //player
        Team t; //team player is on
        String numString; //player number as a string
        int num; //player number
        EditText name; //name control
        EditText number; //number control
        EditText position; //position control
        CheckBox active; //active checkbox control
        TableRow r; //row in table


        t = selectedTeam;
        //if there is not enough players in the table
        if(tbl_players.getChildCount() <= 1){
            //display error message, no players and remove the team
            Toast.makeText(getApplicationContext(),"Error 0 players.  Team has been deleted",Toast.LENGTH_LONG).show();
            StatsManager.removeTeam(team);
            return;
        }

        //starts as 1 because 0 is the heading
        for(int i = 1; i < tbl_players.getChildCount(); i++){
            //get current row
            r = (TableRow)tbl_players.getChildAt(i);
            //read player information
            name = (EditText)r.getVirtualChildAt(0);
            number = (EditText)r.getVirtualChildAt(1);
            position = (EditText)r.getVirtualChildAt(2);
            active = (CheckBox)r.getVirtualChildAt(3);
            //requires name
            if(name.getText().toString().equals("")) {
                name.setError("Must have a valid name");
                return;
            }
            //requires number
            if(number.getText().toString().equals("")) {
                name.setError("Must have a valid number");
                return;
            }

            //get player number and converts it
            numString = number.getText().toString();
            try{
                num = Integer.parseInt(numString);
            }catch(Exception x){
                number.setError("Must have a valid number");
                return;
            }
            //see if player exists
            p = t.findPlayer(name.getText().toString(), num);
            //if the player exists
            if(p != null){
                //update existing player
                p.setPosition(position.getText().toString());
                p.setActive(active.isChecked());
            }else{ //player doesn't exist
                //create new player
                p = new Player(name.getText().toString(), num);
                p.setPosition(position.getText().toString());
                p.setActive(active.isChecked());
                t.addPlayer(p);
            }
            //saves everything including teams
            StatsManager.toFile();
        }
    }
}
