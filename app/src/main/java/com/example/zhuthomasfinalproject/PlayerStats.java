/**
 * Sydney Thomas
 * January 14, 2019
 * Player statistics class that stores the stats for players game performance
 * Each player will have a PlayerStats for each game that they play in
 */
package com.example.zhuthomasfinalproject;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class PlayerStats implements Serializable {
    private int minPlayed; //minutes played by current player
    private int twoPtMakes; //two point shot made by player
    private int twoPtMisses; //two point shot attempted by player
    private int threePtMakes; //three point shot made by player
    private int threePtMisses; //three point show attempted by player
    private int assists; //assist count
    private int ftMakes; //free throws made by player
    private int ftMisses; //free throws attempted by player
    private int defRebs; //defensive rebounds
    private int offRebs; //offensive rebounds
    private int turnovers; //total turnovers
    private int steals; //total steals
    private int blocks; //total blocks
    private long gameDateTime; //time and date associated with this set of stats
    private int fouls; //number of fouls
    private int points; //points
    private long playingTime; // playing time in milliseconds
    private String opp; //opponent name

    /**
     * default constructor that constructs a com.example.zhuthomasfinalproject.PlayerStats class and initializes attributes
     */
    public PlayerStats(){
        minPlayed = 0;
        twoPtMakes = 0;
        twoPtMisses = 0;
        threePtMakes = 0;
        threePtMisses = 0;
        assists = 0;
        ftMakes = 0;
        ftMisses = 0;
        defRebs = 0;
        offRebs = 0;
        turnovers = 0;
        steals = 0;
        blocks = 0;
        gameDateTime = System.currentTimeMillis();
        playingTime = 0;
        opp = "";
    }

    /**
     * constructor that constructs a com.example.zhuthomasfinalproject.PlayerStats class with parameter and calls default constructor
     * @param gDT - date and time of game
     */
    public PlayerStats(long gDT){
        this();
        gameDateTime = gDT;
    }

    public String getOpp(){
        return opp;
    }

    public void setOpp(String o) {
        opp = o;
    }


    /**
     * accessor that gets the the timestamp for the game
     * @return - timestamp for the game
     */
    public long getGameDateTime() {
        return gameDateTime;
    }

    /**
     * mutator that sets the timestamp for the game
     * @param gDT - timestamp to set game
     */
    public void setGameDateTime(long gDT) {
        gameDateTime = gDT;
    }


    /**
     * accessor that gets the number of minutes the player has played
     * @return - number of minutes played
     */
    public int getMinPlayed() {
        return minPlayed;
    }

    /**
     * mutator that sets the number of minutes played by current player
     * @param mPlay - minutes of current player to set the minutes played to
     */
    public void setMinPlayed(int mPlay) {
        minPlayed = mPlay;
    }

    /**
     * accessor that gets the number of two pointers made by current player
     * @return - number of two pointers made
     */
    public int getTwoPtMakes() {
        return twoPtMakes;
    }

    /**
     * method that adds one to the two points makes stat
     */
    public void addTwoPtMakes(){
        twoPtMakes++;
    }

    /**
     * method that subtracts one from the two point makes stat
     */
    public void subtractTwoPtMakes(){
        twoPtMakes--;
    }

    /**
     * mutator that sets two pointers made by current player
     * @param twoPM - two pointers to set
     */
    public void setTwoPtMakes(int twoPM) {
        twoPtMakes = twoPM;
    }

    /**
     * accessor that gets the number of two pointers attempted by current player
     * @return - number of two pointers attempted by current player
     */
    public int getTwoPtMisses() {
        return twoPtMisses;
    }

    /**
     * method that adds one to two point misses stat
     */
    public void addTwoPtMisses(){
        twoPtMisses++;
    }

    /**
     * method that subtract one from two point misses stat
     */
    public void subtractTwoPtMisses(){
        twoPtMisses--;
    }

    /**
     * mutator that sets the number of two pointers missed by current player
     * @param twoPM - two pointers missed by current player to set
     */
    public void setTwoPtMisses(int twoPM) {
        twoPtMisses = twoPM;
    }

    /**
     * accessor that gets the number of three pointers made by current player
     * @return - number of three pointers made
     */
    public int getThreePtMakes() {
        return threePtMakes;
    }

    /**
     * method that adds one to the three point makes stat
     */
    public void addThreePtMakes(){
        threePtMakes++;
    }

    /**
     * method that subtracts one from the three point makes stat
     */
    public void subtractThreePtMakes(){
        threePtMakes--;
    }
    /**
     * mutator that sets the number of three pointers made by current player
     * @param threePM - three pointers made by current player to set
     */
    public void setThreePtMakes(int threePM) {
        threePtMakes = threePM;
    }

    /**
     * accessor that gets the number of three pointers missed by current player
     * @return - number of three pointers missed
     */
    public int getThreePtMisses() {
        return threePtMisses;
    }

    /**
     * method that adds one to the three point misses stat
     */
    public void addThreePtMisses(){
        threePtMisses++;
    }

    /**
     * method that subtracts one from the three point misses stat
     */
    public void subtractThreePtMisses(){
        threePtMisses--;
    }
    /**
     * mutator that sets the number of three pointers missed by the current player
     * @param threePM - three pointers missed by current player to set
     */
    public void setThreePtMisses(int threePM) {
        threePtMisses = threePM;
    }

    /**
     * accessor that gets the number of assists
     * @return - number of assists
     */
    public int getAssists() {
        return assists;
    }

    /**
     * method that adds one to the assist stat
     */
    public void addAssists(){
        assists++;
    }

    /**
     * method that subtracts one from the assist stat
     */
    public void subtractAssists(){
        assists--;
    }

    /**
     * mutator that sets the number of assists
     * @param a - number of assists to set
     */
    public void setAssists(int a) {
        assists = a;
    }

    /**
     * accessor that gets the number of free throws made by the current player
     * @return - number of free throws made
     */
    public int getFtMakes() {
        return ftMakes;
    }

    /**
     * method that adds one to the free throw makes stat
     */
    public void addFtMakes(){
        ftMakes++;
    }

    /**
     * method that subtracts one from the free throw makes stat
     */
    public void subtractFtMakes(){
        ftMakes--;
    }

    /**
     * mutator that sets the number of free throws made
     * @param ftM - free throws made to set
     */
    public void setFtMakes(int ftM) {
        ftMakes = ftM;
    }

    /**
     * accessor that gets the number of free throws missed by current player
     * @return - number of free throws missed
     */
    public int getFtMisses() {
        return ftMisses;
    }

    /**
     * method that adds one to the free throw missed stat
     */
    public void addFtMisses(){
        ftMisses++;
    }

    /**
     * method that subtracts one from the free throw missed stat
     */
    public void subtractFtMisses(){
        ftMisses--;
    }

    /**
     * mutator that sets the number of free throws missed
     * @param ftM - free throws missed to set
     */
    public void setFtMisses(int ftM) {
        ftMisses = ftM;
    }

    /**
     * accessor that gets the number of defensive rebounds by current player
     * @return - number of defensive rebounds
     */
    public int getDefRebs() {
        return defRebs;
    }

    /**
     * method that adds one to the defensive rebound stat
     */
    public void addDefRebs(){
        defRebs++;
    }

    /**
     * method that subtracts one from the defensive rebound stat
     */
    public void subtractDefRebs(){
        defRebs--;
    }

    /**
     * mutator that sets the number of defensive rebounds
     * @param dR - defensive rebounds to set
     */
    public void setDefRebs(int dR) {
        defRebs = dR;
    }

    /**
     * accessor that gets the number of offensive rebounds by current player
     * @return - number of offensive rebounds
     */
    public int getOffRebs() {
        return offRebs;
    }
    /**
     * method that adds one to the offensive rebound stat
     */
    public void addOffRebs(){
        offRebs++;
    }

    /**
     * method that subtracts one from the offensive rebound stat
     */
    public void subtractOffRebs(){
        offRebs--;
    }

    /**
     * mutator that sets the number of offensive rebounds
     * @param oR - offensive rebounds to set
     */
    public void setOffRebs(int oR) {
        offRebs = oR;
    }

    /**
     * accessor that gets the total number of rebounds
     * @return - total number of rebounds (offensive + defensive)
     */
    public int getTtlRebs() {
        return offRebs + defRebs;
    }

    /**
     * accessor that gets the number of turnovers by current player
     * @return - number of turnovers
     */
    public int getTurnovers() {
        return turnovers;
    }

    /**
     * method that adds one to the turnover stat
     */
    public void addTurnovers(){
        turnovers++;
    }

    /**
     * method that subtracts one from the turnover stat
     */
    public void subtractTurnovers(){
        turnovers--;
    }
    /**
     * mutator that sets the number of turnovers
     * @param t - number of turnovers to set
     */
    public void setTurnovers(int t) {
        turnovers = t;
    }

    /**
     * accessor that gets the number of steals
     * @return - number of steals
     */
    public int getSteals() {
        return steals;
    }

    /**
     * method that adds one to the steals stat
     */
    public void addSteals(){
        steals++;
    }

    /**
     * method that subtracts one from the steals stat
     */
    public void subtractSteals(){
        steals--;
    }

    /**
     * mutator that sets the number of steals
     * @param s - number of steals to set
     */
    public void setSteals(int s) {
        steals = s;
    }

    /**
     * accessor that gets the number of blocks by current player
     * @return - number of blocks
     */
    public int getBlocks() {
        return blocks;
    }

    /**
     * method that adds one to the blocks stat
     */
    public void addBlocks(){
        blocks++;
    }

    /**
     * method that subtracts one from the blocks stat
     */
    public void subtractBlocks(){
        blocks--;
    }

    /**
     * mutator that sets the number of blocks
     * @param b - number of blocks to set
     */
    public void setBlocks(int b) {
        blocks = b;
    }

    /**
     * accessor that gets the number of fouls on player
     * @return - number of fouls on player
     */
    public int getFouls() {
        return fouls;
    }

    /**
     * method that adds one to the personal fouls stat
     */
    public void addFouls(){
        fouls++;
    }

    /**
     * method that subtracts one from the personal fouls stat
     */
    public void subtractFouls(){
        fouls--;
    }

    /**
     * mutator that sets the number of personal fouls
     * @param f - number of personal fouls to set
     */
    public void setFouls(int f) {
        fouls = f;
    }

    /**
     * accessor that gets the number of points by the player
     * @return - number of points by player
     */
    public int getPoints() {
        return points;
    }

    /**
     * mutator that sets the number of points by player
     * @param p - number of points by player to set
     */
    public void setPoints(int p) {
        points = p;
    }

    /**
     * method that adds specified value to the points for the player
     * @param ptsToAdd - value to add to points
     */
    public void addPoints(int ptsToAdd) {
        points += ptsToAdd;
    }

    /**
     * method that subtracts specified value from points for the player
     * @param ptsToSub - value to subtract from points
     */
    public void subtractPoints(int ptsToSub) {
        points -= ptsToSub;
    }

    /**
     * accessor that gets playing time in milliseconds
     * @return - playing time in milliseconds
     */
    public long getPlayingTime() {
        return playingTime;
    }

    /**
     * mutator that sets playing time in milliseconds
     * @param t - playing time in milliseconds to set
     */
    public void setPlayingTime(long t) {
        playingTime = t;
    }

    /**
     * method that adds specified value to playing time
     * @param t - specified value
     */
    public void addPlayingTime(long t) {
        playingTime = playingTime + t;
    }

    /**
     * method that calculates the two point attempts
     * @return - two point attempts
     */
    public int getTwoPtAttempts() {
        return twoPtMakes + twoPtMisses;
    }

    /**
     * method that calculates the two point percentage
     * @return - two point percentage
     */
    public double getTwoPtPct() {
        if (this.getTwoPtAttempts() > 0) {
            return (double) twoPtMakes / (double) this.getTwoPtAttempts();
        }
        return 0.0;
    }

    public int getFGs() {
        return twoPtMakes + threePtMakes;
    }

    public int getFGAttempts() {
        return this.getTwoPtAttempts() + this.get3Attempts();
    }

    public double getFGPct() {
        if (this.getFGAttempts() > 0) {
            return (double) this.getFGs() / (double) this.getFGAttempts();
        }
        return 0.0;

    }

    /**
     * method that calculates total three point attempts
     * @return - total three point attempts
     */
    public int get3Attempts() {
        return threePtMakes + threePtMisses;
    }

    /**
     * method that calculates the three point percentage
     * @return - three point percentage
     */
    public double get3Pct() {
        if (this.get3Attempts() > 0) {
            return (double) threePtMakes / (double) this.get3Attempts();
        }
        return 0.0;
    }

    /**
     * method that calculates total free throw attempts
     * @return - total free throw attempts
     */
    public int getFTAttempts() {
        return ftMakes + ftMisses;
    }

    /**
     * method that calculates free throw percentage
     * @return - free throw percentage
     */
    public double getFTPct() {
        if (this.getFTAttempts() > 0) {
            return (double) ftMakes / (double) this.getFTAttempts();
        }
        return 0.0;
    }

    /**
     * Effective Field Goal Percentage; the formula is (FG + 0.5 * 3P) / FGA.
     * This statistic adjusts for the fact that a 3-point field goal is worth one more point than a 2-point field goal.
     * For example, suppose Player A goes 4 for 10 with 2 threes, while Player B goes 5 for 10 with 0 threes.
     * Each player would have 10 points from field goals, and thus would have the same effective field goal percentage (50%)
     * (from Basketball Reference)
     * @return the Player's effective field goal percentage
     */
    public double getEFGPct() {
        if (this.getFGAttempts() > 0) {
            return (twoPtMakes + threePtMakes + 0.5 * threePtMakes) / (double) this.getFGAttempts();
        }
        return 0.0;
    }

    /**
     * String representation of class
     * @return - string representation
     */
    public String toString(){
        String str = "";
        Date d = new  Date(this.gameDateTime);
        Time t = new Time(this.gameDateTime);
        str += "Minutes Played: " + minPlayed + " Two Pointers Made: " + twoPtMakes + " Two Pointers Missed: " +
                twoPtMisses + " Three Pointers Made: " + threePtMakes + " Three Pointers Missed: " + threePtMisses +
                " Assist: " + assists + " Free Throws Made: " + ftMakes + " Free Throws Missed: " + ftMisses +
                "\nDefensive Rebounds: " + defRebs + " Offensive Rebounds: " + offRebs + " Total Rebounds: " +
                offRebs+defRebs + " Turnovers: " + turnovers + " Steals: " + steals + " Blocks: " + blocks +
                " Fouls: " + fouls +  " Date/Time " +  d.toString() + " " + t.toString();
        return str;
    }
}
