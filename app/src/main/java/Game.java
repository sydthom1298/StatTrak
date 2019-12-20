public class Game {
    private int time;
    private String team; //team name stats are being tracked for
    private String opponent;
    private String date;
    public Game(){
        team = "";
        time = 0;
        opponent = "";

    }
    public Game(String t){
        team = t;
    }
    public Game(String d, Team t, String opp){
        date = d;
        //team = t;
        opponent = opp;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int t) {
        time = t;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String t) {
        team = t;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String o) {
        opponent = o;
    }

    /**
     * Method that creates String representation of class
     * @return - string representation
     */
    public String toString(){
        String str = "";
        str += "Team Name: " + team + "\nOpponent: " + opponent + "\nDate: ";
        return str;
    }
}
