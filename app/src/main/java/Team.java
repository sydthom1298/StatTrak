import java.util.ArrayList;
public class Team {
    private String name;
    private int numPlayers;
    private ArrayList<Player> players;

    /**
     * default constructor that constructs a team class and initializes attributes
     */
    public Team(){
        name = "";
        numPlayers = 0;
    }


    /**
     * constructor that constructs a team class with parameters and initializes attributes
     * @param n - name of team
     * @param num - number of players on team
     */
    public Team(String n, int num){
        name = n;
        numPlayers = num;
    }

    /**
     * constructor that constructs a team class with parameters and initializes attributes
     * @param n - name of team
     * @param num - number of players on team
     * @param p - array list that stores all the players on the team
     */
    public Team(String n, int num, ArrayList<Player> p){
        name = n;
        numPlayers = num;
        players = p;
    }

    /**
     * accessor that gets the name of the team
     * @return - name of team
     */
    public String getName() {
        return name;
    }

    /**
     * mutator that sets the name of the current team
     * @param n - name to set current team to
     */
    public void setName(String n) {
        name = n;
    }

    /**
     * accessor that gets the number of players on the current team
     * @return - number of players on current team
     */
    public int getNumPlayers() {
        return numPlayers;
    }

    /**
     * mutator that sets the number of players on the current team
     * @param nP - number of players to set number of players to
     */
    public void setNumPlayers(int nP) {
        numPlayers = nP;
    }

    /**
     * accessor to return players
     * @return player ArrayList
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> p) {
        players = p;
    }

    /**
     * creates string representaiton of class
     * @return - string representation
     */
    public String toString(){
        String str = "";
        str += "Name: " + name + "\nNumber of Players: " + numPlayers + "\nPlayers: " + players;
        return str;
    }
}
