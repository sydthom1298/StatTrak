package com.example.zhuthomasfinalproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public final class StatsManager implements Serializable {
    private static Player currentPlayer;
    private static Season currentSeason;
    private static Game currentGame;
    private static boolean test = true;

    private static Player Playing[];
    private static ArrayList<Team> teams = new ArrayList<Team>(); //arrayList of Teams



    public StatsManager() {

    }

    public static void initStatsManager() {
        File file = new File(getDirectory() + "/StatsFile");
        if(file.exists()) {
            fromFile("StatsFile");
        }
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
        Playing[slot].Bench(0);  //TODO pass in time
        p.Play(0); //TODO pass in time
        Playing[slot] = p;
    }

    public static void removePlayerFromGame(int slot) {
        Playing[slot].Bench(0);  // TODO pass in time
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


    public static void toFile( String fn) {
        FileOutputStream fos;


        System.out.println("Output");


        try {
            File tempfile = new File(getDirectory());
            tempfile.mkdirs();

            //String fn = getApplicationContext().getFilesDir()+"/person";
            fos = new FileOutputStream(getDirectory() + "/StatsFile");
            System.out.println("writing to file " + getDirectory() +"/StatsFile");
            ObjectOutputStream o = new ObjectOutputStream(fos);

            o.writeObject(teams);





        } catch (Exception x){
            System.exit(-1);
        }

    }
    private static String Directory = "";

    public static String getDirectory() {
        return Directory;
    }

    public static void setDirectory(String fileDir) {
        Directory = fileDir;
    }

    public static void fromFile(String fn) {
        FileInputStream fis;
        try {


            fis = new FileInputStream(getDirectory() + "/StatsFile");
            ObjectInputStream i = new ObjectInputStream(fis);
            teams = (ArrayList<Team>) i.readObject();
            System.out.println("Input + ");
            // System.out.println(r.toString());
        } catch( Exception x) {
            System.exit( -1 );
        }

    }


}
