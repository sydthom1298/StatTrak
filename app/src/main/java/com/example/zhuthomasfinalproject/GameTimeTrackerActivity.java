package com.example.zhuthomasfinalproject;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class GameTimeTrackerActivity extends AppCompatActivity {

    private TextView txt_points;
    private TextView txt_quarter;
    private TextView txt_fouls;

    private ListView lst_players;
    private TextView lst_item_text;
    private TextView txt_timer;
    private ToggleButton selectedButton;
    private int selectedPlayerIndex = 0;
    private ToggleButton playerButtons[] = new ToggleButton[5];
    private GameClock clock = new GameClock(8000*60, 1000 );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_time_tracker);
        StatsManager.initStatsManager();
        txt_points = (TextView)findViewById(R.id.team_points);
        txt_quarter = (TextView)findViewById(R.id.quarter);
        txt_fouls = (TextView)findViewById(R.id.team_fouls);
        txt_timer = (TextView)findViewById(R.id.timer);
        playerButtons[0] = (ToggleButton)findViewById(R.id.btn_player1);
        playerButtons[1] = (ToggleButton)findViewById(R.id.btn_player2);
        playerButtons[2] = (ToggleButton)findViewById(R.id.btn_player3);
        playerButtons[3] = (ToggleButton)findViewById(R.id.btn_player4);
        playerButtons[4] = (ToggleButton)findViewById(R.id.btn_player5);
        selectedButton = playerButtons[0];
        lst_players = (ListView)findViewById(R.id.list_players);
        lst_item_text = (TextView)findViewById(R.id.list_item_text);

        txt_points.setText("0");
        txt_quarter.setText("q1");
        txt_fouls.setText("0");
        playerButtons[0].setBackgroundColor(0xFF245300);
        playerButtons[0].setText(Integer.toString(StatsManager.getCurrentGame().getPlaying()[0].getJerseyNum()));
        playerButtons[1].setText(Integer.toString(StatsManager.getCurrentGame().getPlaying()[1].getJerseyNum()));
        playerButtons[2].setText(Integer.toString(StatsManager.getCurrentGame().getPlaying()[2].getJerseyNum()));
        playerButtons[3].setText(Integer.toString(StatsManager.getCurrentGame().getPlaying()[3].getJerseyNum()));
        playerButtons[4].setText(Integer.toString(StatsManager.getCurrentGame().getPlaying()[4].getJerseyNum()));

        final ArrayList<Player> list = StatsManager.getCurrentGame().getTeam().getPlayers();
        Player[] array = list.toArray( new Player[list.size()] );
        lst_players.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Player p=list.get(position);
                String display="You have clicked "+p.toString();
                System.out.println(display);
                lst_players.setVisibility(View.INVISIBLE);
                StatsManager.setCurrentPlayer(p);
                selectedButton.setText(Integer.toString(StatsManager.getCurrentPlayer().getJerseyNum()));
                StatsManager.getCurrentGame().getPlaying()[selectedPlayerIndex] = p;
            }
        });

        ArrayAdapter <Player> adapter = new ArrayAdapter<Player>(this,
                R.layout.list_view_item, R.id.list_item_text, array);

        lst_players.setAdapter(adapter);
    }
    public void onTwoPtMakes(View v){
        PlayerStats s = StatsManager.getCurrentPlayer().getCurrentStats();
        s.addTwoPtMakes();
        s.addPoints(2);

        StatsManager.getCurrentGame().addPoints(2);

        txt_points.setText(Integer.toString(StatsManager.getCurrentGame().getPoints()));

    }
    public void onTwoPtMisses(View v) {
        PlayerStats s = StatsManager.getCurrentPlayer().getCurrentStats();
        s.addTwoPtMisses();

    }
    public void onThreePtMakes(View v){
        PlayerStats s = StatsManager.getCurrentPlayer().getCurrentStats();
        s.addThreePtMakes();
        s.addPoints(3);
        StatsManager.getCurrentGame().addPoints(3);

        txt_points.setText(Integer.toString(StatsManager.getCurrentGame().getPoints()));
    }
    public void onThreePtMisses(View v){
        StatsManager.getCurrentPlayer().getCurrentStats().addThreePtMisses();
    }
    public void onFtMakes(View v){
        PlayerStats s = StatsManager.getCurrentPlayer().getCurrentStats();
        s.addFtMakes();

        s.addPoints(1);
        StatsManager.getCurrentGame().addPoints(1);

        txt_points.setText(Integer.toString(StatsManager.getCurrentGame().getPoints()));
    }
    public void onFtMisses(View v){
        StatsManager.getCurrentPlayer().getCurrentStats().addFtMisses();
    }
    public void onAssists(View v){
        StatsManager.getCurrentPlayer().getCurrentStats().addAssists();
    }
    public void onOffRebs(View v){
        StatsManager.getCurrentPlayer().getCurrentStats().addOffRebs();
    }
    public void onDefRebs(View v){
        StatsManager.getCurrentPlayer().getCurrentStats().addDefRebs();
    }
    public void onSteals(View v){
        StatsManager.getCurrentPlayer().getCurrentStats().addSteals();
    }
    public void onTurnovers(View v){
        StatsManager.getCurrentPlayer().getCurrentStats().addTurnovers();
    }
    public void onBlocks(View v){
        StatsManager.getCurrentPlayer().getCurrentStats().addBlocks();
    }
    public void onFouls(View v){
        StatsManager.getCurrentPlayer().getCurrentStats().addFouls();
        StatsManager.getCurrentGame().addTeamFouls();

        txt_fouls.setText(Integer.toString(StatsManager.getCurrentGame().getTeamFouls()));

        // TEST
        System.out.println(StatsManager.getCurrentPlayer().getCurrentStats().toString());
    }
    public void onQuarter(View v) {
        String current = txt_quarter.getText().toString();
        if( current.equals("q1")) {
            txt_quarter.setText("q2");
        } else if( current.equals("q2")) {
            txt_quarter.setText("q3");
        } else if( current.equals("q3")) {
            txt_quarter.setText("q4");
        } else if( current.equals("q4")) {
            txt_quarter.setText("q1");
        }
    }

    public void onPlayer1(View v) {
        handlePlayerButtons(0);
    }
    public void onPlayer2(View v) {
        handlePlayerButtons(1);
    }
    public void onPlayer3(View v) {
        handlePlayerButtons(2);
    }
    public void onPlayer4(View v) {
        handlePlayerButtons(3);
    }
    public void onPlayer5(View v) {
        handlePlayerButtons(4);
    }
    public void handlePlayerButtons(int index) {
        Game game = StatsManager.getCurrentGame();

        if( game.getPlaying()[index].equals(StatsManager.getCurrentPlayer())) {
            lst_players.setVisibility(View.VISIBLE);
            lst_players.setZ(10);
        } else {
            StatsManager.setCurrentPlayer(StatsManager.getCurrentGame().getPlaying()[index]);
        }

        selectedButton = playerButtons[index];
        selectedPlayerIndex = index;
        playerButtons[index].setText(Integer.toString(StatsManager.getCurrentPlayer().getJerseyNum()));

        for( int i=0; i<playerButtons.length; i++) {
            if(i == index) {
                playerButtons[i].setBackgroundColor(0xFF245300);
            } else {
                playerButtons[i].setBackgroundColor(0xFF245354);
            }
        }

    }
    public void onNextQuarter(View v) {
        onQuarter(v);
        txt_timer.setText("8:00");
    }
    public void onClock(View v) {
        if (clock.isRunning()) {
            clock.pause();
        } else
            clock.start();
    }

}

