/**
 * Sydney Thomas
 * January 14, 2020
 * Class that holds all the data from the program including player stats, games, seasons
 */
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

    private static Player currentPlayer; //current active player
    private static Season currentSeason; //currently active season
    private static Game currentGame; //currently active game

    private static ArrayList<Team> teams = new ArrayList<Team>(); //ArrayList of Team
    private static ArrayList<Season> seasons = new ArrayList<Season>(); //ArrayList of Season

    private static String directory = ""; //name of directory used to store data
    /**
     * method that does all initialization for StatsManager because there is no
     * constructor in a final class
     */
    public static void initStatsManager() {
        File file = new File(getDirectory() + "/StatsManager.teams");
        /* check to see if data file exists - if it does it reads in the file to initialize StatsManager
        with previously saved data*/
        if(file.exists()) {
            fromFile();
        }
    }

    /**
     * accessor that gets the current player
     * @return - current player
     */
    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * mutator that sets the current player
     * @param cp - current player to set
     */
    public static void setCurrentPlayer(Player cp) {
        currentPlayer = cp;
    }

    /**
     * accessor that gets the current game
     * @return - current game
     */
    public static Game getCurrentGame() {
        return currentGame;
    }

    /**
     * mutator that sets the current game
     * @param g - current game to set
     */
    public static void setCurrentGame(Game g) {
        currentGame = g;
    }

    /**
     * accessor that gets the current season
     * @return - current season
     */
    public static Season getCurrentSeason() {
        return currentSeason;
    }

    /**
     * mutator that sets the current season
     * @param s - current season to set
     */
    public static void setCurrentSeason(Season s) {
        currentSeason = s;
    }

    /**
     * method that creates a new season
     * @return - new season
     */
    public static Season newSeason() {
        Season s = new Season();
        return s;
    }

    /**
     * method that creates a Player and adds the player to the roster
     * @param name - name of player
     * @param num - number of player
     */
    public static void addToRoster(String name, int num) {
        Player p = new Player(name, num);
        currentSeason.getRoster().addPlayer(p);
    }

    /**
     * method that creates a new game
     * @return - new game
     */
    public static Game newGame() {
        Game newGame = new Game();
        return newGame;
    }

    /**
     * method that finds a player from the current game with a given name and number
     * @param name - name of player
     * @param number - number of player
     */
    public static void selectPlayer(String name, int number) {
        currentPlayer = currentGame.getTeam().findPlayer(name, number);
    }

    /**
     * accessor that gets the ArrayList of teams
     * @return - ArrayList of teams
     */
    public static ArrayList<Team> getTeams() {
        return teams;
    }

    /**
     * mutator that sets the ArrayList of teams
     * @param t - ArrayList of teams to set
     */
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

    /**
     * method that removes team from list
     * @param t - team to remove
     */
    public static void removeTeam(Team t) {
        teams.remove(t);
    }

    /**
     * method that finds a team based on the name
     * @param name - name of team to find
     * @return - team found or null if not found
     */
    public static Team findTeam(String name) {
        for(Team t: teams){
            if(t.getName().equals(name)) {
                return t;
            }
        }
        return null;
    }

    /**
     * method that adds a season
     * @param s - season to add
     */
    public static void addSeason(Season s) {
        seasons.add(s);
    }

    /**
     * method that finds a season based on the startYear and endYear
     * @param startYear - year the season started
     * @param endYear - year the season ended
     * @return - season found or null if not found
     */
    public static Season findSeason( int startYear, int endYear){
        for(Season s: seasons) {
            if((s.getStartYear() == startYear) && (s.getEndYear() == endYear)){
                return s;
            }
        }
        return null;
    }

    /**
     * accessor that gets the full ArrayList of seasons
     * @return - ArrayList of seasons
     */
    public static ArrayList<Season> getSeasons() {
        return seasons;
    }

    /**
     * method that saves all the data for the program using serialization
     */
    public static void toFile() {
        //file objects needed to save data
        FileOutputStream fos;
        File tempFile;
        ObjectOutputStream o;

        try{
            //make directories to store the files
            tempFile = new File(getDirectory());
            tempFile.mkdirs();
            //serializing and outputting the team data
            fos = new FileOutputStream(getDirectory() + "/StatsManager.teams");
            System.out.println("writing to file " + getDirectory() +"/StatsManager.teams");
            o = new ObjectOutputStream(fos);
            o.writeObject(teams);
            o.close();
            //serializing and outputting the season data
            fos = new FileOutputStream(getDirectory() + "/StatsManager.seasons");
            o = new ObjectOutputStream(fos);
            System.out.println("writing to file " + getDirectory() +"/StatsManager.seasons");
            o.writeObject(seasons);
            o.close();

        }catch(Exception x){
            System.exit(-1);
        }

    }

    /**
     * accessor that gets the directory
     * @return - directory
     */
    public static String getDirectory() {
        return directory;
    }

    /**
     * mutator that sets the directory
     * @param fileDir - directory to set
     */
    public static void setDirectory(String fileDir) {
        directory = fileDir;
    }

    /**
     * method that reads and deserializes all the information for StatsManager from the file
     */
    public static void fromFile() {
        FileInputStream fis;
        ObjectInputStream i;
        try{
            //reads and deserializes the data for teams
            fis = new FileInputStream(getDirectory() + "/StatsManager.teams");
            i = new ObjectInputStream(fis);
            teams = (ArrayList<Team>) i.readObject();
            i.close();
            //reads and deserializes the data for seasons
            fis = new FileInputStream(getDirectory() + "/StatsManager.seasons");
            i = new ObjectInputStream(fis);
            seasons = (ArrayList<Season>) i.readObject();
            i.close();

        }catch(Exception x){
            System.exit( -1 );
        }

    }

    /**
     * method that is a conversion function that converts from time stamp in milliseconds to a
     * date and time String
     * @param dateTime - time stamp in milliseconds
     * @return - formatted date and time String
     */
    public static String getDateTimeFromTimestamp(long dateTime) {
        Calendar cal;
        cal = Calendar.getInstance();
        cal.setTimeInMillis(dateTime);
        String dt = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(dateTime);
        System.out.println("Date Time: " + dt);
        return dt;
    }

    /**
     * method that is a conversion function that converts from time stamp in milliseconds to a
     * time String
     * @param dateTime - time stamp in milliseconds
     * @return - formatted time String
     */
    public static String getTimeFromTimestamp(long dateTime) {
        Calendar cal;
        cal = Calendar.getInstance();
        cal.setTimeInMillis(dateTime);
        String time = new SimpleDateFormat("HH.mm.ss").format(dateTime);
        System.out.println("Time: " + time);
        return time;
    }

    /**
     * method that is a conversion function that convert from time stamp in milliseconds to a day
     * of the month
     * @param dateTime - time stamp in milliseconds
     * @return - formatted date of month String
     */
    public static String getDayFromTimestamp(long dateTime) {
        int day;
        Calendar cal;
        cal = Calendar.getInstance();
        cal.setTimeInMillis(dateTime);
        day = cal.get(Calendar.DAY_OF_MONTH);
        System.out.println("Day: " + day);
        return Integer.toString(day);
    }

    /**
     * method that is a conversion function that converts from time stamp in milliseconds to a
     * month
     * @param dateTime - time stamp in milliseconds
     * @return - formatted month String
     */
    public static String getMonthFromTimestamp(long dateTime) {
        int month;
        Calendar cal;
        cal = Calendar.getInstance();
        cal.setTimeInMillis(dateTime);
        month = cal.get(Calendar.MONTH);
        System.out.println("Month: " + month);
        return Integer.toString(month);
    }

    /**
     * method that is a conversion function that converts from time stamp in milliseconds to a
     * year
     * @param dateTime - time stamp in milliseconds
     * @return - formatted year String
     */
    public static String getYearFromTimestamp(long dateTime) {
        int year;
        Calendar cal;
        cal = Calendar.getInstance();
        cal.setTimeInMillis(dateTime);
        year = cal.get(Calendar.YEAR);
        System.out.println("Year: " + year);
        return Integer.toString(year);
    }

}
