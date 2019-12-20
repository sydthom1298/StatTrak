public class Player {
    private int jerseyNum;
    private String name;
    private PlayerStats stats;

    public Player(){
        jerseyNum = 0;
        name = "";
        //initialize PlayerStats stats

    }
    public Player(String n){
        name = n;
    }
    public Player(String n, int num, PlayerStats p){
        name = n;
        jerseyNum = num;
        stats = p;
    }

    public int getJerseyNum() {
        return jerseyNum;
    }

    public void setJerseyNum(int num) {
        jerseyNum = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public PlayerStats getStats() {
        return stats;
    }

    public void setStats(PlayerStats s) {
        stats = s;
    }

    public String toString(){
        String str = "";
        str += "Name: " + name + "\nPlayer Number: " + jerseyNum;
        return str;
    }
}
