package com.example.zhuthomasfinalproject;

import android.util.Printer;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.sql.Time;
import java.util.List;

public class Game {
    private Team team; //team stats are being tracked for
    private String opponent; //opponent team is playing
    private long dateTime;
    private int teamFouls; //number of team fouls
    private int points; //points team scores
    private Player Playing[]; //array that stores the 5 players currently on the floor

    /**
     * Default constructor that constructs a Game class and initializes attributes
     */
    public Game(){
        opponent = "";
        dateTime = System.currentTimeMillis();
        Playing = new Player[5];
    }

    /**
     * Constructor that constructs a Game class with parameters and initializes attributes
     * @param t - team stats are being tracked for
     * @param opp - opponent team is playing
     */
    public Game(Team t, String opp){
        this();
        team = t;
        opponent = opp;
    }


    public Player[] getPlaying() {
        return Playing;
    }

    public void setPlaying(Player[] p) {
        Playing = p;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team t) {
        team = t;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String o) {
        opponent = o;
    }
    public long getGameDateTime() {
        return dateTime;
    }

    public void setGameDateTime(long dT) {
        dateTime = dT;
    }

    public int getTeamFouls() {
        return teamFouls;
    }
    public void addTeamFouls(){
        teamFouls++;
    }
    public void subtractTeamFouls(){
        teamFouls--;
    }
    public void setTeamFouls(int tF) {
        teamFouls = tF;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int p) {
        points = p;
    }

    public void addPoints(int ptsToAdd) {
        points += ptsToAdd;
    }

    public void subtractPoints(int ptsToSub) {
        points -= ptsToSub;
    }

    public void addPlayingTime(long time) {
        for(int i = 0; i < this.Playing.length; i++) {
            Playing[i].getCurrentStats().addPlayingTime(time);
        }
    }


    /**
     * Method that creates String representation of class
     * @return - string representation
     */
    public String toString(){
        String str = "";
        Date d = new  Date(this.dateTime);
        Time t = new Time(this.dateTime);
        str += "com.example.zhuthomasfinalproject.Team Name: " + team + "\nOpponent: " + opponent + "\nDate/Time " + d.toString() + " " + t.toString();
        //TODO add missing fields
        return str;
    }
}
