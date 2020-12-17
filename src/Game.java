import java.util.Collections;

public class Game {
    private Player[] players = new Player[2];
    private Piece[][] grid = new Piece[7][6];

    public Game(String player1, String player2)
    {
        players[0] = new Player(player1, Color.RED);
        players[1] = new Player(player2, Color.YELLOW);
    }

    public void insertPiece(Piece p, int column) {
        //TODO Affichage erreur utilisateur
        if(grid[0][column] != null)
            System.out.println("La colonne sélectionnée est pleine !");

        for(int i=grid[1].length-1; i>=0; --i)
        {
            if( grid[i][column] == null)
            {
                grid[i-1][column] = p;
                break;
            }
        }
    }

    public Player[] getPlayers() {
        return players;
    }

    public Player getPlayer(int i) {
        return players[i];
    }

    public Player winner()
    {
        Player winner = null;
        Color c = null;
        int piecesInARow = 0;
        for(int i=0; i<grid[1].length; ++i)
        {
            for(int j=0; j<grid[0].length; ++j)
            {
                if(c == null)
                {
                    c = grid[j][i].getColor();
                    piecesInARow = 1;
                }
                else if(c == grid[j][i].getColor())
                {
                    piecesInARow++;
                }
                else
                {
                    c = grid[j][i].getColor();
                    piecesInARow = 1;
                }

                if(piecesInARow >= 4)
                {
                    winner = c == getPlayer(0).getColor() ? getPlayer(0) : getPlayer(1);
                    break;
                }
            }
        }

        if(winner == null)
        {
            c = null;

            for(int i=0; i<grid[0].length; ++i)
            {
                for(int j=0; j<grid[1].length; ++j)
                {
                    if(c == null)
                    {
                        c = grid[i][j].getColor();
                        piecesInARow = 1;
                    }
                    else if(c == grid[i][j].getColor())
                    {
                        piecesInARow++;
                    }
                    else
                    {
                        c = grid[i][j].getColor();
                        piecesInARow = 1;
                    }

                    if(piecesInARow >= 4)
                    {
                        winner = c == getPlayer(0).getColor() ? getPlayer(0) : getPlayer(1);
                        break;
                    }
                }
            }
        }

        if(winner == null)
        {
            c = null;


        }

        return winner;
    }
}
