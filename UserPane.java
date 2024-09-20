import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

// This class creates the user pane, which creates and manages users
public class UserPane implements ActionListener {

    private final JLabel nameLabel;
    private final JLabel balanceLabel;
    private final JButton changeButton;
    private final JButton newButton;
    private final JScrollPane userPane;
    private final JTextField bet;
    private final JButton setBet;
    private final JLabel betLabel;
    private double currentBet = 0;
    private double balance = 0;
    private String fileName;
    private String userName = "";

    // create the user pane
    public UserPane() {

        // create user scroll pane
        JPanel userPanel = new JPanel();
        nameLabel = new JLabel(userName);
        String stringBalance = String.format("Balance: $%.2f", balance);
        balanceLabel = new JLabel(stringBalance);
        changeButton = new JButton("Change User");
        changeButton.addActionListener(this);
        newButton = new JButton("   New User   ");
        newButton.addActionListener(this);
        bet = new JTextField();
        bet.setMaximumSize(new Dimension(110,30));
        setBet = new JButton("      Set bet      ");
        setBet.addActionListener(this);
        betLabel = new JLabel(String.format("Current bet: $%.2f", currentBet));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        balanceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        changeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        bet.setAlignmentX(Component.CENTER_ALIGNMENT);
        setBet.setAlignmentX(Component.CENTER_ALIGNMENT);
        betLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userPanel.add(Box.createRigidArea( new Dimension( 1 , 90 )));
        userPanel.add(nameLabel);
        userPanel.add(Box.createRigidArea( new Dimension( 1 , 15 )));
        userPanel.add(balanceLabel);
        userPanel.add(Box.createRigidArea( new Dimension( 1 , 15 )));
        userPanel.add(changeButton);
        userPanel.add(Box.createRigidArea( new Dimension( 1 , 15 )));
        userPanel.add(newButton);
        userPanel.add(Box.createRigidArea( new Dimension( 1 , 80 )));
        userPanel.add(bet);
        userPanel.add(Box.createRigidArea( new Dimension( 1 , 15 )));
        userPanel.add(setBet);
        userPanel.add(Box.createRigidArea( new Dimension( 1 , 15 )));
        userPanel.add(betLabel);
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
        userPane = new JScrollPane(userPanel);

    }

    // handles button presses
    public void actionPerformed(ActionEvent e) {

        Object button = e.getSource();
        // sets the bet amount
        if ( button == setBet ) {
            currentBet = Double.parseDouble(bet.getText());
            if ( currentBet > balance ) {
                currentBet = balance;
            }
            if (currentBet < 0 ) {
                currentBet = 0;
            }
            betLabel.setText(String.format("Current bet: $%.2f", currentBet));
            bet.setText("");
            // changes users
        } else if ( button == changeButton ) {
            try {
                userName = JOptionPane.showInputDialog("Enter the name of the user you would like to load.");
                loadUser();
                currentBet = 0;
                betLabel.setText(String.format("Current bet: $%.2f", currentBet));
            } catch ( IOException ioe ) {
                JOptionPane.showMessageDialog(null, "Unable to load user.");
            }
        } else if ( button == newButton ) {
            // creates new users
            try {
                userName = JOptionPane.showInputDialog("Enter your new username.");
                balance = 500;
                saveUser();
                currentBet = 0;
                betLabel.setText(String.format("Current bet: $%.2f", currentBet));
            } catch ( IOException ioe ) {
                JOptionPane.showMessageDialog(null, "Unable to create user.");
            }
            balanceLabel.setText(String.format("Balance: $%.2f", balance));
            nameLabel.setText(userName);
        } else {
            JOptionPane.showMessageDialog(null, "???");
        }

    }

    // returns the user pane
    public JScrollPane getUserPane() {

        return userPane;

    }

    // returns the current balance
    public double getBalance() {

        return balance;

    }

    // changes the current balance
    public void setBalance(double balance) {

        this.balance = balance;
        balanceLabel.setText(String.format("Balance: $%.2f", balance));
        try {
            saveUser();
        } catch ( IOException ioe ) {
            JOptionPane.showMessageDialog(null, "Could not save");
        }

    }

    // returns the current bet
    public double getCurrentBet() {

        return currentBet;

    }

    // save user info to a file
    public void saveUser() throws IOException {

        fileName = "Casino/CasinoUsers/"+userName+".save";

        FileOutputStream fw = new FileOutputStream(fileName);
        PrintWriter pw = new PrintWriter(fw);
        pw.println(userName);
        pw.println(balance);
        pw.close();

    }

    // loads user info from a file
    public void loadUser() throws IOException {

        fileName = "Casino/CasinoUsers/"+userName+".save";

        FileReader reader = new FileReader(fileName);
        Scanner scan = new Scanner(reader);
        userName = scan.nextLine();
        balance = Double.parseDouble(scan.nextLine());
        scan.close();
        balanceLabel.setText(String.format("Balance: $%.2f", balance));
        nameLabel.setText(userName);

    }

}
