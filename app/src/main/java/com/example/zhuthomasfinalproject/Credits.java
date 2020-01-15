/**
Jessica Zhu and Sydney Thomas
January 12 2020
Window that displays the credits for the application.
 */
package com.example.zhuthomasfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Credits extends AppCompatActivity {
    /**
     * called when Credits window starts up
     * @param savedInstanceState - used by the system
     */
    protected void onCreate(Bundle savedInstanceState){
        //set up screen from xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credits);
    }

    /**
     * Method that launches the instructions window when the Help button is clicked
     * @param v - the current window
     */
    public void launchInstructions(View v) {
        Intent i = new Intent(this, Instructions.class);
        Instructions.setIndex(0); // information about credits on first slide
        startActivity(i);
    }
}
