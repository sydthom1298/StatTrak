/**
 * Sydney Thomas
 * January 14, 2020
 * Class that encapsulates the season
 */
package com.example.zhuthomasfinalproject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class Season implements Serializable {
    private ArrayList<Game> games; //ArrayList of games
    private Team roster; //team that plays this season
    private int startYear; //start year of season
    private int endYear; //end year of season

    /**
     * default constructor that constructs a season class and initializes attributes
     */
    public Season(){
        //set start and end year
        startYear = Calendar.getInstance().get(Calendar.YEAR);
        endYear = startYear + 1;
        //create ArrayList for games
        games = new ArrayList<Game>();
    }

    /**
     * constructor that constructs a season class with parameters and initializes attributes
     * @param t - team to set
     * @param sYear - start year to set
     * @param eYear - end year to set
     */
    public Season(Team t, int sYear, int eYear){
        this();
        roster = t;
        startYear = sYear;
        endYear = eYear;

    }

    /**
     * accessor that gets the ArrayList of games
     * @return - ArrayList of games
     */
    public ArrayList<Game> getGames() {
        return games;
    }

    /**
     * mutator that sets the ArrayList of games
     * @param g - ArrayList of games to set
     */
    public void setGames(ArrayList<Game> g) {
        games = g;
    }

    /**
     * accessor that gets the player roster
     * @return - player roster
     */
    public Team getRoster() {
        return roster;
    }

    /**
     * mutator that sets the player roster
     * @param r - player roster to set
     */
    public void setRoster(Team r) {
        roster = r;
    }

    /**
     * accessor that gets the start year of the season
     * @return - start year of the season
     */
    public int getStartYear() {
        return startYear;
    }

    /**
     * mutator that sets the start year of the season
     * @param sYear - start year of the season to set
     */
    public void setStartYear(int sYear) {
        startYear = sYear;
    }

    /**
     * accessor that gets the end year of the season
     * @return - end year of season
     */
    public int getEndYear() {
        return endYear;
    }

    /**
     * mutator that sets the end year of the season
     * @param eYear - end year of season to set
     */
    public void setEndYear(int eYear) {
        endYear = eYear;
    }

    /**
     * method that searches for a game by the date and time game was created
     * @param dateTime - date and time game was created
     * @return - game found or null if not found
     */
    public Game getGame(long dateTime){
        for(Game g: this.games) {
            if(g.getGameDateTime() == dateTime){
                return g;
            }
        }
        return null;
    }

    /**
     * method that adds specified game to ArrayList of games for season
     * @param g - game to add
     */
    public void addGame(Game g){
        games.add(g);
    }

    /**
     * method that creates a string representation
     * @return - string representation
     */
    public String toString() {
        String str = "";
        str += startYear + " - " + endYear;
        return str;
    }
}
