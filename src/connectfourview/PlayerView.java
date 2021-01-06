package connectfourview;

import connectfourcontroller.ConnectFourController;
import connectfourmodel.Game;
import connectfourmodel.Player;
import observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlayerView extends JFrame implements Observer {

    private Player player;
    private Player playerTwo;
    private JPanel grid;

    private ArrayList<JButton> ALButtons = new ArrayList<>();
    private JButton currentButton;
    private JLabel gameInfos;
    private JLabel playerCoins;
    private JLabel playerTwoCoins;

    private ConnectFourController controller;

    public PlayerView(ConnectFourController c) {
        super("Connect4");
        this.controller = c;
        this.player = this.controller.getGame().getPlayer(0);
        this.playerTwo = this.controller.getGame().getPlayer(1);

        this.controller.addObserver(this);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        int width = 446, height = 306;
        this.setLocation(width, height);
        this.setSize(width, height);
        this.setVisible(true);

        gameInfos = new JLabel("Au tour de " + controller.getGame().getPlayer(0).getName());
        gameInfos.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel playerName = new JLabel(this.player.getName());
        playerName.setHorizontalAlignment(SwingConstants.CENTER);
        playerCoins = new JLabel(Integer.toString(this.player.getCoins()));
        playerCoins.setText(String.valueOf(this.player.getCoins()));
        playerCoins.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel pannelWest = new JPanel(new BorderLayout());
        if (this.player.getColor().toString().equals("RED")) {
            pannelWest.setBackground(Color.RED);
        } else {
            pannelWest.setBackground(Color.YELLOW);
        }
        pannelWest.add(playerName, BorderLayout.CENTER);
        pannelWest.add(playerCoins, BorderLayout.SOUTH);

        JLabel playerTwoName = new JLabel(this.playerTwo.getName());
        playerTwoName.setHorizontalAlignment(SwingConstants.CENTER);
        playerTwoCoins = new JLabel(Integer.toString(this.playerTwo.getCoins()));
        playerTwoCoins.setText(String.valueOf(this.playerTwo.getCoins()));
        playerTwoCoins.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel pannelEast = new JPanel(new BorderLayout());
        if (this.playerTwo.getColor().toString().equals("RED")) {
            pannelEast.setBackground(Color.RED);
        } else {
            pannelEast.setBackground(Color.YELLOW);
        }
        pannelEast.add(playerTwoName, BorderLayout.CENTER);
        pannelEast.add(playerTwoCoins, BorderLayout.SOUTH);

        JPanel pannelCenter = new JPanel(new BorderLayout());
        JPanel buttons = new JPanel(new GridLayout(1, 6));
        for (int i = 0; i < 7; i++) {
            JButton btn = new JButton("Insert coin");
            btn.setName(String.valueOf(i));
            buttons.add(btn);
            ALButtons.add(btn);
        }
        pannelCenter.add(buttons, BorderLayout.NORTH);
        grid = new JPanel(new GridLayout(6, 7));
        for (int i = 0; i < 42; i++) {
            JPanel box = new JPanel();
            box.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            JLabel txt = new JLabel(String.valueOf(i));
            box.add(txt);
            grid.add(box);
        }

        for (JButton btn : ALButtons) {
            btn.addActionListener(e -> {
                currentButton = btn;
                System.out.println("Button " + currentButton.getName());
                placeACoin(controller.getCurrentPlayer());
            });
        }

        pannelCenter.add(grid, BorderLayout.CENTER);

        JPanel mainPannel = (JPanel) this.getContentPane();
        mainPannel.add(gameInfos, BorderLayout.NORTH);
        mainPannel.add(pannelWest, BorderLayout.WEST);
        mainPannel.add(pannelEast, BorderLayout.EAST);
        mainPannel.add(pannelCenter, BorderLayout.CENTER);
    }

    public void placeACoin(Player p) {
        controller.setCurrentColumn(Integer.parseInt(currentButton.getName()));
        controller.play();
        JPanel box = (JPanel) grid.getComponent(((controller.getGame().getCurrentLine() * 6) + controller.getGame().getCurrentColumn()) - (controller.getGame().getNumberToSubtract()));
        Color color;
        if (p.getColor().toString().equals("RED"))
            color = Color.RED;
        else
            color = Color.YELLOW;
        box.setBackground(color);
        if(controller.getGame().isCurrentColumnFull()){
            currentButton.setEnabled(false);
        }
        gameInfos.setText("Au tour de " + controller.getCurrentPlayer().getName());
        //TODO bug affichage des coins
        if(controller.getCurrentPlayer() == controller.getGame().getPlayer(0))
            playerCoins.setText(String.valueOf(controller.getCurrentPlayer().getCoins()));
        else
            playerTwoCoins.setText(String.valueOf(controller.getCurrentPlayer().getCoins()));
    }

    public static void main(String[] args) {
        Game g = new Game("clement", "gautier");
        ConnectFourController c = new ConnectFourController(g);
        PlayerView pv = new PlayerView(c);

        // Premier joueur
        c.setCurrentPlayer(c.getGame().getPlayer(0));
    }

    @Override
    public void update() {
        //TODO dire au joueur que c'est a lui de jouer
    }
}
