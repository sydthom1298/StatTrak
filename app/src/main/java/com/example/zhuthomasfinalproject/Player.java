package com.example.zhuthomasfinalproject;


import java.time.LocalDateTime;
import java.util.ArrayList;

public class Player {
    private int jerseyNum;
    private String name;
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
        ArrayList<PlayerStats> stats = new ArrayList<PlayerStats>();

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
        currentStats = s;
    }
    public void Play(LocalDateTime start){
        //set start time (game clock)
        inGame = true;
    }
    public void Bench(LocalDateTime end){
        //set end time (game clock)
        inGame = false;
    }
    //used to fill the players in the playerlist
    public java.lang.String toString(){
        String str = "Name: " + name + "\nPlayer Number: " + jerseyNum;
        return str;
    }
}
