package com.example.zhuthomasfinalproject;

public class StatsManager {
    private Player currentPlayer;
    private Season currentSeason;
    private Game currentGame;

    private Player Playing[];

    public StatsManager() {
        Playing = new Player[5];
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public Season getCurrentSeason() {
        return currentSeason;
    }

    public void setCurrentSeason(Season currentSeason) {
        this.currentSeason = currentSeason;
    }

    public Season newSeason() {
        Season s = new Season();
        return s;
    }

    public void addToRoster( String name, int num ) {
        Player p = new Player(name, num);

        this.currentSeason.getRoster().addPlayer(p);


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
