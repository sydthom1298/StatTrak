import java.util.Date;
import java.sql.Time;

public class Game {
    private Team team; //team stats are being tracked for
    private String opponent;
    private long dateTime;
    public Game(){
        opponent = "";
        dateTime = System.currentTimeMillis();
    }
    public Game(Team t, String opp){
        team = t;
        opponent = opp;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team t) {
        team = t;
    }

    public String getOpponent() {
        return opponent;
    }

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
     * Method that creates String representation of class
     * @return - string representation
     */
    public String toString(){
        String str = "";
        Date d = new  Date(this.dateTime);
        Time t = new Time(this.dateTime);
        str += "Team Name: " + team + "\nOpponent: " + opponent + "\nDate/Time " + d.toString() + " " + t.toString();
        //TODO add missing fields
        return str;
    }
}
