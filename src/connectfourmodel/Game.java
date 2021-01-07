package connectfourmodel;

public class Game {
    private final Player[] players = new Player[2];
    private int coinsRowToWin = 4;
    private Coin[][] grid;
    private int currentLine;

    public String getType() {
        return "classic";
    }

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

    public Game(String player1, String player2) {
        players[0] = new Player(player1, Color.RED);
        players[1] = new Player(player2, Color.YELLOW);
        initializeGrid(6, 7);
    }

    public int getCurrentLine() {
        return this.currentLine;
    }

    public void setCurrentLine(int line) {
        this.currentLine = line;
    }

    public void insertCoin(int column, Color c) {
        currentColumnFull = grid[1][column] != null;

        for (int i = grid.length - 1; i >= 0; --i) {
            if (grid[i][column] == null) {
                grid[i][column] = new Coin(c);

                currentLine = i + 1;
                currentColumn = column;
                switch (currentLine) {
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

    public void initializeGrid(int height, int width) {
        grid = new Coin[height][width];
    }

    public Player[] getPlayers() {
        return players;
    }

    public Player getPlayer(int i) {
        return players[i];
    }

    public Color winner() {

        int l = 4;
        int ll = l - 1;

        int width = grid.length;
        int height = grid[0].length;


        // South - North
        int h = height - ll;
        for (int col = 0; col < width; col++)
            for (int row = 0; row < l; row++) {
                Coin c = grid[col][row];
                if (c == null)
                    break;
                boolean success = true;
                for (int i = 1; i < l && success; i++) {
                    if (grid[col][row + i] != null) {
                        success = (grid[col][row + i].getColor() == c.getColor());
                    } else {
                        success = false;
                    }
                }

                if (success)
                    return c.getColor();
            }


        //  West - East
        int w = width - ll;
        for (int col = 0; col < w; col++)
            for (int row = 0; row < height; row++) {
                Coin c = grid[col][row];
                if (c != null) {
                    boolean success = true;
                    for (int i = 1; i < l && success; i++) {
                        if (grid[col + i][row] != null) {
                            success = (grid[col + i][row].getColor() == c.getColor());
                        } else {
                            success = false;
                        }
                    }

                    if (success)
                        return c.getColor();
                }
            }


        // SouthWest - NorthEast
        h = height - ll;
        w = width - ll;
        for (int col = 0; col < w; col++)
            for (int row = 0; row < h; row++) {
                Coin c = grid[col][row];
                if (c != null) {
                    boolean success = true;
                    for (int i = 1; i < l && success; i++) {
                        if (grid[col + i][row + i] != null) {
                            success = (grid[col + i][row + i].getColor() == c.getColor());
                        } else {
                            success = false;
                        }
                    }

                    if (success)
                        return c.getColor();
                }
            }

        // NorthWest - SouthEast
        w = width - ll;
        for (int col = 0; col < w; col++)
            for (int row = ll; row < height; row++) {
                Coin c = grid[col][row];
                if (c != null) {
                    boolean success = true;
                    for (int i = 1; i < l && success; i++) {
                        if (grid[col + i][row - i] != null) {
                            success = (grid[col + i][row - i].getColor() == c.getColor());
                        } else {
                            success = false;
                        }
                    }

                    if (success)
                        return c.getColor();
                }
            }


        // No connection detected
        return null;
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
