
import javax.swing.*;
import java.awt.*;

/**
 * GameController is a bridge between application layer and graphical user interface. The GameController delivers instructions coming from the GameEngine to the GameScreen panel. GameEngine repaint this panel in repaint method of GameController. Because GameController is a bridge with graphical user interface, it is created in the main method of GameClass to show user interface after program executed. GameController also controls the sequence of panels which will appear on the screen and it does these things with its methods. GameController class is implemented in order to reduce the coupling between application layer and user interface layer.
 */
public class GameController extends JFrame{

    // variables
    private Container contentPane;
    private GameScreen gameScreen;
    private boolean paused;

    /**
     * Creates a new GameController.
     */
    public GameController() {
        super("The Man Who Saves The World");
        this.setIconImage((new ImageIcon("images/icon.jpg")).getImage());
        contentPane = getContentPane();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(100, 100);
        setResizable(false);
        contentPane.add(new IntroPanel(this));
        gameScreen = new GameScreen(this);
        paused = false;
        pack();
        this.setVisible(true);
    }
    /**
     * Displays IntroPanel.
     */
    public void displayIntro() {
        setTitle("The Man Who Saves The World - Main Menu");
        gameScreen = new GameScreen(this);
        contentPane.getComponent(0).setVisible(false);
        contentPane.remove(0);
        contentPane.add(new IntroPanel(this));
    }

    /**
     * Displays NewGamePanel.
     */
    public void displayNewGame() {
        setTitle("The Man Who Saves The World - New Game");
        contentPane.getComponent(0).setVisible(false);
        contentPane.remove(0);
        contentPane.add(new NewGamePanel(this));
    }
    /**
     * Displays GameScreen.
     */
    public void displayGameScreen() {
        setTitle("The Man Who Saves The World - Game Screen");
        contentPane.getComponent(0).setVisible(false);
        contentPane.remove(0);
        gameScreen.setVisible(true);
        contentPane.add(gameScreen);
        gameScreen.requestFocus();
    }
    /**
     * Displays OptionsPanel.
     */
    public void displayOptions() {
        setTitle("The Man Who Saves The World - Options");
        contentPane.getComponent(0).setVisible(false);
        contentPane.remove(0);
        contentPane.add(new OptionsPanel(this));
    }
    /**
     * Displays CreditsPanel.
     */
    public void displayCredits() {
        setTitle("The Man Who Saves The World - Credits");
        contentPane.getComponent(0).setVisible(false);
        contentPane.remove(0);
        contentPane.add(new CreditsPanel(this));
    }
    /**
     * Displays HelpPanel.
     */
    public void displayHelp() {
        setTitle("The Man Who Saves The World - Help");
        contentPane.getComponent(0).setVisible(false);
        contentPane.remove(0);
        contentPane.add(new HelpPanel(this));
    }
    /**
     * Displays GameScreen if the game is paused.
     * Displays IntroPanel if the game is not started.
     */
    public void optionsDone() {
        if (paused) {
            setTitle("The Man Who Saves The World - Game Paused");
            contentPane.getComponent(0).setVisible(false);
            contentPane.remove(0);
            gameScreen.setVisible(true);
            contentPane.add(gameScreen);
        }
        else
            displayIntro();
    }
    /**
     * Called when the game continues.
     */
    public void cont() {
        setTitle("The Man Who Saves The World - Game Screen");
        gameScreen.requestFocus();
    }
    /**
     * Called when the game is saved.
     */
    public void save() {
        SaveLoadManager.save(this);

    }
    /**
     * Displays LoadPanel.
     */
    public void displayLoad() {
        setTitle("The Man Who Saves The World - Load Game Menu");
        contentPane.getComponent(0).setVisible(false);
        contentPane.remove(0);
        contentPane.add(new LoadPanel(this));
    }
    /**
     * Sets whether the game is paused.
     * @param paused true if the game is paused
     */
    public void setPaused(boolean paused) {
        if (paused) {
            setTitle("The Man Who Saves The World - Game Paused");
        }
        this.paused = paused;
    }
    /**
     * Exits the game.
     */
    public void exit() {
        setVisible(false);
        dispose();
    }
    /**
     * Repaints GameScren.
     */
    public void repaint() {
        gameScreen.repaint();
    }
    /**
     * Returns GameScreen Object.
     * @return GameScreen Object.
     */
    public GameScreen getGameScreen() {
        return gameScreen;
    }
    /**
     * Sets GameScreen Object.
     * @param gameScreen GameScreen Object to use.
     */
    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        gameScreen.setController(this);
    }
}
