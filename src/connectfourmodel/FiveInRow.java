package connectfourmodel;

public class FiveInRow extends Game
{
    @Override
    public String getType() {
        return "fiveinarow";
    }

    public FiveInRow(String player1, String player2)
    {
        super(player1, player2);
        initializeGrid(6,9);
        setCoinsRowToWin(5);
        fillGrid();
    }

    private void fillGrid()
    {
        //TODO colorier les cases dans la vue mais jsp comment faire
        for(int i=0; i<getGrid().length; ++i)
        {
            if(i % 2 == 0)
            {
                getGrid()[i][0] = new Coin(Color.RED);
                getGrid()[i][getGrid()[1].length-1] = new Coin(Color.YELLOW);
            }
            else
            {
                getGrid()[i][0] = new Coin(Color.YELLOW);
                getGrid()[i][getGrid()[1].length-1] = new Coin(Color.RED);
            }
        }
    }
}
