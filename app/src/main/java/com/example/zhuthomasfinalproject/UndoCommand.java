package com.example.zhuthomasfinalproject;

public class UndoCommand {


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

    private int cmd;
    private Player player;
    
    public UndoCommand(int c, Player p) {
        cmd = c;
        player = p;
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
