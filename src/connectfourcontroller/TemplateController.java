package connectfourcontroller;

import connectfourmodel.Game;
import connectfourmodel.Piece;
import connectfourmodel.Player;

public abstract class TemplateController
{
    private Game game;

    private Player currentPlayer;

    private boolean gameEnded;

    public void play()
    {

    }

    public abstract void insertPiece(Piece p, int column);

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
}
