
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.StringTokenizer;

/**
 * This panel allows player to see and load a previously saved game. After user chooses a saved game, user clicks load button, saved game data is delivered to necessary classes and game starts. Additional information about loading a game is given in the SaveGameManager explanation.
 */
public class LoadPanel extends JPanel implements ActionListener {

    // variables
    private GameController controller;

    JPanel gameList;
    JButton back;
    JButton delete;
    JButton load;

    private int currentGame;
    private File[] savedGames;
    /**
     * Creates a new LoadPanel.
     * @param controller GameController to use.
     */
    public LoadPanel(GameController controller) {
        this.controller = controller;
        setLayout(null);
		setBackground(Color.black);
        setPreferredSize(new Dimension(600, 600));

        currentGame = -1;

        JLabel loadGame = new JLabel("Load Game", JLabel.CENTER);
        loadGame.setForeground(Game.COLOR);
        loadGame.setFont(Game.TITLE);
        loadGame.setLocation(0, 50);
        loadGame.setSize(600, 30);
        add(loadGame);

        gameList = new JPanel();
        gameList.setLayout(null);
        gameList.setBackground(Color.black);

        JLabel format = new JLabel("Name - Date (MM/DD/YYYY) - Time (HH:MM:SS)", JLabel.CENTER);
        format.setSize(500, 30);
        format.setLocation(50, 95);
        format.setFont(Game.BUTTON);
        format.setForeground(Game.COLOR);
        add(format);

        ButtonGroup group = new ButtonGroup();

        File file = new File("settings/");
        savedGames = file.listFiles(new SavedGameFilter());
        int i;
        for (i = 0; i < savedGames.length; i++) {

            StringTokenizer tokenizer = new StringTokenizer(savedGames[i].getName(), "_");
            String name = tokenizer.nextToken();
            String date = tokenizer.nextToken();
            date = date.replace('.', '/');
            String time = tokenizer.nextToken();
            time = time.substring(0, time.length() - 4);
            time = time.replace('.', ':');

            JRadioButton savedGame = new JRadioButton((i+1) + " - " + name + " - " + date + " - " + time);
            savedGame.setLocation(50, i * 40);
            savedGame.setSize(400, 30);
            savedGame.setForeground(Color.red);
            savedGame.setBackground(Color.black);
            savedGame.setFont(Game.TEXT);
            savedGame.addActionListener(this);
            savedGame.setActionCommand(String.valueOf(i));

            group.add(savedGame);
            gameList.add(savedGame);
        }



        if (i == 0 ) {
            JLabel empty = new JLabel("Empty", JLabel.CENTER);
            empty.setLocation(50, 0);
            empty.setSize(400, 30);
            empty.setForeground(Color.red);
            empty.setBackground(Color.black);
            empty.setFont(Game.TEXT);
            gameList.add(empty);
        }

        gameList.setPreferredSize(new Dimension(450, i * 40));

        JScrollPane sp = new JScrollPane(gameList);
        sp.setForeground(Game.COLOR);
        sp.setWheelScrollingEnabled(true);
        sp.setLocation(50, 140);
        sp.setSize(500, 335);
        sp.setBorder(null);
        add(sp);

        back = new JButton("Back");
        back.setForeground(Game.COLOR);
        back.setBackground(Color.black);
        back.setFont(Game.BUTTON);
        back.setLocation(50, 500);
        back.setSize(150, 50);
        back.addActionListener(this);
        add(back);

        delete = new JButton("Delete");
        delete.setForeground(Game.COLOR);
        delete.setBackground(Color.black);
        delete.setFont(Game.BUTTON);
        delete.setLocation(225, 500);
        delete.setSize(150, 50);
        delete.addActionListener(this);
        delete.setVisible(false);
        add(delete);

        load = new JButton("Load");
        load.setForeground(Game.COLOR);
        load.setBackground(Color.black);
        load.setFont(Game.BUTTON);
        load.setLocation(400, 500);
        load.setSize(150, 50);
        load.addActionListener(this);
        load.setVisible(false);
        add(load);
    }
    /**
     * Invoked when an action occurs.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back)
            controller.displayIntro();
        else if (e.getSource() == delete) {
            savedGames[currentGame].delete();
            gameList.remove(currentGame);
            JLabel deleted = new JLabel("Deleted");
            deleted.setLocation(70, currentGame * 40);
            deleted.setSize(380, 30);
            deleted.setForeground(Color.red);
            deleted.setBackground(Color.black);
            deleted.setFont(Game.TEXT);
            gameList.add(deleted, currentGame);
            currentGame = -1;
            repaint();
        }
        else if (e.getSource() == load) {
            SaveLoadManager.load(controller, "settings/" + savedGames[currentGame].getName());
        }
        else {
            currentGame = Integer.valueOf(((JRadioButton)e.getSource()).getActionCommand()).intValue();
        }
        if (currentGame == -1) {
            load.setVisible(false);
            delete.setVisible(false);
        }
        else {
            load.setVisible(true);
            delete.setVisible(true);
        }

}

}
