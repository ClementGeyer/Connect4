package connectfourview;

import connectfourcontroller.ConnectFourController;
import connectfourmodel.FiveInRow;
import connectfourmodel.Game;
import connectfourmodel.Player;
import connectfourmodel.PopOut;
import observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayerView extends JFrame implements Observer {

    private final JPanel grid;

    private boolean popout = false;

    private final ArrayList<JButton> ALButtonsInsert = new ArrayList<>();
    private final ArrayList<JButton> ALButtonsPopOut = new ArrayList<>();

    private JButton currentButton;
    private final JLabel gameInfos;
    private final JLabel playerCoins;
    private final JLabel playerTwoCoins;

    private final ConnectFourController controller;

    public PlayerView(ConnectFourController c) {

        super("Connect4");
        this.controller = c;

        //Instanciation des joueurs
        Player player = this.controller.getGame().getPlayer(0);
        Player playerTwo = this.controller.getGame().getPlayer(1);

        this.controller.addObserver(this);

        //Taille, position de la vue
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        int width = 900, height = 500;
        this.setLocation(width, height);
        this.setSize(width, height);
        this.setVisible(true);

        //Création du label affichant les informations en haut de l'écran
        gameInfos = new JLabel("Au tour de " + controller.getGame().getPlayer(0).getName());
        gameInfos.setHorizontalAlignment(SwingConstants.CENTER);

        //Création de l'affichage du nom du joueur 1 et de ses pions
        JLabel playerName = new JLabel(player.getName());
        playerName.setHorizontalAlignment(SwingConstants.CENTER);
        playerCoins = new JLabel(Integer.toString(player.getCoins()));
        playerCoins.setText(String.valueOf(player.getCoins()));
        playerCoins.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel pannelWest = new JPanel(new BorderLayout());
        if (player.getColor().toString().equals("RED"))
            pannelWest.setBackground(Color.RED);
        else
            pannelWest.setBackground(Color.YELLOW);
        pannelWest.add(playerName, BorderLayout.CENTER);
        pannelWest.add(playerCoins, BorderLayout.SOUTH);

        //Création de l'affichage du nom du joueur 2 et de ses pions
        JLabel playerTwoName = new JLabel(playerTwo.getName());
        playerTwoName.setHorizontalAlignment(SwingConstants.CENTER);
        playerTwoCoins = new JLabel(Integer.toString(playerTwo.getCoins()));
        playerTwoCoins.setText(String.valueOf(playerTwo.getCoins()));
        playerTwoCoins.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel pannelEast = new JPanel(new BorderLayout());
        if (playerTwo.getColor().toString().equals("RED"))
            pannelEast.setBackground(Color.RED);
        else
            pannelEast.setBackground(Color.YELLOW);
        pannelEast.add(playerTwoName, BorderLayout.CENTER);
        pannelEast.add(playerTwoCoins, BorderLayout.SOUTH);

        //Création des boutons pour insérer les pièces
        JPanel pannelCenter = new JPanel(new BorderLayout());
        JPanel buttonsInsert = new JPanel(new GridLayout(1, controller.getGame().getGrid()[0].length));
        for (int i = 0; i < controller.getGame().getGrid()[0].length; i++) {
            JButton btn = new JButton("Insert coin");
            btn.setName(String.valueOf(i));
            //Désactivation des boutons sur les côtés dans le mode de jeu Five in a row
            if(controller.getGame().getGrid()[0].length > 7 && (i == 0 || i == controller.getGame().getGrid()[0].length - 1)){
                btn.setEnabled(false);
            }
            buttonsInsert.add(btn);
            ALButtonsInsert.add(btn);
        }
        pannelCenter.add(buttonsInsert, BorderLayout.NORTH);

        //Création de la grille selon celle du mode de jeu
        grid = new JPanel(new GridLayout(6, controller.getGame().getGrid()[0].length));
        for (int i = 0; i < 6*controller.getGame().getGrid()[0].length; i++) {
            JPanel box = new JPanel();
            box.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            //Si le mode de jeu est un Five in a row
            if(controller.getGame().getType().equals("fiveinarow")){
                if(i == 0 || i == 17 || i == 18 || i == 35 || i == 36 || i == 53)
                    box.setBackground(Color.RED);
                if(i == 8 || i == 9 || i == 26 || i == 27 || i == 44 || i == 45)
                    box.setBackground(Color.YELLOW);
            }
            JLabel txt = new JLabel(String.valueOf(i));
            box.add(txt);
            grid.add(box);
        }

        //Ajout d'un évènement déclenchant la méthode 'placeACoin()' quand on clique sur les boutons insérer
        for (JButton btn : ALButtonsInsert) {
            btn.addActionListener(e -> {
                currentButton = btn;
                popout = false;
                placeACoin();
            });
        }

        //Ajout des boutons popout si le mode de jeu est Popout
        if(controller.getGame().getType().equals("popout")){
            JPanel buttonsPopOut = new JPanel(new GridLayout(1, 7));
            for(int i = 0; i< 7;i++){
                JButton btn = new JButton("Pop out");
                btn.setName(String.valueOf(i));
                btn.setEnabled(false);
                buttonsPopOut.add(btn);
                ALButtonsPopOut.add(btn);
            }
            pannelCenter.add(buttonsPopOut, BorderLayout.SOUTH);

            //Ajout d'un évènement déclenchant la méthode 'popOutACoin()' quand on clique sur les boutons popout
            for (JButton btn : ALButtonsPopOut) {
                btn.addActionListener(e -> {
                    currentButton = btn;
                    popout = true;
                    popOutACoin();
                });
            }
        }

        //Ajout des éléments sur la vue
        pannelCenter.add(grid, BorderLayout.CENTER);

        JPanel mainPannel = (JPanel) this.getContentPane();
        mainPannel.add(gameInfos, BorderLayout.NORTH);
        mainPannel.add(pannelWest, BorderLayout.WEST);
        mainPannel.add(pannelEast, BorderLayout.EAST);
        mainPannel.add(pannelCenter, BorderLayout.CENTER);
    }

    //Méthode permettant de jouer un coin
    public void placeACoin() {
        controller.setCurrentColumn(Integer.parseInt(currentButton.getName()));
        controller.play();
    }

    //Méthode permettant d'enlever un coin
    public void popOutACoin()
    {
        controller.setCurrentColumn(Integer.parseInt(currentButton.getName()));
        controller.playPopOut();
    }

    @Override
    public void update() {
        //Si la partie est finie on désactive les boutons insérer et popout
        if(controller.isGameEnded()){
            for(JButton btn : ALButtonsInsert)
                btn.setEnabled(false);
            if(controller.getGame().getType().equals("popout")){
                for(JButton btn : ALButtonsPopOut)
                    btn.setEnabled(false);
            }
            //Change le texte 'au tour de' à 'a gagné la partie'
            gameInfos.setText(controller.getCurrentPlayer().getName() + " a gagné la partie");
        }
        else{
            //Si l'utilisateur veut enlever un coin
            if(popout){
                //Permet de désactiver le bouton popup quand la colonne est vide
                for(int i = 0; i<7;i++){
                    if(controller.getGame().getGrid()[controller.getGame().getGrid().length - 1][i] != null) {
                        if (controller.getGame().getGrid()[controller.getGame().getGrid().length - 1][i].getColor() != controller.getCurrentPlayer().getColor())
                            ALButtonsPopOut.get(i).setEnabled(false);
                    }
                    else
                        ALButtonsPopOut.get(i).setEnabled(false);
                }

                //Comme on veut uniquement un case de la dernière ligne on ajoute 35
                int current_case = controller.getCurrentColumn() + 35;
                //Permet de récupérer la case correspondante
                JPanel box = (JPanel) grid.getComponent(current_case);
                //La case précédente est celle du dessus on soustrait donc 7 pour l'avoir
                int previous_case = current_case - 7;
                //TODO Bug : exception de child < 0 ne devrait pas se produire mais elle devrait venir de la condition du while
                while(previous_case > 0){
                    //A chaque tour dans la boucle on récupère la case de dessus
                    previous_case = current_case - 7;
                    //Si la case du haut de la colonne est rouge ou jaune (donc qu'elle est jouée)
                    if(grid.getComponent(controller.getCurrentColumn()).getBackground() == Color.RED || grid.getComponent(controller.getCurrentColumn()).getBackground() == Color.YELLOW){
                        //On la récupère et on la met en blanc
                        //TODO: le blanc n'est pas la couleur de base c'est du genre de gris clair mais nsm au pire
                        box = (JPanel) grid.getComponent(controller.getCurrentColumn());
                        box.setBackground(Color.WHITE);
                    }
                    else{
                        //Change la couleur des cases en mettant celle de la case de dessus à la place
                        box.setBackground(grid.getComponent(previous_case).getBackground());
                        box = (JPanel) grid.getComponent(previous_case);
                        current_case = previous_case;
                    }
                }
            }
            //Si l'utilisateur veut insérer un coin
            else{
                JPanel box;
                //Check si la game est un five in a row et fais les calculs selon le nombre de colonnes
                if(controller.getGame().getType().equals("fiveinarow"))
                    //Pour le calcul pour récupérer la case on multiplie par 9 le nombre de ligne ce qui permet de déterminer la ligne (ici 9 car c'est un five in a row qui a 9 colonnes) et on la ajoute le nombre de la colonne
                    box = (JPanel) grid.getComponent(controller.getCurrentColumn() + (9 * (controller.getGame().getCurrentLine() - 1)));
                else
                    box = (JPanel) grid.getComponent(controller.getCurrentColumn() + (7 * (controller.getGame().getCurrentLine() - 1)));

                Color color;
                //On applique la couleur correspondante a celle du joueur
                if (controller.getCurrentPlayer().getColor().toString().equals("RED"))
                    color = Color.RED;
                else
                    color = Color.YELLOW;
                box.setBackground(color);

                //Si la colonne est pleine, on désactive le bouton
                if(controller.getGame().isCurrentColumnFull()){
                    currentButton.setEnabled(false);
                }

                //Si le type de partie est popout, on check quels boutons popout doivent être activés en fonction du tout
                if(controller.getGame().getType().equals("popout")){
                    for(int i = 0; i<7;i++){
                        ALButtonsPopOut.get(i).setEnabled(controller.getGame().getGrid()[controller.getGame().getGrid().length - 1][i] != null && controller.getGame().getGrid()[controller.getGame().getGrid().length - 1][i].getColor() != controller.getCurrentPlayer().getColor());
                    }
                }
            }
            //Puis on définis les informations pour le tour suivant
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
