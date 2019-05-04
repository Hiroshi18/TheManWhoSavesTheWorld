
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

/**
 * This panel appears when user pauses the game by clicking P key.
 */
public class PausePanel extends JPanel implements ActionListener {
    // variables
    private GameController controller;

    JButton save;
    JButton options;
    JButton quit;
    JButton cont;
    /**
     * Creates a new PausePanel.
     * @param controller GameController to use.
     */
    public PausePanel(GameController controller) {
        this.controller = controller;
        setLayout(null);
        setOpaque(false);
        setLocation(120, 200);
        setSize(360, 200);
        setVisible(false);

        JLabel pauseMenu = new JLabel("Pause Menu", JLabel.CENTER);
        pauseMenu.setOpaque(false);
        pauseMenu.setForeground(Game.COLOR);
        pauseMenu.setFont(Game.TITLE);
        pauseMenu.setLocation(55, 25);
        pauseMenu.setSize(250, 30);
        add(pauseMenu);

        save = new JButton("Save Game");
        save.setForeground(Game.COLOR);
        save.setBackground(Color.black);
        save.setOpaque(false);
        save.setFont(Game.BUTTON);
        save.setLocation(20, 80);
        save.setSize(150, 30);
        save.addActionListener(this);
        add(save);

        options = new JButton("Options");
        options.setForeground(Game.COLOR);
        options.setBackground(Color.black);
        options.setOpaque(false);
        options.setFont(Game.BUTTON);
        options.setLocation(190, 80);
        options.setSize(150, 30);
        options.addActionListener(this);
        add(options);

        quit = new JButton("Quit");
        quit.setForeground(Game.COLOR);
        quit.setBackground(Color.black);
        quit.setOpaque(false);
        quit.setFont(Game.BUTTON);
        quit.setLocation(20, 135);
        quit.setSize(150, 30);
        quit.addActionListener(this);
        add(quit);

        cont = new JButton("Continue");
        cont.setForeground(Game.COLOR);
        cont.setBackground(Color.black);
        cont.setOpaque(false);
        cont.setFont(Game.BUTTON);
        cont.setLocation(190, 135);
        cont.setSize(150, 30);
        cont.addActionListener(this);
        add(cont);
    }
    /**
     * Invoked when an action occurs.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cont) {
            setVisible(false);
            Game.ENGINE.cont();
            controller.cont();
        }
        else if (e.getSource() == save) {
            controller.save();
        }

        else if (e.getSource() == options) {
            controller.displayOptions();
        }

        else if (e.getSource() == quit) {
            setVisible(false);
            Game.ENGINE.gameFinished();
        }
    }
}
