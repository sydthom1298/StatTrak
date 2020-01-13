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
    private long dateTime;
    private int teamFouls; //number of team fouls
    private int points; //points team scores
    private Player Playing[]; //array that stores the 5 players currently on the floor

    /**
     * Default constructor that constructs a Game class and initializes attributes
     */
    public Game(){
        opponent = "";
        dateTime = System.currentTimeMillis();
        Playing = new Player[5];
    }

    /**
     * Constructor that constructs a Game class with parameters and initializes attributes
     * @param t - team stats are being tracked for
     * @param opp - opponent team is playing
     */
    public Game(Team t, String opp){
        this();
        team = t;
        opponent = opp;
    }


    public Player[] getPlaying() {
        return Playing;
    }

    public void setPlaying(Player[] p) {
        Playing = p;
    }

    /**
     * accessor that gets the team tracking stats for
     * @return - team
     */
    public Team getTeam() {
        return team;
    }

    /**
     * mutator that sets the team that the stats are being tracked for
     * @param t - team to set
     */
    public void setTeam(Team t) {
        team = t;
    }

    /**
     * accessor that gets the opponent team is playing
     * @return - opponent
     */
    public String getOpponent() {
        return opponent;
    }

    /**
     * mutator that sets the opponent team is playing
     * @param o - opponent to set
     */
    public void setOpponent(String o) {
        opponent = o;
    }
    public long getGameDateTime() {
        return dateTime;
    }

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

    public void addPlayingTime(long time) {
        for(int i = 0; i < this.Playing.length; i++) {
            Playing[i].getCurrentStats().addPlayingTime(time);
        }
    }

    /**
     * Method that creates String representation of class
     * @return - string representation
     */
    public String toString(){
        String str = "";
        Date d = new  Date(this.dateTime);
        Time t = new Time(this.dateTime);
        str += "com.example.zhuthomasfinalproject.Team Name: " + team + "\nOpponent: " + opponent + "\nDate/Time " + d.toString() + " " + t.toString();
        //TODO add missing fields
        return str;
    }

    public void exportToCSV() {
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        String date = "Game " + StatsManager.getMonthFromTimestamp(dateTime) +
                StatsManager.getDayFromTimestamp(dateTime) +
                StatsManager.getYearFromTimestamp(dateTime);
        File file = new File(dir, "Game " + date + " vs " + opponent);
        System.out.println( "Export Game to " + dir.getAbsolutePath() + "/" +
                "Game " + date + " vs " + opponent);
        try (FileWriter fileWriter = new FileWriter(file)) {
            // fileWriter.append("Writing to file!");
            fileWriter.write( team.getName() + " vs " + opponent + "," +
                    StatsManager.getDateTimeFromTimestamp(this.dateTime));

            fileWriter.write( "Points:," + points + ",Team Fouls:,"+ teamFouls);
            fileWriter.write("Name,Number,Position,Assists, Blocks, DefReb, Fouls, FtMakes," +
                    "FtMisses,Minutes,OffReb,PlayTime,Points,Steals,3PtMake, 3PtMiss, 2PtMake, " +
                    "2PtMiss,TtlReb,Turnover");
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
                        s.getTurnovers() );

            }

        } catch (IOException e) {
            //Handle exception
        }
    }
}
