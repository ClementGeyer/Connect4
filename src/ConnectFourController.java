public class ConnectFourController extends TemplateController
{
    public ConnectFourController(Game g)
    {
        setGame(g);
    }



    @Override
    public void play()
    {

    }

    @Override
    public void insertPiece(Piece p, int column) {
        getGame().insertPiece(p, column);
    }
}
