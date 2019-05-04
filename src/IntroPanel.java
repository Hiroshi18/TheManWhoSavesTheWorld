
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This panel is the first panel which user interacts. It contains New Game, Load Game, Options, Credits, Help and Exit
 */
public class IntroPanel extends JPanel implements ActionListener {
    // variables
    private GameController controller;
    /**
     * Creates a new IntroPanel.
     * @param controller GameController to use.
     */
    public IntroPanel(GameController controller) {
        this.controller = controller;
        setLayout(null);
        setBackground(Color.black);

        JLabel title = new JLabel("The Man Who Saves the World", JLabel.CENTER);
        title.setLocation(0,50);
        title.setFont(Game.TITLE);
        title.setSize(600, 30);
        title.setForeground(Game.COLOR);
        add(title);

        final String buttonNames[] = {"New Game", "Load Game", "Options", "Credits", "Help", "Exit"};
        for (int i = 0; i < 6; i++) {
            JButton button = new JButton(buttonNames[i]);
            button.setSize(300, 50);
            button.setForeground(Game.COLOR);
            button.setBackground(Color.black);
            button.setFont(Game.BUTTON);
		    button.setLocation(150, 125 + i*75);
            button.addActionListener(this);
            add(button);
        }
        setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
    }
    /**
     * Invoked when an action occurs.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.getComponent(1)) {
            controller.displayNewGame();
        }
        else if (e.getSource() == this.getComponent(2)) {
            controller.displayLoad();
        }
        else if (e.getSource() == this.getComponent(3)) {
            controller.displayOptions();
        }
        else if (e.getSource() == this.getComponent(4)) {
            controller.displayCredits();
        }
        else if (e.getSource() == this.getComponent(5)) {
            controller.displayHelp();
        }
        else if (e.getSource() == this.getComponent(6)) {
            controller.exit();
        }
    }
}
