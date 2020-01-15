/**
 * Sydney Thomas
 * January 14, 2020
 * Class that encapsulates a team
 */
package com.example.zhuthomasfinalproject;
import java.io.Serializable;
import java.util.ArrayList;
public class Team implements Serializable {
    private String name; //name of team
    private ArrayList<Player> players; //ArrayList of players
    private ArrayList<Season> seasons; //ArrayList of seasons

    /**
     * default constructor that constructs a team class and initializes attributes
     */
    public Team(){
        name = "";
        players = new ArrayList<Player>();
        seasons = new ArrayList<Season>();
    }


    /**
     * constructor that constructs a team class with parameters and initializes attributes
     * @param n - name of team
     */
    public Team(String n){
        this();
        name = n;
    }

    /**
     * constructor that constructs a team class with parameters and initializes attributes
     * @param n - name of team
     * @param p - array list that stores all the players on the team
     */
    public Team(String n, ArrayList<Player> p){
        this(n);
        players = p;
    }

    /**
     * accessor that gets the name of the team
     * @return - name of team
     */
    public String getName() {
        return name;
    }

    /**
     * mutator that sets the name of the current team
     * @param n - name to set current team to
     */
    public void setName(String n) {
        name = n;
    }

    /**
     * accessor that gets the number of players on team
     * @return - number of players
     */
    public int getNumPlayers() {
        return players.size();
    }


    /**
     * accessor that gets players list
     * @return - players ArrayList
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * mutator that sets players list
     * @param p - players ArrayList
     */
    public void setPlayers(ArrayList<Player> p) {
        players = p;
    }

    /**
     * method that finds the player with the name and number
     * @param name - name searching for
     * @param num - number searching for
     * @return - found player or null if not found
     */
    public Player findPlayer(String name, int num) {
        //loop through players ArrayList
        for(Player p : players){
            //finds player
            if(p.getName().equals(name) && (num == p.getJerseyNum())){
                return p;
            }
        }
        //player wasn't found
        return null;
    }

    /**
     * method that adds a player to a team
     * @param p - player to add
     */
    public void addPlayer(Player p) {
        players.add(p);
    }

    /**
     * method that removes a player from a team
     * @param p - player to remove
     */
    public void removePlayer(Player p){
        players.remove(p);
    }

    /**
     * accessor that gets the season ArrayList
     * @return - season ArrayList
     */
    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    /**
     * accessor that gets the season for the specified start year
     * @param startYear - start year
     * @return - season found, null if not found
     */
    public Season getSeason(int startYear) {
        for(int i = 0; i < seasons.size(); i++) {
            if(seasons.get(i).getStartYear() == startYear) {
                return seasons.get(i);
            }
        }
        return null;
    }

    /**
     * mutator that sets season ArrayList
     * @param s - season ArrayList
     */
    public void setSeasons(ArrayList<Season> s) {
        seasons = s;
    }

    /**
     * method that adds a season
     * @param s - season to add
     */
    public void addSeason(Season s) {
        seasons.add(s);
    }

    /**
     * creates String representation of class
     * @return - String representation
     */
    public String toString(){
        String str = "";
        str += name;
        return str;
    }
}
