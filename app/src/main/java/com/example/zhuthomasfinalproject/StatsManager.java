package com.example.zhuthomasfinalproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public final class StatsManager implements Serializable {

    private static Player currentPlayer;
    private static Season currentSeason;
    private static Game currentGame;
    private static boolean test = true;

    private static ArrayList<Team> teams = new ArrayList<Team>(); //arrayList of Teams
    private static ArrayList<Season> seasons = new ArrayList<Season>();


    public static void initStatsManager() {
        File file = new File(getDirectory() + "/StatsManager.teams");
        if(file.exists()) {
            fromFile();
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

    public static void selectPlayer(String name, int number) {
        currentPlayer = currentGame.getTeam().findPlayer(name, number);
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

    public static void addSeason(Season s) {
        seasons.add(s);
    }

    public static Season findSeason( int startYear, int endYear){
        for(Season s: seasons) {
            if( ( s.getStartYear() == startYear) &&
                    ( s.getEndYear() == endYear )){
                return s;
            }
        }
        return null;
    }

    public static void toFile() {
        FileOutputStream fos;

        System.out.println("Output");

        try {
            File tempfile = new File(getDirectory());
            tempfile.mkdirs();

            fos = new FileOutputStream(getDirectory() + "/StatsManager.teams");
            System.out.println("writing to file " + getDirectory() +"/StatsManager.teams");
            ObjectOutputStream o = new ObjectOutputStream(fos);
            o.writeObject(teams);
            o.close();

            fos = new FileOutputStream(getDirectory() + "/StatsManager.seasons");
            o = new ObjectOutputStream(fos);
            System.out.println("writing to file " + getDirectory() +"/StatsManager.seasons");
            o.writeObject(seasons);
            o.close();

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

    public static void fromFile() {
        FileInputStream fis;
        try {
            fis = new FileInputStream(getDirectory() + "/StatsManager.teams");
            ObjectInputStream i = new ObjectInputStream(fis);
            teams = (ArrayList<Team>) i.readObject();
            System.out.println("Input + ");
            i.close();

            fis = new FileInputStream(getDirectory() + "/StatsManager.seasons");
            i = new ObjectInputStream(fis);
            seasons = (ArrayList<Season>) i.readObject();
            System.out.println("Input + ");
            i.close();

        } catch( Exception x) {
            System.exit( -1 );
        }

    }
    public static String getDateTimeFromTimestamp(long dateTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(dateTime);
        String dt = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(dateTime);


        System.out.println("Date Time: " + dt);
        return dt;
    }
    public static String getTimeFromTimestamp(long dateTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(dateTime);
        String time = new SimpleDateFormat("HH.mm.ss").format(dateTime);

        System.out.println("Time: " + time);
        return time;
    }

    public static String getDayFromTimestamp(long dateTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(dateTime);

        int day = cal.get(Calendar.DAY_OF_MONTH);

        System.out.println("Day: " + day);
        return Integer.toString(day);
    }

    public static String getMonthFromTimestamp(long dateTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(dateTime);

        int month = cal.get(Calendar.MONTH);

        System.out.println("Month: " + month);
        return Integer.toString(month);
    }
    public static String getYearFromTimestamp(long dateTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(dateTime);

        int year = cal.get(Calendar.YEAR);

        System.out.println("Year: " + year);
        return Integer.toString(year);
    }

}
