/**
 * Sydney Thomas
 * January 14, 2020
 *
 */
package com.example.zhuthomasfinalproject;

import java.io.Serializable;
import java.util.ArrayList;
public class Team implements Serializable {
    private String name;
    private ArrayList<Player> players;
    private ArrayList<Season> seasons;

    /**
     * default constructor that constructs a team object and initializes attributes
     */
    public Team(){
        name = "";
        players = new ArrayList<Player>();
        seasons = new ArrayList<Season>();
    }


    /**
     * constructor that constructs a team object with parameters and initializes attributes
     * @param n - name of team
     */
    public Team(String n){
        this();
        name = n;
    }

    /**
     * constructor that constructs a team object with parameters and initializes attributes
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


    public int getNumPlayers() {
        return players.size();
    }


    /**
     * accessor to return players
     * @return player ArrayList
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> p) {
        players = p;
    }
    /*
    TODO LATER
    public com.example.zhuthomasfinalproject.Team clone(){

    }*/



    public Player findPlayer(String name, int num) {
        for(Player p : this.players){
            if(p.getName().equals(name) && (num == p.getJerseyNum())){
                return p;
            }
        }
        return null;
    }

    public void addPlayer(Player p) {
        players.add(p);
    }

    public void removePlayer(Player p){
        players.remove(p);
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public Season getSeason(int startYear) {
        for (int i = 0; i < seasons.size(); i++) {
            if (seasons.get(i).getStartYear() == startYear) {
                return seasons.get(i);
            }
        }
        return null;
    }

    public void setSeasons(ArrayList<Season> s) {
        seasons = s;
    }

    public void addSeason(Season s) {
        seasons.add(s);
    }

    /**
     * creates String representation of class
     * @return - String representation
     */
    public String toString(){

        String str = "";
        //str += "Name: " + name+ "\nPlayers: " + players;
        str += name;
        //TODO add missing fields
        return str;
    }
}
