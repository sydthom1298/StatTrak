package com.example.zhuthomasfinalproject;

import java.util.ArrayList;
public class Team {
    private String name;
    private ArrayList<Player> players;


    /**
     * default constructor that constructs a team class and initializes attributes
     */
    public Team(){
        name = "";
        ArrayList<Player> players = new ArrayList<Player>();
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
        name = n;
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

    public void addPlayer(Player p) {
        players.add(p);
    }

    public Player findPlayer(String name, int num) {
        for(Player p : this.players){
            if(p.getName().equals(name) && (num == p.getJerseyNum())){
                return p;
            }
        }
        return null;
    }

    /**
     * creates String representaiton of class
     * @return - String representation
     */
    public String toString(){
        String str = "";
        str += "Name: " + name+ "\nPlayers: " + players;
        //TODO add missing fields
        return str;
    }
}
