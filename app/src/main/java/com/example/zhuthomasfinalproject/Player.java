package com.example.zhuthomasfinalproject;


import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    private int jerseyNum;
    private String name;
    private String position;
    private boolean active;
    private ArrayList<PlayerStats> stats; //list of players stats for player
    private boolean inGame; //indicator to tell if player is playing in the game

    private PlayerStats currentStats;

    /**
     * default constructor that contructs a player class and initializes attributes
     */
    public Player(){
        jerseyNum = 0;
        name = "";
        inGame = false;
        stats = new ArrayList<PlayerStats>();

    }

    /**
     * constructor that creates and player class with parameters, initializes attributes and
     * calls default constructor
     * @param n - name of player
     * @param num - number of player
     */
    public Player(String n, int num){
        this();
        name = n;
        jerseyNum = num;
    }

    /**
     * constructor that creates and player class with parameters, initializes attributes and
     * calls default constructor
     * @param n - name of player
     * @param num - number of player
     * @param p - player stats for player
     */
    public Player(String n, int num, ArrayList<PlayerStats> p){
        this();
        name = n;
        jerseyNum = num;
        stats = p;
    }

    /**
     * accessor that gest the current stats of the player
     * @return - player stats
     */
    public PlayerStats getCurrentStats() {
        return currentStats;
    }

    /**
     * mutator that sets the current stats of the player
     * @param cS - current stats to set
     */
    public void setCurrentStats(PlayerStats cS) {
        currentStats = cS;
    }

    /**
     * accessor that gets the jersey number of the player
     * @return - jersey number
     */
    public int getJerseyNum() {
        return jerseyNum;
    }

    /**
     * mutator that sets the jersey number of the player
     * @param num - jersey number to set
     */
    public void setJerseyNum(int num) {
        jerseyNum = num;
    }

    /**
     * accessor that gets the name of the player
     * @return - name of player
     */
    public String getName() {
        return name;
    }

    /**
     * mutator that sets the name of the player
     * @param n - name of player to set
     */
    public void setName(String n) {
        name = n;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String p) {
        position = p;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean a) {
        active = a;
    }

    public ArrayList<PlayerStats> getStats() {
        return stats;
    }

    public void setStats(ArrayList<PlayerStats> s) {
        stats = s;
    }

    public PlayerStats getStat(long t) {
        for(PlayerStats s: stats){
            if(s.getGameDateTime() == t){
                return s;
            }
        }
        return null;
    }
    public void addPlayerStat(long t){
        PlayerStats s = new PlayerStats(t);
        if(stats == null) {
            stats = new ArrayList<PlayerStats>();
        }
        stats.add(s);
        currentStats = s;
    }
    public void Play(long start){
        //set start time (game clock)
        inGame = true;
    }
    public void Bench(long end){
        //set end time (game clock)
        inGame = false;
    }
    //used to fill the players in the playerlist
    public java.lang.String toString(){
        String str = "Name: " + name + "\nPlayer Number: " + jerseyNum;
        return str;
    }
}
