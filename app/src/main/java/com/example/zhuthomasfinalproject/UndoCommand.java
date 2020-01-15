/**
 * Sydney Thomas
 * January 14, 2020
 * Class that is the commands to save on the undo stack
 */
package com.example.zhuthomasfinalproject;
public class UndoCommand {
    //indicators to tell what stat was just updated so it can be undone
    public static final int MAKE_3PT = 1; //made 3 point shot
    public static final int MAKE_2PT = 2; //made 2 point shot
    public static final int MAKE_FT = 3; //made free throw
    public static final int MISS_3PT = 4; //missed 3 point shot
    public static final int MISS_2PT = 5; //missed 2 point shot
    public static final int MISS_FT = 6; //missed free throw
    public static final int ASSIST = 7; //missed assist
    public static final int OFFR = 8; //offensive rebound
    public static final int DEFR = 9; //defensive rebound
    public static final int STL = 10; //steal
    public static final int TO = 11; //turnover
    public static final int BLK = 12; //block
    public static final int FOUL = 13; //foul

    private int cmd; //one of the commands above
    private Player player; //player the stat was entered for

    /**
     * default constructor that constructs an UndoCommand object
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
