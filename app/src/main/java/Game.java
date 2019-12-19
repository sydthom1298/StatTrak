public class Game {
    int time;
    String team; //team name stats are being tracked for
    String opponent;
    String date;
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

    public String toString(){
        String str = "";
        str += "Team Name: " + team + "\nOpponent: " + opponent + "\nDate: ";
        return str;
    }
}
