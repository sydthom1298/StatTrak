package com.example.zhuthomasfinalproject;

import java.util.Date;
import java.sql.Time;

public class Game {
    private Team team; //team stats are being tracked for
    private String opponent;
    private long dateTime;
    private int teamFouls;
    private int points;

    public Game(){
        opponent = "";
        dateTime = System.currentTimeMillis();
    }
    public Game(Team t, String opp){
        team = t;
        opponent = opp;
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
