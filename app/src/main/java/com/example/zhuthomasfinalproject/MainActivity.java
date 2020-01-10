package com.example.zhuthomasfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        //variables that store the attributes of the Buttons on the menu
        final Button btn_manage_teams = findViewById(R.id.manage_teams);
        final Button btn_credits = findViewById(R.id.credits);
        /*
*/
/*

        btn_manage_teams.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //executes on main thread after user selects "credits" button
                //redirects to team manager layout
                setContentView(R.layout.team_manager);
            }
        });
        */


        btn_credits.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //executes on main thread after user selects "credits" button
                //redirects to credits layout
                setContentView(R.layout.credits);
            }
        });

    }


    public void launchGameSetup(View v) {
        Intent i = new Intent(this, GameSetup.class);
        startActivity(i);

    }

    public void launchStatModeSelection(View v) {
        Intent i = new Intent(this, StatModeSelection.class);
        startActivity(i);
    }

    public void launchTeamManager(View v) {
        Intent i = new Intent(this, TeamManager.class);
        startActivity(i);
    }
}
