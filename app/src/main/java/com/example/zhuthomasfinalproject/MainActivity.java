package com.example.zhuthomasfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        // variables that store the attributes of the Buttons on the menu
        final Button btn_new_game = findViewById(R.id.track_new);
        final Button btn_view_stats = findViewById(R.id.view_stats);
        final Button btn_manage_teams = findViewById(R.id.manage_teams);
        final Button btn_credits = findViewById(R.id.credits);

        //event listeners to check when buttons are selected by the user
        btn_new_game.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // executes on main thread after user selects "track new game" button
                // redirects to game setup layout
                setContentView(R.layout.setup);

                final Button btn_setup_cont = findViewById(R.id.setup_cont_btn);
                //event listeners to check when buttons are selected by the user
                btn_setup_cont.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // executes on main thread after user selects "CONTINUE" button
                        // redirects to game setup layout
                        setContentView(R.layout.game_time_tracker);
                    }
                });

            }
        });


        btn_view_stats.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // executes on main thread after user selects "view statistics" button
                // redirects to statistic mode selection layout
                setContentView(R.layout.stat_mode_selection);
            }
        });

        btn_manage_teams.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // executes on main thread after user selects "credits" button
                // redirects to team manager layout
                setContentView(R.layout.team_manager);
            }
        });

        btn_credits.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // executes on main thread after user selects "credits" button
                // redirects to credits layout
                setContentView(R.layout.credits);
            }
        });



    }
}
