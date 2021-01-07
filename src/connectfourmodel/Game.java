package connectfourmodel;

public class Game {
    private final Player[] players = new Player[2];
    private int coinsRowToWin = 4;
    private  Coin[][] grid;
    private int currentLine;

    public boolean isCurrentColumnFull() {
        return currentColumnFull;
    }

    private boolean currentColumnFull = false;

    public int getNumberToSubtract() {
        return numberToSubtract;
    }

    public void setNumberToSubtract(int numberToSubtract) {
        this.numberToSubtract = numberToSubtract;
    }

    private int numberToSubtract;

    public int getCurrentColumn() {
        return currentColumn;
    }

    private int currentColumn;

    public Game(String player1, String player2)
    {
        players[0] = new Player(player1, Color.RED);
        players[1] = new Player(player2, Color.YELLOW);
        initializeGrid(6, 7);
    }

    public int getCurrentLine(){
        return this.currentLine;
    }

    public void insertCoin(int column, Color c) {
        currentColumnFull = grid[1][column] != null;

        for(int i=grid.length-1; i>=0; --i)
        {
            if( grid[i][column] == null)
            {
                grid[i][column] = new Coin(c);
                currentLine = i + 1;
                currentColumn = column;
                switch(currentLine){
                    case 1:
                        setNumberToSubtract(6);
                        break;
                    case 2:
                        setNumberToSubtract(5);
                        break;
                    case 3:
                        setNumberToSubtract(4);
                        break;
                    case 4:
                        setNumberToSubtract(3);
                        break;
                    case 5:
                        setNumberToSubtract(2);
                        break;
                    case 6:
                        setNumberToSubtract(1);
                        break;
                }
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