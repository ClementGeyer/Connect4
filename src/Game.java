public class Game {
    private Player[] players = new Player[2];
    private Piece[][] grid = new Piece[7][6];

    public Game(String player1, String player2)
    {
        players[0] = new Player(player1);
        players[1] = new Player(player2);
    }

    public void insertPiece(Piece p, int column) {
        //TODO Si colonne pleine ajoute pas
        for(int i=grid[1].length-1; i>=0; i--)
        {
            if( grid[i][column] == null)
            {
                grid[i-1][column] = p;
            }
        }
    }

    public Player[] getPlayers() {
        return players;
    }

    public Player getPlayer(int i) {
        return players[i];
    }
}
