public class PlayerStats {

    int minPlayed; //minutes played by current player
    int twoPtMakes; //two point shot made by player
    int twoPtAttempts; //two point shot attempted by player
    int threePtMakes; //three point shot made by player
    int threePtAttempts; //three point show attempted by player
    int assist; //assist count
    int ftMakes; //free throws made by player
    int ftAttempts; //free throws attempted by player
    int defRebs; //defensive rebounds
    int offRebs; //offsenvie rebounds
    int ttlRebs; //total rebounds
    int turnovers; //total turnovers
    int steals; //total steals
    int blocks; //total blocks

    /**
     * default constructor that constructs a player class and initializes attributes
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
    }

    /**
     * constructor that constructs a player stats class with parameters and initializes attributes
     * @param min - players minutes
     * @param twoPointM - two points made
     * @param threePointM - three points made
     * @param a - assist count
     * @param freeThrowM - free throws made
     * @param r - total rebounds
     * @param t - turnover count
     * @param s - steal count
     * @param b - block count
     */
    public PlayerStats(int min, int twoPointM, int threePointM, int a, int freeThrowM, int r, int t, int s, int b){
        minPlayed = min;
        twoPtMakes = twoPointM;
        threePtMakes = threePointM;
        assist = a;
        ftMakes = freeThrowM;
        ttlRebs = r;
        turnovers = t;
        steals = s;
        blocks = b;
    }
    public int getMinPlayed() {
        return minPlayed;
    }

    public void setMinPlayed(int mPlay) {
        minPlayed = mPlay;
    }

    public int getTwoPtMakes() {
        return twoPtMakes;
    }

    public void setTwoPtMakes(int twoPM) {
        twoPtMakes = twoPM;
    }

    public int getTwoPtAttempts() {
        return twoPtAttempts;
    }

    public void setTwoPtAttempts(int twoPA) {
        twoPtAttempts = twoPA;
    }

    public int getThreePtMakes() {
        return threePtMakes;
    }

    public void setThreePtMakes(int threePM) {
        threePtMakes = threePM;
    }

    public int getThreePtAttempts() {
        return threePtAttempts;
    }

    public void setThreePtAttempts(int threePA) {
        threePtAttempts = threePA;
    }

    public int getAssist() {
        return assist;
    }

    public void setAssist(int a) {
        assist = a;
    }

    public int getFtMakes() {
        return ftMakes;
    }

    public void setFtMakes(int ftM) {
        ftMakes = ftM;
    }

    public int getFtAttempts() {
        return ftAttempts;
    }

    public void setFtAttempts(int ftA) {
        ftAttempts = ftA;
    }

    public int getDefRebs() {
        return defRebs;
    }

    public void setDefRebs(int dR) {
        defRebs = dR;
    }

    public int getOffRebs() {
        return offRebs;
    }

    public void setOffRebs(int oR) {
        offRebs = oR;
    }

    public int getTtlRebs() {
        return ttlRebs;
    }

    public void setTtlRebs(int ttlR) {
        ttlRebs = ttlR;
    }

    public int getTurnovers() {
        return turnovers;
    }

    public void setTurnovers(int t) {
        turnovers = t;
    }

    public int getSteals() {
        return steals;
    }

    public void setSteals(int s) {
        steals = s;
    }

    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(int b) {
        blocks = b;
    }
    public String toString(){
        String str = "";
        str += "Minutes Played: " + minPlayed + "\nTwo Pointers Made: " + twoPtMakes + "Three Pointers Made: " +
                threePtMakes + "\nAssist: " + assist + "\nFree Throws Made: " + ftMakes + "\nDefensive Rebounds: " +
                defRebs + "\nOffensive Rebounds: " + offRebs + "\nTotal Rebounds: " + ttlRebs + "\nTurnovers: " +
                turnovers + "\nSteals: " + steals + "\nBlocks: " + blocks;
        return str;
    }
}
