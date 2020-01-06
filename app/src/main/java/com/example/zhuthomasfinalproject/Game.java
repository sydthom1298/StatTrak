package com.example.zhuthomasfinalproject;

import java.util.Date;
import java.sql.Time;

public class Game {
    private Team team; //team stats are being tracked for
    private String opponent;
    private long dateTime;
    private int teamFouls;
    private int points;
    private Player Playing[]; //array that stores the 5 players currently on the floor

    public Game(){
        opponent = "";
        dateTime = System.currentTimeMillis();
        Playing = new Player[5];
    }
    public Game(Team t, String opp){
        this();
        team = t;
        opponent = opp;
    }

    public Player[] getPlaying() {
        return Playing;
    }

    public void setPlaying(Player[] playing) {
        Playing = playing;
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

    public void setPoints(int points) {
        this.points = points;
    }

    public void addPoints(int ptsToAdd) {
        points += ptsToAdd;
    }

    public void subtractPoints(int ptsToSub) {
        this.points -= ptsToSub;
    }

    public void addPlayingTime(long time) {
        for(int i=0; i<this.Playing.length; i++) {
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
