import java.sql.Time;
import java.util.Date;

public class PlayerStats {
    private int minPlayed; //minutes played by current player
    private int twoPtMakes; //two point shot made by player
    private int twoPtAttempts; //two point shot attempted by player
    private int threePtMakes; //three point shot made by player
    private int threePtAttempts; //three point show attempted by player
    private int assist; //assist count
    private int ftMakes; //free throws made by player
    private int ftAttempts; //free throws attempted by player
    private int defRebs; //defensive rebounds
    private int offRebs; //offensive rebounds
    private int ttlRebs; //total rebounds
    private int turnovers; //total turnovers
    private int steals; //total steals
    private int blocks; //total blocks
    private long gameDateTime;

    /**
     * default constructor that constructs a PlayerStats class and initializes attributes
     */
    public PlayerStats(){
        minPlayed = 0;
        twoPtMakes = 0;
        twoPtAttempts = 0;
        threePtMakes = 0;
        threePtAttempts = 0;
        assist = 0;
        ftMakes = 0;
        ftAttempts = 0;
        defRebs = 0;
        offRebs = 0;
        ttlRebs = 0;
        turnovers = 0;
        steals = 0;
        blocks = 0;
        gameDateTime = System.currentTimeMillis();
    }

    /**
     * constructor that constructs a PlayerStats class with parameter and calls default constructor
     * @param gDT - date and time of game
     */
    public PlayerStats(long gDT){
        this();
        gameDateTime = gDT;
    }


    public long getGameDateTime() {
        return gameDateTime;
    }

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

    public void addTwoPtMakes(){
        twoPtMakes++;
    }
    public void subtractTwoPtMakes(){
        twoPtMakes--;
    }

    /**
     * mutator that sets two pointers made by current player
     * @param twoPM - two pointers to set two pointers made to
     */
    public void setTwoPtMakes(int twoPM) {
        twoPtMakes = twoPM;
    }

    /**
     * accessor that gets the number of two pointers attempted by current player
     * @return - number of two pointers attempted by current player
     */
    public int getTwoPtAttempts() {
        return twoPtAttempts;
    }

    public void addTwoPtAttempts(){
        twoPtAttempts++;
    }
    public void subtractTwoPtAttempts(){
        twoPtAttempts--;
    }

    /**
     * mutator that sets the number of two pointers attempted by current player
     * @param twoPA - two pointers attempted by current player to set two points atempted to
     */
    public void setTwoPtAttempts(int twoPA) {
        twoPtAttempts = twoPA;
    }

    /**
     * accessor that gets the number of three pointers made by current player
     * @return - number of three pointers made
     */
    public int getThreePtMakes() {
        return threePtMakes;
    }

    public void addThreePtMakes(){
        threePtMakes++;
    }
    public void subtractThreePtMakes(){
        threePtMakes--;
    }
    /**
     * mutator that sets the number of three pointers made by current player
     * @param threePM - three pointers made by current player to set three pointers made to
     */
    public void setThreePtMakes(int threePM) {
        threePtMakes = threePM;
    }

    /**
     * accessor that gets the number of three pointers attempted by current player
     * @return - number of three pointers attempted
     */
    public int getThreePtAttempts() {
        return threePtAttempts;
    }
    public void addThreePtAttempts(){
        threePtAttempts++;
    }
    public void subtractThreePtAttempts(){
        threePtAttempts--;
    }
    /**
     * mutator that sets the number of three pointers attempted by the current player
     * @param threePA - three pointers attempted by current player to set three pointers attempted to
     */
    public void setThreePtAttempts(int threePA) {
        threePtAttempts = threePA;
    }

    /**
     * accessor that gets the number of assists by the current player
     * @return - number of assists
     */
    public int getAssist() {
        return assist;
    }
    public void addAssists(){
        assist++;
    }
    public void subtractAssists(){
        assist--;
    }

    /**
     * mutator that sets the number of assists by the current player
     * @param a - number of assits by current player to set number of assists to
     */
    public void setAssist(int a) {
        assist = a;
    }

