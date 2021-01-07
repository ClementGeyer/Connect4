package connectfourmodel;

public class Coin {
    private final Color color;

    public Coin(Color color)
    {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return color.name();
    }
}
