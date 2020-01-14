/**
 * Sydney Thomas
 * January 14, 2020
 * Class that is the commands to save on the undo stack
 */
package com.example.zhuthomasfinalproject;
public class UndoCommand {
    //indicators to tell what stat was just updated so it can be undone
    public static final int MAKE_3PT = 1;
    public static final int MAKE_2PT = 2;
    public static final int MAKE_FT = 3;
    public static final int MISS_3PT = 4;
    public static final int MISS_2PT = 5;
    public static final int MISS_FT = 6;
    public static final int ASSIST = 7;
    public static final int OFFR = 8;
    public static final int DEFR = 9;
    public static final int STL = 10;
    public static final int TO = 11;
    public static final int BLK = 12;
    public static final int FOUL = 13;

    private int cmd; //one of the commands above
    private Player player; //player the stat was entered for

    /**
     * default constructor that constructs an UndoCommand class
     */
    public UndoCommand(){

    }

    /**
     * constructor that contructs a UndoCommand class with parameters and initializes attributes
     * @param c - command to undo
     * @param p - player the command was for
     */
    public UndoCommand(int c, Player p) {
        cmd = c;
        player = p;
    }

    /**
     * accessor that gets the command to undo
     * @return - command to undo
     */
    public int getCmd() {
        return cmd;
    }

    /**
     * mutator that sets the command to undo
     * @param c - command to undo
     */
    public void setCmd(int c) {
        cmd = c;
    }

    /**
     * accessor that gets the player the command to undo was for
     * @return - player command was for
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * mutator that sets the player the command undo was for
     * @param p - player command was for
     */
    public void setPlayer(Player p) {
        player = p;
    }
}
