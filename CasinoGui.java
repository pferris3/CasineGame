import javax.swing.*;
import java.awt.*;

// This class creates the window and adds the user pane and game panes to it.
public class CasinoGui extends JPanel {

    public CasinoGui() {

        // create the user pane
        UserPane up = new UserPane();
        JScrollPane userPane = up.getUserPane();

        // create tabbed game pane
        JTabbedPane gamePane = new JTabbedPane();

        // panel for slots
        SlotMachine slots = new SlotMachine(up);
        gamePane.addTab("Slots", slots.displaySlotMachine()); // replace game1 with a JPanel returned from your class

        // panel for roulette
        Roulette roulette = new Roulette(up);
        gamePane.addTab("Roulette", roulette.getJPanel()); // replace game2 with a JPanel returned from your class

        // create split pane with user and game panes
        JSplitPane sPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, userPane, gamePane);
        sPane.setOneTouchExpandable(false);
        sPane.setDividerLocation(150);

        userPane.setMinimumSize(new Dimension(150, 150));
        sPane.setPreferredSize(new Dimension(800, 600));

        JFrame frame = new JFrame("Casino Games");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(sPane);
        frame.setSize(800, 600);
        frame.setVisible(true);

    }

}