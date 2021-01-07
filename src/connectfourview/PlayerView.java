package connectfourview;

import connectfourcontroller.ConnectFourController;
import connectfourcontroller.TemplateController;
import connectfourmodel.FiveInRow;
import connectfourmodel.Game;
import connectfourmodel.Player;
import connectfourmodel.PopOut;
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

    private boolean popout = false;

    private ArrayList<JButton> ALButtonsInsert = new ArrayList<>();
    private ArrayList<JButton> ALButtonsPopOut = new ArrayList<>();
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
        int width = 900, height = 500;
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
        JPanel buttonsInsert = new JPanel(new GridLayout(1, 6));
        for (int i = 0; i < 7; i++) {
            JButton btn = new JButton("Insert coin");
            btn.setName(String.valueOf(i));
            buttonsInsert.add(btn);
            ALButtonsInsert.add(btn);
        }
        pannelCenter.add(buttonsInsert, BorderLayout.NORTH);
        grid = new JPanel(new GridLayout(6, 7));
        for (int i = 0; i < 42; i++) {
            JPanel box = new JPanel();
            box.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            JLabel txt = new JLabel(String.valueOf(i));
            box.add(txt);
            grid.add(box);
        }

        for (JButton btn : ALButtonsInsert) {
            btn.addActionListener(e -> {
                currentButton = btn;
                popout = false;
                placeACoin();
            });
        }

        if(controller.getGame().getType().equals("popout")){
            JPanel buttonsPopOut = new JPanel(new GridLayout(1, 6));
            for(int i = 0; i< 7;i++){
                JButton btn = new JButton("Pop out");
                btn.setName(String.valueOf(i));
                btn.setEnabled(false);
                buttonsPopOut.add(btn);
                ALButtonsPopOut.add(btn);
            }
            pannelCenter.add(buttonsPopOut, BorderLayout.SOUTH);

            for (JButton btn : ALButtonsPopOut) {
                btn.addActionListener(e -> {
                    currentButton = btn;
                    popout = true;
                    popOutACoin();
                });
            }
        }

        pannelCenter.add(grid, BorderLayout.CENTER);

        JPanel mainPannel = (JPanel) this.getContentPane();
        mainPannel.add(gameInfos, BorderLayout.NORTH);
        mainPannel.add(pannelWest, BorderLayout.WEST);
        mainPannel.add(pannelEast, BorderLayout.EAST);
        mainPannel.add(pannelCenter, BorderLayout.CENTER);
    }

    public void placeACoin() {
        controller.setCurrentColumn(Integer.parseInt(currentButton.getName()));
        controller.play();
    }

    public void popOutACoin()
    {
        controller.setCurrentColumn(Integer.parseInt(currentButton.getName()));
        controller.playPopOut();
    }

    @Override
    public void update() {
        if(controller.isGameEnded()){
            for(JButton btn : ALButtonsInsert)
                btn.setEnabled(false);
            gameInfos.setText(controller.getCurrentPlayer().getName() + " a gagné la partie");
        }
        else{
            if(popout){
                for(int i = 0; i<7;i++){
                    if(controller.getGame().getGrid()[controller.getGame().getGrid().length - 1][i] != null) {
                        if (controller.getGame().getGrid()[controller.getGame().getGrid().length - 1][i].getColor() != controller.getCurrentPlayer().getColor())
                            ALButtonsPopOut.get(i).setEnabled(false);
                    }
                    else
                        ALButtonsPopOut.get(i).setEnabled(false);
                }
                System.out.println(controller.getGame().getCurrentColumn());
                System.out.println(controller.getGame().getNumberToSubtract());
                System.out.println(35 + controller.getGame().getCurrentColumn() - controller.getGame().getNumberToSubtract());
                JPanel box = (JPanel) grid.getComponent(35 + controller.getGame().getCurrentColumn() - controller.getGame().getNumberToSubtract());
                box.setBackground(Color.WHITE);
            }
            else{
                JPanel box = (JPanel) grid.getComponent(((controller.getGame().getCurrentLine() * 6) + controller.getGame().getCurrentColumn()) - (controller.getGame().getNumberToSubtract()));
                Color color;
                if (controller.getCurrentPlayer().getColor().toString().equals("RED"))
                    color = Color.RED;
                else
                    color = Color.YELLOW;
                box.setBackground(color);
                if(controller.getGame().isCurrentColumnFull()){
                    currentButton.setEnabled(false);
                }
                if(controller.getGame().getType().equals("popout")){
                    for(int i = 0; i<7;i++){
                        if(controller.getGame().getGrid()[controller.getGame().getGrid().length - 1][i] == null || controller.getGame().getGrid()[controller.getGame().getGrid().length - 1][i].getColor() == controller.getCurrentPlayer().getColor())
                            ALButtonsPopOut.get(i).setEnabled(false);
                        else
                            ALButtonsPopOut.get(i).setEnabled(true);
                    }
                }
            }
            gameInfos.setText("Au tour de " + (controller.getCurrentPlayer() == controller.getGame().getPlayer(0) ? controller.getGame().getPlayer(1).getName() : controller.getGame().getPlayer(0).getName()));
            playerCoins.setText(String.valueOf(controller.getGame().getPlayer(0).getCoins()));
            playerTwoCoins.setText(String.valueOf(controller.getGame().getPlayer(1).getCoins()));
        }
    }


    public static void main(String[] args) {
        connectFourPopOut();
        /*if(args.length > 1)
        {
            System.out.println("Usage: java connectFour base/fiveInRow/popOut");
            System.exit(1);
        }
        else if(args.length == 0 || args[0] == "base")
        {
            connectFourBase();
        }
        else if(args[0] == "fiveInRow")
        {
            connectFourFiveInRow();
        }
        else if (args[0] == "popOut")
        {

        }*/

    }

    static void connectFourBase()
    {
        Game g = new Game("clement", "gautier");
        ConnectFourController c = new ConnectFourController(g);
        PlayerView pv = new PlayerView(c);

        // Premier joueur
        c.setCurrentPlayer(c.getGame().getPlayer(0));

        //TODO Args pour select mode + si pas d'arg mode de base
    }

    static void connectFourFiveInRow()
    {
        //TODO passer la hauteur/longueur de la grille en parametre
        FiveInRow fg = new FiveInRow("Clément", "Gautier");
        ConnectFourController c = new ConnectFourController(fg);
        PlayerView pv = new PlayerView(c);

        // Premier joueur
        c.setCurrentPlayer(c.getGame().getPlayer(0));
    }

    static void connectFourPopOut()
    {
        PopOut po = new PopOut("Clément", "Gautier");
        ConnectFourController c = new ConnectFourController(po);
        PlayerView pv = new PlayerView(c);

        // Premier joueur
        c.setCurrentPlayer(c.getGame().getPlayer(0));
    }
}
