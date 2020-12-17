package connectfourview;

import connectfourmodel.Player;

import javax.swing.*;
import java.awt.*;

public class PlayerView extends JFrame {

    //private ConnectFourController controller;

    private Player player;
    private Player playerTwo;
    private JPanel grid;

    public PlayerView(/*ConnectFourController c,*/ Player p, Player p2){
        super("Connect4");
        //this.controller = c;
        this.player = p;
        this.playerTwo = p2;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        int width = 446, height = 306;
        this.setLocation(width, height);
        this.setSize(width, height);
        this.setVisible(true);

        JLabel gameInfos = new JLabel("Game Informations");
        gameInfos.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel playerName = new JLabel(this.player.getName());
        playerName.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel playerCoins = new JLabel(Integer.toString(this.player.getPieces().size()));
        playerCoins.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel pannelWest = new JPanel(new BorderLayout());
        pannelWest.add(playerName, BorderLayout.CENTER);
        pannelWest.add(playerCoins, BorderLayout.SOUTH);

        JLabel playerTwoName = new JLabel(this.playerTwo.getName());
        playerTwoName.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel playerTwoCoins = new JLabel(Integer.toString(this.playerTwo.getPieces().size()));
        playerTwoCoins.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel pannelEast = new JPanel(new BorderLayout());
        pannelEast.add(playerTwoName, BorderLayout.CENTER);
        pannelEast.add(playerTwoCoins, BorderLayout.SOUTH);

        JPanel pannelCenter = new JPanel(new BorderLayout());
        JPanel buttons = new JPanel(new GridLayout(1, 6));
        for(int i = 0; i< 6; i++){
            JButton btn = new JButton("Insert coin");
            buttons.add(btn);
        }
        pannelCenter.add(buttons, BorderLayout.NORTH);
        grid = new JPanel(new GridLayout(7,6));
        for(int i = 0; i<42;i++){
            JPanel box = new JPanel();
            box.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
            grid.add(box);
        }
        pannelCenter.add(grid, BorderLayout.CENTER);

        JPanel mainPannel = (JPanel) this.getContentPane();
        mainPannel.add(gameInfos, BorderLayout.NORTH);
        mainPannel.add(pannelWest, BorderLayout.WEST);
        mainPannel.add(pannelEast, BorderLayout.EAST);
        mainPannel.add(pannelCenter, BorderLayout.CENTER);
    }

    public void placeACoin(int column, int line, Player p){
        for(int i = 0; i<6; i++){
            for(int j = 0; j<7;j++){
                if(i == column && j == line){
                    JPanel box = (JPanel) grid.getComponent((line * 6) + column);
                    Color color;
                    if(p.getColor().toString().equals("RED"))
                        color = Color.RED;
                    else
                        color = Color.YELLOW;
                    box.setBorder(BorderFactory.createLineBorder(color,1));
                }
            }
        }
    }

    public static void main(String[] args){
        Player p = new Player("clement", connectfourmodel.Color.RED);
        Player p2 = new Player("gautier", connectfourmodel.Color.YELLOW);
        PlayerView pv = new PlayerView(p, p2);
    }
}
