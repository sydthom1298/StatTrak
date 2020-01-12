/*
Jessica Zhu and Sydney Thomas
January 12 2020
Window that displays the options in the main menu, allowing the user to access each via buttons.
Each button opens a new window (each ha its own activity class), this window is mainly used for navigation.
 */

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

    public void launchInstructions(View v) {
        Intent i = new Intent(this, Instructions.class);
        startActivity(i);
    }
    public void launchCredits(View v) {
        Intent i = new Intent(this, Credits.class);
        startActivity(i);
    }


}
