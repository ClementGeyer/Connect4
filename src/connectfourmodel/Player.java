package connectfourmodel;

import java.util.ArrayList;

public class Player {

    public ArrayList<Piece> pieces = new ArrayList<>();
    public String name;
    public Color color;

    public Player(String name, Color c) {
        this.color = c;
        this.name = name;
        pieceInititalisation();
    }

    public void pieceInititalisation(){
        for(Piece p : getPieces()){
            p = new Piece(getColor());
        }
    }

    public void play(){
        this.getPieces().remove(0);
    }

    public boolean gotPieces(){
        return this.getPieces().size() != 0;
    }

    public ArrayList<Piece> getPieces() { return pieces; }

    public void setPieces(ArrayList<Piece> pieces) { this.pieces = pieces; }

    public Color getColor() { return color; }

    public void setColor(Color color) { this.color = color; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
