package com.example.zhuthomasfinalproject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class Season implements Serializable {
    private ArrayList<Game> games;
    private Team roster;
    private int startYear;
    private int endYear;
    public Season(){
        startYear = Calendar.getInstance().get(Calendar.YEAR);
        endYear = startYear + 1;
        games = new ArrayList<Game>();
    }
    public Season(Team t, int sYear, int eYear){
        this();
        roster = t;
        startYear = sYear;
        endYear = eYear;

    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public void setGames(ArrayList<Game> g) {
        games = g;
    }

    public Team getRoster() {
        return roster;
    }

    public void setRoster(Team r) {
        roster = r;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int sYear) {
        startYear = sYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int eYear) {
        endYear = eYear;
    }
    public Game getGame(long dateTime){
        for(Game g: this.games) {
            if(g.getGameDateTime() == dateTime){
                return g;
            }
        }
        return null;
    }
    public void addGame(Game game){
        games.add(game);
    }

    public String toString() {
        String str = "";
        str += startYear + " - " + endYear;
        //TODO add missing field
        return str;
    }
}
