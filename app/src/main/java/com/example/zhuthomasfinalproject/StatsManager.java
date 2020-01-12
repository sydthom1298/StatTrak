package com.example.zhuthomasfinalproject;

import java.util.ArrayList;

public final class StatsManager {
    private static Player currentPlayer;
    private static Season currentSeason;
    private static Game currentGame;
    private static boolean test = true;

    private static Player Playing[];
    private static ArrayList<Team> teams = new ArrayList<Team>(); //arrayList of Teams



    public StatsManager() {

    }


    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static void setCurrentPlayer(Player cp) {
        //System.out.println("OLD Current: " + currentPlayer.getCurrentStats().toString());
        currentPlayer = cp;
        //System.out.println("NEW Current: " + currentPlayer.getCurrentStats().toString());
    }

    public static Game getCurrentGame() {
        return currentGame;
    }

    public static void setCurrentGame(Game g) {
        currentGame = g;
    }

    public static Season getCurrentSeason() {
        return currentSeason;
    }

    public static void setCurrentSeason(Season s) {
        currentSeason = s;
    }

    public static Season newSeason() {
        Season s = new Season();
        return s;
    }

    public static void addToRoster(String name, int num) {
        Player p = new Player(name, num);
        currentSeason.getRoster().addPlayer(p);
    }

    public static Game newGame() {
        Game newGame = new Game();
        return newGame;
    }

    public static void putPlayerInGame(int slot, Player p) {
        Playing[slot].Bench(null);  //TODO pass in time
        p.Play(null); //TODO pass in time
        Playing[slot] = p;
    }

    public static void removePlayerFromGame(int slot) {
        Playing[slot].Bench(null);  // TODO pass in time
        Playing[slot] = null;
    }

    public static void selectPlayer(String name, int number) {
        currentPlayer = currentGame.getTeam().findPlayer(name, number);
    }

    public static Player[] getPlaying() {
        return Playing;
    }

    public static void setPlaying(Player[] p) {
        Playing = p;
    }

    public static ArrayList<Team> getTeams() {
        return teams;
    }

    public static void setTeams(ArrayList<Team> t) {
        teams = t;
    }

    /**
     * method that adds team entered by user to array list of teams
     * @param t - team to add
     */
    public static void addTeam(Team t){
        teams.add(t);
    }

    public static Team findTeam( String name) {
        for(Team t: teams) {
            if( t.getName().equals(name)) {
                return t;
            }
        }
        return null;
    }


}
