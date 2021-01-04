package connectfourcontroller;

import connectfourmodel.Game;

public class ConnectFourController extends TemplateController
{
    public ConnectFourController(Game g)
    {
        setGame(g);
    }

    @Override
    public void play()
    {
        // main: setCurrentPlayer(getGame().getPlayer(0));

        getCurrentPlayer().play();
        insertPiece();

        // playCoin dans vue va setCarteCourante & appeler play()

        if(getGame().winner() == null || getCurrentPlayer().getPieces().size() > 0)
        {
            setCurrentPlayer( getCurrentPlayer() == getGame().getPlayer(0) ? getGame().getPlayer(1) : getGame().getPlayer(0));
        }
        else
        {
            setGameEnded(true);
        }

        notifyObservers();
    }

    @Override
    public void insertPiece()
    {
        getGame().insertPiece(getCurrentPlayer().getPieces().get(0), getCurrentColumn());
    }
}
