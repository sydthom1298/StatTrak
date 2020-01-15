/**
Jessica Zhu and Sydney Thomas
January 12 2020
Window of the application that allows the user to view the instructions in operating our application.
User can flip through sections of instructions that resemble a user manual, minus the pictures
in order to gain a better understanding of how the program runs.
 */
package com.example.zhuthomasfinalproject;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

public class Instructions extends AppCompatActivity {
    // object which represents the TextView widget that will display the instructions
    private TextView instructions;
    // array that stores each section of instructions
    private String[] sections;
    // stores the index of the section that the user is currently viewing
    private int index;

    /**
     * Called when Instruction sets up
     * @param savedInstanceState - used by the system
     */
    protected void onCreate(final Bundle savedInstanceState) {
        // sets up screen based on XML
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructions);

        // initialize index to 0 (first section of instructions)
        index = 0;

        // instantiate object to store the text box that displays the instructions
        instructions = findViewById(R.id.instruct_text);
        // allows the text box to scroll for longer sections of text
        instructions.setMovementMethod(new ScrollingMovementMethod());

        // load the array of sections with text from the instruction file
        loadArray();

        // default to initially display first section of instructions upon creation of Instructions window
        instructions.setText(sections[index]);

        // objects to store the left/right buttons
        ImageButton right;
        ImageButton left;

        // instantiate the left button
        left = findViewById(R.id.btn_left);
        // listener to see when user clicks this button
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check if the index is currently at the leftmost index (0)
                // cannot go to a lower section (out of bounds), so instead go to the last section
                // creates wraparound effect
                if (index == 0) { // index is 0
                    // set to last section
                    index = sections.length - 1;
                } else { // not 0
                    // go down one section
                    index --;
                }
                // update display of instructions to show the current section
                instructions.setText(sections[index]);
            }
        });
        // instantiate the right button
        right = findViewById(R.id.btn_right);
        // listener to see when user clicks this button
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check if the index is at the rightmost index (last index)
                // cannot go higher (out of bounds), so instead go to first section
                // wraparound effect
                if (index == sections.length - 1) { // on last section
                    // set to first section
                    index = 0;
                } else { // not the last section
                    // go to next section
                    index ++;
                }
                // update display of instructions
                instructions.setText(sections[index]);
            }
        });

    }

    /**
     * loads String array of instruction sections with information from file
     */
    public void loadArray() {
        // initialize string to hold the information from the file
        String text = "";
        try {
            // initializes input stream to read file
            InputStream is = getAssets().open("instructions.txt");
            int size = is.available(); // determines how many bytes are available to read on the data file
            // instantiate byte type array of size of availability to store data
            byte[] buffer = new byte[size];
            // read the file, storing information in byte array
            is.read(buffer);
            // close the input stream and stop reading
            is.close();
            // convert the byte array into a string
            text = new String(buffer);
        } catch (IOException ex) { // catches file reading errors to prevent crashing
            // helps trace caught exception
            ex.printStackTrace();
        }
        // store the String information as an array of String segments
        // split the file based on the pattern "BREAK" which divides up sections in the file
        sections = text.split("BREAK");
    }

}
