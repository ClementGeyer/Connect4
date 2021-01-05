package connectfourcontroller;

import connectfourmodel.Coin;
import connectfourmodel.Color;
import connectfourmodel.Game;

import java.util.Arrays;

public class ConnectFourController extends TemplateController
{
    public ConnectFourController(Game g)
    {
        setGame(g);
        setCurrentPlayer(g.getPlayer(0));
    }

    @Override
    public void play()
    {
        // main: setCurrentPlayer(getGame().getPlayer(0));

        getCurrentPlayer().play();
        insertCoin();

        // playCoin dans vue va setCarteCourante & appeler play()

        if(getGame().winner() == null || getCurrentPlayer().getCoins() > 0)
        {
            setCurrentPlayer( getCurrentPlayer() == getGame().getPlayer(0) ? getGame().getPlayer(1) : getGame().getPlayer(0));
        }
        else
        {
            setGameEnded(true);
        }

        notifyObservers();
    }

    public void lineCheck(){
        Coin[][] grid = getGame().getGrid();
        getGame().setCurrentLine(grid[1].length);
        for(int i = 0;i<=grid[1].length;i++){
            //if(grid[getGame().getCurrentLine()][getCurrentColumn()] == null)
                //System.out.println(i);
                //getGame().setCurrentLine(getGame().getCurrentLine() - 1);
        }
    }

    @Override
    public void insertCoin()
    {
        getGame().insertCoin(getCurrentColumn());
    }
}
