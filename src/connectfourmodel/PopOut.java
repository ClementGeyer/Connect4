package connectfourmodel;

public class PopOut extends Game
{
    public PopOut(String player1, String player2) {
        super(player1, player2);
    }

    public void popOutCoin(int column, Color c)
    {
        if(getGrid()[getGrid().length-1][column].getColor() == c && getGrid()[getGrid().length-1][column] != null)
        {
            for(int i= getGrid().length - 2; i>= 0; i--)
            {
                getGrid()[i+1][column] = getGrid()[i][column];
            }
        }
    }
}
