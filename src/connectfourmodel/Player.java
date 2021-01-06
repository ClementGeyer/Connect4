package connectfourmodel;

import java.util.ArrayList;

public class Player {

    public void setCoinsCount(int coinsCount) {
        this.coinsCount = coinsCount;
    }

    private int coinsCount = 20;
    private String name;
    private Color color;

    public Player(String name, Color c) {
        this.color = c;
        this.name = name;
    }

    public void play(){
        coinsCount--;
    }

    public boolean gotCoins(){
        return this.getCoins() != 0;
    }

    public int getCoins() { return coinsCount; }

    public Color getColor() { return color; }

    public void setColor(Color color) { this.color = color; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
