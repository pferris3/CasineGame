//Only one slot machine is made, should I bother making it a Singleton? It's not like anyone else is messing with this.
//Consists of 3 reels (and a lever (button))
//has 3 "paylines": straight across and both diagonals
//gives a different payout depending on which it is
//may implement checking for specific faces for face-specific payouts

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Chris
 * This class represents a slot machine in a casino
 */
public class SlotMachine implements ActionListener {
    private Reel reel1 = new Reel();
    private Reel reel2 = new Reel();
    private Reel reel3 = new Reel();

    private ReelFaces face1;
    private ReelFaces face2;
    private ReelFaces face3;

    //UserPane here so we can access the methods from all our internal methods without needing to pass it
    private UserPane currentUser;

    //these are for the GUI
    JButton lever;
    JLabel reelLabel1;
    JLabel reelLabel2;
    JLabel reelLabel3;

    /**
     * Creates a slot machine.
     * @param givenUserPane Gives the UserPane from the main section so it can access the methods
     */
    public SlotMachine(UserPane givenUserPane){
        this.build();
        currentUser = givenUserPane;
    }

    /**
     * Fills the Reels of the slot machine with faces so they're not empty
     */
    public void build(){

        //fills the reels with faces
        try {
            reel1.Populate();
            reel2.Populate();
            reel3.Populate();
        } catch (IOException e) {
            System.out.println("Populating the reels failed.");
            e.printStackTrace();
        }
    }

    /**
     * Plays the slot machine.
     */
    public void spin(){
        //I may want to integrate multithreading right here so they can all do their spins at the same time (visually)
        //If only 1 thread is used, they might try to spin 1 at a time.
        double bet = currentUser.getCurrentBet();
        if (bet <= currentUser.getBalance()){
            //pass them their labels so they can update the images.
            reel1.Spin(reelLabel1);
            reel2.Spin(reelLabel2);
            reel3.Spin(reelLabel3);
            payout(getWinner(), bet);
            //System.out.println(reel1.getCurrentFace()); //uncomment this line when troubleshooting the display problem
        }else{
            JOptionPane.showMessageDialog(null, "That bet is more than your balance");
        }
    }

    /**
     * Checks the faces of the Reels for matches.
     * @return int value to represent which prize to give
     */
    public int getWinner(){
        //ways to win (each gives a different payout)
        //Straight across (jackpot)
        //diagonal up or diagonal down (minor payout)
        face1 = reel1.getCurrentFace();
        face2 = reel2.getCurrentFace();
        face3 = reel3.getCurrentFace();

        if (face1 == face2 && face1 == face3) {
            return 1;
        }else if(reel1.getUpFace()==face2 && reel1.getUpFace()==reel3.getDownFace()) {
            return 2;
        }else if(reel1.getDownFace()==face2 && reel1.getDownFace()==reel3.getUpFace()){
            return 2;
        }else{
            return 0;
        }
    }

    /**
     * Updates the players balance with their rewards (or losses) from their spin().
     * @param winnerResult Result of getWinner() method
     * @param bet current bet from the currentUser
     */
    public void payout(int winnerResult, double bet) {
        //alter the player balance from this method somehow?
        //return the double value of currency gained/lost, then have the top level thing that called it do the change?
        switch (winnerResult) { //prints were for testing without GUI
            case 1:
                //System.out.println("Paid out the jackpot!");
                JOptionPane.showMessageDialog(null, "You won the jackpot!");
                currentUser.setBalance(currentUser.getBalance()+(bet*2));
                break;
            case 2:
                //System.out.println("Paid out the minor prize!");
                JOptionPane.showMessageDialog(null, "You won a minor prize!");
                currentUser.setBalance(currentUser.getBalance()+bet);
                break;
            case 0:
                JOptionPane.showMessageDialog(null, "You lost, better luck next time!");
                currentUser.setBalance(currentUser.getBalance()-bet);
                //System.out.println("Removed money from your balance!");
                break;
        }
    }

    /**
     * creates the interface for the SlotMachine
     * @return the JPanel that hold the visible SlotMachine
     */
    public JPanel displaySlotMachine() {
        SpringLayout layout = new SpringLayout();
        JPanel slotMachinePanel = new JPanel();

        slotMachinePanel.setLayout(layout);


        try{
            BufferedImage wPic = ImageIO.read(this.getClass().getResource("Images/0_BAR_BLUE.png"));
            BufferedImage lPic = ImageIO.read(this.getClass().getResource("Images/leverUpTemp.png"));
            reelLabel1 = new JLabel(new ImageIcon(wPic));
            reelLabel2 = new JLabel(new ImageIcon(wPic));
            reelLabel3 = new JLabel(new ImageIcon(wPic));
            lever = new JButton(new ImageIcon(lPic));


            //constraints to fit the reels
            layout.putConstraint(SpringLayout.WEST, reelLabel1, 5, SpringLayout.WEST, slotMachinePanel);
            layout.putConstraint(SpringLayout.NORTH, reelLabel1, 50, SpringLayout. NORTH, slotMachinePanel);

            layout.putConstraint(SpringLayout.WEST, reelLabel2,174, SpringLayout.WEST, slotMachinePanel);
            layout.putConstraint(SpringLayout.NORTH, reelLabel2,50, SpringLayout.NORTH, slotMachinePanel);

            layout.putConstraint(SpringLayout.WEST, reelLabel3,343, SpringLayout.WEST, slotMachinePanel);
            layout.putConstraint(SpringLayout.NORTH, reelLabel3,50, SpringLayout.NORTH, slotMachinePanel);


            //constraints to fit the lever
            layout.putConstraint(SpringLayout.WEST, lever,512, SpringLayout.WEST, slotMachinePanel);
            layout.putConstraint(SpringLayout.NORTH, lever,150, SpringLayout.NORTH, slotMachinePanel);

            //slotMachinePanel.add(first);
            //slotMachinePanel.add(second);
            slotMachinePanel.add(reelLabel1);
            slotMachinePanel.add(reelLabel2);
            slotMachinePanel.add(reelLabel3);
            slotMachinePanel.add(lever);

            lever.addActionListener(this);

        }catch (Exception e){
            JLabel failure = new JLabel("Panel");
            slotMachinePanel.add(failure);
            System.out.println("Slots panel failed to load");
        }
        return slotMachinePanel;
    }

    /**
     * Listens for when the player wants to spin the slots.
     * @param e Whenever the "play" button is clicked.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object button = e.getSource();

        if(button == lever){
            if(currentUser.getCurrentBet()!=0){
                try{
                    //commented out the image change for the time being since I couldn't get it to change back in a way I liked.
                    //BufferedImage lPic = ImageIO.read(this.getClass().getResource("Images/leverDownTemp.png"));
                    //lever.setIcon(new ImageIcon(lPic));
                    //this works, I'm able to change the image on click
                    //give it a countdown and then update it back to being upright
                    this.spin();

                }catch (Exception x){
                    System.out.println("Something went wrong with the Lever");
                }
            }
        }
    }
}
