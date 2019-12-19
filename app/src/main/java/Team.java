import java.util.ArrayList;
public class Team {
    String name;
    int numPlayers;
    ArrayList<Player> players;

    /**
     * default constructor that constructs a team class and initializes attributes
     */
    public Team(){
        name = "";
        numPlayers = 0;
    }


    public Team(String n, int num){
        name = n;
        numPlayers = num;
    }
    public Team(String n, int num, ArrayList<Player> p){
        name = n;
        numPlayers = num;
        players = p;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int nP) {
        numPlayers = nP;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> p) {
        players = p;
    }

    public String toString(){
        String str = "";
        str += "Name: " + name + "\nNumber of Players: " + numPlayers + "\nPlayers: " + players;
        return str;
    }
}
