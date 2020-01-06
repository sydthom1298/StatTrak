package com.example.zhuthomasfinalproject;


import java.time.LocalDateTime;
import java.util.ArrayList;

public class Player {
    private int jerseyNum;
    private String name;
    private ArrayList<PlayerStats> stats; //list of players stats for player
    private boolean inGame; //indicator to tell if player is playing in the game

    private PlayerStats currentStats;

    public PlayerStats getCurrentStats() {
        return currentStats;
    }

    public void setCurrentStats(PlayerStats cS) {
        currentStats = cS;
    }


    public Player(){
        jerseyNum = 0;
        name = "";
        inGame = false;
        ArrayList<PlayerStats> stats = new ArrayList<PlayerStats>();

    }
    public Player(String n, int num){
        this();
        name = n;
        jerseyNum = num;
    }
    public Player(String n, int num, ArrayList<PlayerStats> p){
        this();
        name = n;
        jerseyNum = num;
        stats = p;
    }

    public int getJerseyNum() {
        return jerseyNum;
    }

    public void setJerseyNum(int num) {
        jerseyNum = num;
    }

    public String getName() {
        return name;
    }

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
