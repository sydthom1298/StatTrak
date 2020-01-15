/**
 * Sydney Thomas
 * January 15, 2020
 * Class to encapsulate a player
 */
package com.example.zhuthomasfinalproject;
import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    private int jerseyNum; //players number
    private String name; //name of player
    private String position; //position of player
    private ArrayList<PlayerStats> stats; //list of players stats for player
    private boolean inGame; //indicator to tell if player is playing in the game
    private PlayerStats currentStats; //currently selected stats

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
     * accessor that gets the current stats of the player
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

    /**
     * accessor that gets the position of the player
     * @return - position of player
     */
    public String getPosition() {
        return position;
    }

    /**
     * mutator that sets the position
     * @param p - position of player to set
     */
    public void setPosition(String p) {
        position = p;
    }

    /**
     * accessor that gets all the player stats
     * @return - player stats
     */
    public ArrayList<PlayerStats> getStats() {
        return stats;
    }

    /**
     * mutator that sets all the player stats
     * @param s - player stats to set
     */
    public void setStats(ArrayList<PlayerStats> s) {
        stats = s;
    }

    /**
     * method that searches for player stats on a given date and time
     * @param t - date and time in milliseconds
     * @return - players stats if true or null if false
     */
    public PlayerStats getStat(long t) {
        for(PlayerStats s: stats){
            if(s.getGameDateTime() == t){
                return s;
            }
        }
        return null;
    }

    /**
     * method that creates a new player stat and adds it to the list
     * @param t - specified date and time that the player stat is for
     */
    public void addPlayerStat(long t){
        PlayerStats s = new PlayerStats(t);
        if(stats == null) {
            stats = new ArrayList<PlayerStats>();
        }
        stats.add(s);
        currentStats = s;
    }
    /**
     * String representation of class
     * used to fill the players in the playerList
     * @return - String representation
     */
    public String toString(){
        String str = "Name: " + name + "\nPlayer Number: " + jerseyNum;
        return str;
    }
}
