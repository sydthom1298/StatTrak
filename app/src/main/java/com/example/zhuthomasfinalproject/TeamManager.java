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
    private Spinner spn_team; //team tracking stats for
    private TextView txt_numPlayers; //number of players on team
    private TextView txt_numActivePlayers; //number of active players on team
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

        //get a control object from the xml id of the control
        spn_team = (Spinner)findViewById(R.id.manage_team_selector);
        txt_numPlayers = (TextView)findViewById(R.id.num_players);
        txt_numActivePlayers = (TextView)findViewById(R.id.act_players);
        txt_newTeam = (TextView)findViewById(R.id.txt_new_team_name);
        txt_numPlayers.setText("0");
        txt_numActivePlayers.setText("0");
        ArrayList<Team> teamList;


        btn_addNewTeam = (Button)findViewById(R.id.btn_add_new_team);
        btn_saveTeam = (Button)findViewById(R.id.btn_save);
        btn_newPlayer = (Button)findViewById(R.id.btn_new_player);
        tbl_players = (TableLayout)findViewById(R.id.roster_table);
        tbl_row = (TableRow)findViewById(R.id.roster_row);
        tbl_header = (TableRow)findViewById(R.id.roster_header);

        if( StatsManager.getTeams().size() == 0) {
            StatsManager.addTeam(new Team("Select team to edit"));
        }
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<Team> adapter = new ArrayAdapter<Team>(this,
                R.layout.support_simple_spinner_dropdown_item, StatsManager.getTeams());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spn_team.setAdapter(adapter);
        spn_team.setOnItemSelectedListener(this);
        enableButtons(false);


        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }


    private void clearTable() {
        tbl_players.removeAllViews();
        tbl_players.addView(tbl_header);
    }
    private void enableButtons(boolean enable) {
        btn_newPlayer.setEnabled(enable);

        btn_saveTeam.setEnabled(enable);
    }
    private boolean firstTime = true;
    private Team selectedTeam;
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
        Team t;
        if(firstTime){
            firstTime = false;
            return;
        }
        clearTable();
        t = (Team) parent.getItemAtPosition(pos);
        if(t.getName().equals("Select team to edit")){
            playersCount = 0;
            txt_numPlayers.setText(Integer.toString(playersCount));
            enableButtons(false);
        }else{
            enableButtons(true);
        }
        selectedTeam = t;
        for(int i = 0; i < t.getNumPlayers(); i++){
            TableRow r = new TableRow(this);
            EditText n,num,position;
            CheckBox chk;
            r.addView(n =newName());
            r.addView(num = newNumber());
            r.addView(position = newPos());
            r.addView(chk = newCheck());
            Player p = t.getPlayers().get(i);
            n.setText(p.getName());
            num.setText(Integer.toString(p.getJerseyNum()));
            position.setText(p.getPosition());
            chk.setEnabled(p.isActive());

            tbl_players.addView(r,tbl_row.getLayoutParams());
            playersCount++;
            txt_numPlayers.setText(Integer.toString(playersCount));
        }

    }
    public void onNothingSelected(AdapterView<?> parent){
        String a = "Test";
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
        TextView t = (TextView)spn_team.getChildAt(spn_team.getChildCount()-1);
        txt_newTeam.setText("");
        spn_team.setSelection(spn_team.getAdapter().getCount()-1);
        spn_team.setSelection(spn_team.getAdapter().getCount()-1);

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
        EditText n,num,pos;
        CheckBox chk;
        r.addView(n =newName());
        r.addView(num = newNumber());
        r.addView(pos = newPos());
        r.addView(chk = newCheck());
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
        String numString;
        int num;

        t = selectedTeam;
        if( tbl_players.getChildCount() <= 1 ) {
            // error, no players.  Remove the Team
            Toast.makeText(getApplicationContext(),"Error 0 players.  Team has been deleted",Toast.LENGTH_LONG).show();
            StatsManager.removeTeam(team);
            return;
        }
        // stars as 1 because 0 is the heading
        for(int i = 1; i < tbl_players.getChildCount(); i++){
            TableRow r = (TableRow)tbl_players.getChildAt(i);

            EditText name = (EditText)r.getVirtualChildAt(0);
            EditText number = (EditText)r.getVirtualChildAt(1);
            EditText position = (EditText)r.getVirtualChildAt(2);
            CheckBox active = (CheckBox)r.getVirtualChildAt(3);

            if(name.getText().toString().equals("")) {
                name.setError("Must have a valid name");
                return;
            }
            if(number.getText().toString().equals("")) {
                name.setError("Must have a valid number");
                return;
            }


            numString = number.getText().toString();

            try{
                num = Integer.parseInt(numString);
            }catch(Exception x){
                number.setError("Must have a valid number");
                return;
            }
            p = t.findPlayer(name.getText().toString(), num);

            if(p != null){
                p.setPosition(position.getText().toString());
                p.setActive(active.isChecked());
            }else{
                p = new Player(name.getText().toString(), num);
                p.setPosition(position.getText().toString());
                p.setActive(active.isChecked());
                t.addPlayer(p);
            }


            StatsManager.toFile();

        }



    }


}
