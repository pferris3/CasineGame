//imports and whatnot
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;

/**
 * This class creates a playable roulette game as a JPanel component
 * It implements an ActionListener attached to the Spin button
 * */

public class Roulette implements ActionListener {
    Random rng = new Random();
    int endValIndex;
    int tileTurns;
    double wager;
    boolean validEntry = true;
    boolean winner;
    String bet;
    WINTYPE wintype = WINTYPE.NUMBER;
    UserPane userPane;
    RouletteWheel rouletteWheel;

    //store winning values somehow
    int[] allTiles;
    int[] redTiles;
    int[] blackTiles;
    HashMap<Integer, ImageIcon> endings;

    //GUI variable & objects
    JPanel jPanel;
    JPanel roulettePanel;
    JPanel dataPanel;
    JLabel instructionLabel;
    JLabel redLabel;
    JLabel blackLabel;
    JLabel numberLabel;
    JLabel gap;
    JLabel wheelPic;
    JLabel betLabel;
    JTextField betField;
    JButton spinButton;


    /**
     * Constructor
     * instantiates arrays for roulette tiles and gets pictures of wheel results
     * @param up takes in a UserPanel object to interact with User variables and methods
     */
    public Roulette(UserPane up) {
        userPane = up;
        allTiles = new int[] {0, 32, 15, 19, 4, 21, 2, 25,17, 34, 6, 27, 13, 36, 11, 30, 8, 23, 10, 5, 24, 16, 33, 1, 20, 14, 31, 9, 22, 18, 29, 7, 28, 12, 35, 3, 26};
        redTiles = new int[] {32, 19, 21, 25, 34, 27, 36, 30, 23, 5, 16, 1, 14, 9, 18, 7, 12, 3};
        blackTiles = new int[] {15, 4, 2, 17, 6, 13, 11, 8, 10, 24, 33, 20, 31, 22, 29, 28, 35, 26};

        rouletteWheel = new RouletteWheel();
        endings = rouletteWheel.getEndings();
    }

    /**
     * creates GUI for roulette wheel
     * @return JPanel of roulette wheel and an input panel
     */
    public JPanel getJPanel(){
        jPanel = new JPanel();
        roulettePanel = new JPanel();

        dataPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(dataPanel, BoxLayout.PAGE_AXIS);
        dataPanel.setLayout(boxLayout);

        instructionLabel = new JLabel("Valid bets include:");
        redLabel = new JLabel("'red' (2:1 odds)");
        redLabel.setForeground(Color.RED);
        blackLabel = new JLabel("'black' (2:1 odds)");
        numberLabel = new JLabel("A number 0-36 (37:1 odds)");
        numberLabel.setForeground(Color.GREEN.darker());
        gap = new JLabel(" ");

        betLabel = new JLabel("Place a bet:");
        betField = new JTextField();

        spinButton = new JButton("Spin");
        spinButton.addActionListener(this);

        dataPanel.add(instructionLabel);
        dataPanel.add(redLabel);
        dataPanel.add(blackLabel);
        dataPanel.add(numberLabel);
        dataPanel.add(gap);
        dataPanel.add(betLabel);
        dataPanel.add(betField);
        dataPanel.add(spinButton);

        FlowLayout layout = new FlowLayout();
        jPanel.setLayout(layout);

        wheelPic = new JLabel(endings.get(37));

        jPanel.add(wheelPic);
        jPanel.add(dataPanel);

        jPanel.setVisible(true);
        return jPanel;
    }

    /**
     * get a bet from a JTextField
     * create JOptionPane if entry is invalid
     * adjust boolean to indicate validity of entry
     * */

    public void setBet(){
        bet = "";

        bet = betField.getText();

        if(bet.strip().equalsIgnoreCase("black")){
            wintype = WINTYPE.COLOR;
            validEntry = true;
        } else if (bet.strip().equalsIgnoreCase("red")){
            wintype = WINTYPE.COLOR;
            validEntry = true;
        } else if (Integer.parseInt(bet) >= 0 && Integer.parseInt(bet) <= 37){
            wintype = WINTYPE.NUMBER;
            validEntry = true;
        } else {
            JOptionPane.showMessageDialog(null, "Invalid entry!");
            validEntry = false;
        }

    }


    //stagnant wheel, ball moves in circular motion
    //start position is between two tiles, offset by (360/37)/2
    /**
     * calculate ending value of spin and set wheelPic to endVal
     * */
    public void spin(){
        tileTurns = rng.nextInt(0, 37);
        endValIndex = tileTurns;

        wheelPic.setIcon(endings.get(allTiles[endValIndex]));
    }


    /**
     * checks results of spin and pays accordingly
     * adjusts user balance
     * prints JOptionPane based on results
     * adjusts boolean to determine if user won
     */
    public void payout(){
        double winnings = 0;
        winner = false;


        if (wintype == WINTYPE.COLOR & bet.equals("red")) {
            for (int element : redTiles) {
                if (element == allTiles[endValIndex]) {

                    winnings = wager * 2;
                    userPane.setBalance(userPane.getBalance() + winnings);
                    winner = true;
                    JOptionPane.showMessageDialog(null, "Congratulations! Red wins!");
                }
            }
            if (winner == false){
                userPane.setBalance(userPane.getBalance()-wager);
                JOptionPane.showMessageDialog(null, "Sorry, better luck next time!");
            }
        }

        if (wintype == WINTYPE.COLOR & bet.equals("black")) {
            for (int element : blackTiles) {
                if (element == allTiles[endValIndex]) {

                    winnings = wager * 2;
                    userPane.setBalance(userPane.getBalance() + winnings);
                    winner = true;
                    JOptionPane.showMessageDialog(null, "Congratulations! Black wins!");
                }
            }
            if (winner == false){
                userPane.setBalance(userPane.getBalance()-wager);
                JOptionPane.showMessageDialog(null, "Sorry, better luck next time!");
            }
        }


        if (wintype == WINTYPE.NUMBER){
            if (Integer.parseInt(bet) == allTiles[endValIndex]) {
                winnings = wager * 37;
                userPane.setBalance(userPane.getBalance()+winnings);
                winner = true;
                JOptionPane.showMessageDialog(null, "Congratulations! " + allTiles[endValIndex] + " wins!");
            } else {
                userPane.setBalance(userPane.getBalance()-wager);
                JOptionPane.showMessageDialog(null, "Sorry, better luck next time!");
            }
        }

    }

    /**
     * sets bet based on input from UserPane
     */
    public void setWager(){
        wager = userPane.getCurrentBet();
    }


    /**
     * Everytime button is clicked, the following happens
     * wager is updated
     * bet is updated
     * runs spin and payout method if bet entry is valid
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        setWager();

        setBet();

        if(validEntry==true){

            spin();

            payout();
        }

    }

    public enum WINTYPE{COLOR, NUMBER}
}
