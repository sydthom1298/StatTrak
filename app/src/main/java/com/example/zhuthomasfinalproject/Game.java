/**
 * Sydney Thomas
 * January 15, 2020
 * Class that encapsulates Game
 */
package com.example.zhuthomasfinalproject;
import android.os.Environment;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class Game implements Serializable {
    private Team team; //team stats are being tracked for
    private String opponent; //opponent team is playing
    private long dateTime; //timestamp of when the game was
    private int teamFouls; //number of team fouls
    private int points; //points team scores
    private Player Playing[]; //array that stores the 5 players currently on the floor

    /**
     * Default constructor that constructs a Game object and initializes attributes
     */
    public Game(){
        opponent = "";
        dateTime = System.currentTimeMillis();
        Playing = new Player[5];
    }

    /**
     * Constructor that constructs a Game object with parameters and initializes attributes
     * @param t - team stats are being tracked for
     * @param opp - opponent team is playing
     */
    public Game(Team t, String opp){
        this();
        team = t;
        opponent = opp;
    }

    /**
     * accessor that gets the array of players playing in game
     * @return - array of players playing in game
     */
    public Player[] getPlaying() {
        return Playing;
    }

    /**
     * mutator that sets array of players playing in game
     * @param p - array of players playing in game to set
     */
    public void setPlaying(Player[] p) {
        Playing = p;
    }

    /**
     * accessor that gets the team object for the team stats are being tracked for
     * @return - team object
     */
    public Team getTeam() {
        return team;
    }

    /**
     * mutator that sets the team object that the stats are being tracked for
     * @param t - team object to set
     */
    public void setTeam(Team t) {
        team = t;
    }

    /**
     * accessor that gets the opponent that the team is playing
     * @return - opponent name
     */
    public String getOpponent() {
        return opponent;
    }

    /**
     * mutator that sets the opponent name that the team is playing
     * @param o - opponent name to set
     */
    public void setOpponent(String o) {
        opponent = o;
    }

    /**
     * accessor that gets the timestamp for the game
     * @return - timestamp for game
     */
    public long getGameDateTime() {
        return dateTime;
    }

    /**
     * mutator that sets the timestamp for the game
     * @param dT - timestamp to set
     */
    public void setGameDateTime(long dT) {
        dateTime = dT;
    }

    /**
     * mutator that gets the number of team fouls for the team
     * @return - number of team fouls
     */
    public int getTeamFouls() {
        return teamFouls;
    }

    /**
     * method that adds to team fouls
     */
    public void addTeamFouls(){
        teamFouls++;
    }

    /**
     * method that subtracts from team fouls
     */
    public void subtractTeamFouls(){
        teamFouls--;
    }

    /**
     * mutator that sets team fouls
     * @param tF - team fouls to set
     */
    public void setTeamFouls(int tF) {
        teamFouls = tF;
    }

    /**
     * accessor that gets the number of points scored by team in game
     * @return - number of points
     */
    public int getPoints() {
        return points;
    }

    /**
     * mutator that sets the number of points scored by team in game
     * @param p - points to set
     */
    public void setPoints(int p) {
        points = p;
    }

    /**
     * method that adds points
     * @param ptsToAdd - points to add
     */
    public void addPoints(int ptsToAdd) {
        points += ptsToAdd;
    }

    /**
     * method that subtracts points
     * @param ptsToSub - points to subtract
     */
    public void subtractPoints(int ptsToSub) {
        points -= ptsToSub;
    }

    /**
     * method that increments the amount of time that all the players on the court have played
     * @param time - time in milliseconds to add
     */
    public void addPlayingTime(long time) {
        for(int i = 0; i < this.Playing.length; i++) {
            Playing[i].getCurrentStats().addPlayingTime(time);
        }
    }


    /**
     * method that exports game and player stats for game to a CSV file
     */
    public void exportToCSV() {
        File dir;
        String date;
        File file;
        String fileName;

        //get downloads directory to store exported file
        dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        //calculate date to put in CSV file name
        date = StatsManager.getMonthFromTimestamp(dateTime) +
                StatsManager.getDayFromTimestamp(dateTime) +
                StatsManager.getYearFromTimestamp(dateTime);
        //create a file object named according to the date of the game
        fileName = dir + "Game " + date + " vs " + opponent;
        file = new File(dir, "Game " + date + " vs " + opponent);
        System.out.println( "Export Game to " + dir.getAbsolutePath() + "/" +
                "Game " + date + " vs " + opponent);
        //file writer that goes through and writes game info followed by headings and player stats for each player
        try(FileWriter fileWriter = new FileWriter(file)) {
            //writes game stats
            fileWriter.write( team.getName() + " vs " + opponent + "," +
                    StatsManager.getDateTimeFromTimestamp(this.dateTime) +"\n");
            //writes headings for player stats
            fileWriter.write( "Points:," + points + ",Team Fouls:,"+ teamFouls + "\n");
            fileWriter.write("Name,Number,Position,Assists, Blocks, DefReb, Fouls, FtMakes," +
                    "FtMisses,Minutes,OffReb,PlayTime,Points,Steals,3PtMake, 3PtMiss, 2PtMake, " +
                    "2PtMiss,TtlReb,Turnover\n");
            //gets Player object for each player on the team and outputs PlayerStats for this game
            for(Player p: team.getPlayers()) {
                PlayerStats s = p.getStat(this.dateTime);
                if(s==null) {
                    continue;
                }
                fileWriter.write(p.getName() + "," + p.getJerseyNum() + "," + p.getPosition() + "," +
                        s.getAssists() + "," + s.getBlocks() + "," + s.getDefRebs() + "," +
                        s.getFouls() + "," + s.getFtMakes() + "," + s.getFtMisses() + "," +
                        s.getMinPlayed() + "," + s.getOffRebs() + "," +
                        s.getPlayingTime() + "," + s.getPoints() + "," + s.getSteals() + "," +
                        s.getThreePtMakes() + "," + s.getThreePtMisses() + "," +
                        s.getTwoPtMakes() + "," + s.getTwoPtMisses() + "," + s.getTtlRebs() + "," +
                        s.getTurnovers() + "\n" );

            }
        }catch (IOException e){
            //ignore exception but output error information
            System.err.println("Error " + e.getMessage()+" writing export file: " + fileName);
        }
    }


    /**
     * method that creates String representation of class
     * @return - string representation
     */
    public String toString(){
        String str = "";
        Date d = new  Date(dateTime);
        Time t = new Time(dateTime);
        str += "com.example.zhuthomasfinalproject.Team Name: " + team + "\nOpponent: " + opponent +
                "\nDate/Time " + d.toString() + " " + t.toString();
        return str;
    }

}
