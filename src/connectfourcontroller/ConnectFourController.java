package connectfourcontroller;

import connectfourmodel.Coin;
import connectfourmodel.Color;
import connectfourmodel.Game;
import connectfourmodel.PopOut;

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

        getCurrentPlayer().play();
        insertCoin();

        notifyObservers();

        if(getGame().winner() == null && getCurrentPlayer().getCoins() > 0)
        {
            setCurrentPlayer( getCurrentPlayer() == getGame().getPlayer(0) ? getGame().getPlayer(1) : getGame().getPlayer(0));
        }
        else
        {
            setGameEnded(true);
            System.out.println("fini");
        }
    }

    public void playPopOut()
    {
        popOutCoin();

        if (getGame().winner() == null && getCurrentPlayer().getCoins() > 0) {
            setCurrentPlayer(getCurrentPlayer() == getGame().getPlayer(0) ? getGame().getPlayer(1) : getGame().getPlayer(0));
        } else {
            setGameEnded(true);
            System.out.println("fini");
        }

        // Print Grid
        /*
        for(int i=0; i<getGame().getGrid()[1].length; ++i)
        {
            for (int j = 0; j < getGame().getGrid().length; ++j)
            {
               System.out.print(" " + getGame().getGrid()[j][i]);
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("----------------------------------");

        for(int i=0; i<getGame().getGrid()[1].length; ++i)
        {
            for (int j = 0; j < getGame().getGrid().length; ++j)
            {
               System.out.print(" " + getGame().getGrid()[j][i]);
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("----------------------------------");
         */

        notifyObservers();
    }

    @Override
    public void insertCoin()
    {
        getGame().insertCoin(getCurrentColumn(), getCurrentPlayer().getColor());
    }

    public void popOutCoin() {
        ((PopOut) getGame()).popOutCoin(getCurrentColumn(), getCurrentPlayer().getColor());
    }
}
