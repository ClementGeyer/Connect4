package connectfourcontroller;

import connectfourmodel.Game;
import connectfourmodel.Player;

public abstract class TemplateController extends observer.Subject {
    private Game game;

    private Player currentPlayer;

    private boolean gameEnded;

    private int currentColumn = 4;

    public abstract void play();

    public abstract void insertCoin();

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public boolean isGameEnded() {
        return gameEnded;
    }

    public void setGameEnded(boolean gameEnded) {
        this.gameEnded = gameEnded;
    }

    public int getCurrentColumn() {
        return currentColumn;
    }

    public void setCurrentColumn(int currentColumn) {
        this.currentColumn = currentColumn;
    }
}
