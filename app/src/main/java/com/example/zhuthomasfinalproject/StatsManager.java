package com.example.zhuthomasfinalproject;

public class StatsManager {
    private Player currentPlayer;
    private Season currentSeason;
    private Game currentGame;
    private boolean test = true;

    private Player Playing[];

    public  StatsManager() {
        Playing = new Player[5];
        if( test ) {
            long t = System.currentTimeMillis();
            Playing[0] = new Player( "Jessica", 7);
            Playing[0].addPlayerStat(t);
            Playing[1] = new Player( "Sydney", 11);
            Playing[1].addPlayerStat(t);
            Playing[2] = new Player( "Tammy", 17);
            Playing[2].addPlayerStat(t);
            Playing[3] = new Player( "Sarah", 8);
            Playing[3].addPlayerStat(t);
            Playing[4] = new Player( "Emma", 12);
            Playing[4].addPlayerStat(t);
            currentPlayer = Playing[0];
        }


    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player cp) {
        currentPlayer = cp;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game game) {
        currentGame = game;
    }

    public Season getCurrentSeason() {
        return currentSeason;
    }

    public void setCurrentSeason(Season season) {
        currentSeason = season;
    }

    public Season newSeason() {
        Season s = new Season();
        return s;
    }

    public void addToRoster( String name, int num ) {
        Player p = new Player(name, num);

        currentSeason.getRoster().addPlayer(p);
    }

    public Game newGame() {
        Game newGame = new Game();
        return newGame;
    }

    public void putPlayerInGame(int slot, Player p) {
        Playing[slot].Bench(null);  // TODO pass in time
        p.Play(null); // TODO pass in time
        Playing[slot] = p;

    }

    public void removePlayerFromGame(int slot) {
        Playing[slot].Bench(null);  // TODO pass in time

        Playing[slot] = null;
    }

    public void selectPlayer(String name, int number) {
        this.currentPlayer = this.currentGame.getTeam().findPlayer(name, number);
    }

}
