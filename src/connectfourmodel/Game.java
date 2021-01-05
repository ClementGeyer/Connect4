package connectfourmodel;

public class Game {
    private final Player[] players = new Player[2];
    private int coinsRowToWin = 4;
    private  Coin[][] grid;
    private int currentLine;

    public Game(String player1, String player2)
    {
        players[0] = new Player(player1, Color.RED);
        players[1] = new Player(player2, Color.YELLOW);
        initializeGrid(6, 7);
        setCurrentLine(grid[1].length);
    }

    public int getCurrentLine(){
        return this.currentLine;
    }

    public void setCurrentLine(int line){
        this.currentLine = line;
    }

    public void insertCoin(int column) {
        //TODO Affichage erreur utilisateur
        if(grid[0][column] != null)
            System.out.println("La colonne sélectionnée est pleine !");

        for(int i=grid.length-1; i>=0; --i)
        {
            if( grid[i][column] == null)
            {
                //grid[i-1][column] = p;
                //TODO place coin
                break;
            }
        }
    }

    public void initializeGrid(int height, int width)
    {
        grid = new Coin[height][width];
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
            for(int j=0; j<grid.length; ++j)
            {
                if(grid[j][i] != null)
                {
                    if(c == grid[j][i].getColor())
                    {
                        piecesInARow++;
                    }
                    else
                    {
                        c = grid[j][i].getColor();
                        piecesInARow = 1;
                    }
                }

                if(piecesInARow >= coinsRowToWin)
                {
                    winner = c == getPlayer(0).getColor() ? getPlayer(0) : getPlayer(1);
                    break;
                }
            }
        }

        if(winner == null)
        {
            c = null;

            for(int i=0; i<grid.length; ++i)
            {
                for(int j=0; j<grid[1].length; ++j)
                {
                    if(grid[i][j] != null)
                    {
                        if(c == grid[i][j].getColor())
                        {
                            piecesInARow++;
                        }
                        else
                        {
                            c = grid[i][j].getColor();
                            piecesInARow = 1;
                        }
                    }

                    if(piecesInARow >= coinsRowToWin)
                    {
                        winner = c == getPlayer(0).getColor() ? getPlayer(0) : getPlayer(1);
                        break;
                    }
                }
            }
        }

        //TODO diagonal

        if(winner == null)
        {
            c = null;
        }

        return winner;
    }

    public int getCoinsRowToWin() {
        return coinsRowToWin;
    }

    public void setCoinsRowToWin(int coinsRowToWin) {
        this.coinsRowToWin = coinsRowToWin;
    }

    public Coin[][] getGrid() {
        return grid;
    }

    public void setGrid(Coin[][] grid) {
        this.grid = grid;
    }
}