    /**
     * accessor that gets the number of free throws made by the current player
     * @return - number of free throws made
     */
    public int getFtMakes() {
        return ftMakes;
    }
    public void addFtMakes(){
        ftMakes++;
    }
    public void subtractFtMakes(){
        ftMakes--;
    }

    /**
     * mutator that sets the number of free throws made by current player
     * @param ftM - free throws made by current player to set free throws made to
     */
    public void setFtMakes(int ftM) {
        ftMakes = ftM;
    }

    /**
     * accessor that gets the number of free throws attempted by current player
     * @return - number of free throws attempted
     */
    public int getFtAttempts() {
        return ftAttempts;
    }
    public void addFtAttempts(){
        ftAttempts++;
    }
    public void subtractFtAttempts(){
        ftAttempts--;
    }

    /**
     * mutator that sets the number of free throws attempted by current player
     * @param ftA - free throws attempted by current player to set free throws attempted to
     */
    public void setFtAttempts(int ftA) {
        ftAttempts = ftA;
    }

    /**
     * accessor that gets the number of defensive rebounds by current player
     * @return - number of defensive rebounds
     */
    public int getDefRebs() {
        return defRebs;
    }
    public void addDefRebs(){
        defRebs++;
    }
    public void subtractDefRebs(){
        defRebs--;
    }

    /**
     * mutator that sets the number of defensive rebounds by current player
     * @param dR - defensive rebounds by current player to set defensive rebounds to
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
    public void addOffRebs(){
        offRebs++;
    }
    public void subtractOffRebs(){
        offRebs--;
    }

    /**
     * mutator that sets the number of offensive rebounds by current player
     * @param oR - offensive rebounds by current player to set offensive rebounds to
     */
    public void setOffRebs(int oR) {
        offRebs = oR;
    }

    /**
     * accessor that gets the total number of rebounds by current player
     * @return - total number of rebounds
     */
    public int getTtlRebs() {
        return ttlRebs;
    }
    public void addTtlRebs(){
        ttlRebs++;
    }
    public void subtractTtlRebs(){
        ttlRebs--;
    }

    /**
     * mutator that sets the total number of rebounds by current player
     * @param ttlR - total rebounds by current player to set total rebounds to
     */
    public void setTtlRebs(int ttlR) {
        ttlRebs = ttlR;
    }

    /**
     * accessor that gets the number of turnovers by current player
     * @return - number of turnovers
     */
    public int getTurnovers() {
        return turnovers;
    }
    public void addTurnovers(){
        turnovers++;
    }
    public void subtractTurnovers(){
        turnovers--;
    }
    /**
     * mutator that sets the number of turnovers by current player
     * @param t - turnovers by current player to set turnovers to
     */
    public void setTurnovers(int t) {
        turnovers = t;
    }

    /**
     * accessor that gets the number of steals by current player
     * @return - number of steals by current player
     */
    public int getSteals() {
        return steals;
    }
    public void addSteals(){
        steals++;
    }
    public void subtractSteals(){
        steals--;
    }

    /**
     * mutator that sets the number of steals by current player
     * @param s - steals by current player to set steals to
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
    public void addBlocks(){
        blocks++;
    }
    public void subtractBlocks(){
        blocks--;
    }

    /**
     * mutator that sets the number of blocks by current player
     * @param b - blocks by current player to set blocks to
     */
    public void setBlocks(int b) {
        blocks = b;
    }

    /**
     * String representation of class
     * @return - string representation
     */
    public String toString(){
        String str = "";
        Date d = new  Date(this.gameDateTime);
        Time t = new Time(this.gameDateTime);
        str += "Minutes Played: " + minPlayed + "\nTwo Pointers Made: " + twoPtMakes + "Three Pointers Made: " +
                threePtMakes + "\nAssist: " + assist + "\nFree Throws Made: " + ftMakes + "\nDefensive Rebounds: " +
                defRebs + "\nOffensive Rebounds: " + offRebs + "\nTotal Rebounds: " + ttlRebs + "\nTurnovers: " +
                turnovers + "\nSteals: " + steals + "\nBlocks: " + blocks + "\nDate/Time " +  d.toString() + " " + t.toString();
        //TODO add missing fields
        return str;
    }
}
