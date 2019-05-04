import java.awt.event.*;
import java.util.Properties;
import java.util.Random;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.applet.AudioClip;
import javax.swing.*;

/**
 * GameEngine class is the core of the system. It controls most of the data flow. This class starts or stops the game. Basically it controls the main functions.
 */
public class GameEngine implements ActionListener, Serializable {

    // variables
    private GameController controller;
    private Player player;
    private Timer timer;
    private GameObjectManager manager;
    private Scenario scenario;
    private int score;
    private int scoreTemp;
    private int difficulty;
    private int damageRate;
    private int strength;
    private int speed;
    private int quality;
    private int createBonus;
    private int lifesLeft;
    private String name;
    private boolean music;
    private boolean sound;
    private boolean control;
    transient private AudioClip theme;
    private boolean playerWon;
    private boolean finalStep;

    /**
     * Creates a new GameEngine.
     */
    public GameEngine()
    {
        timer = new Timer(40, this);
        playerWon = false;
        finalStep = false;
    }
    /**
     * Returns the GameObjectManager of the GameEngine.
     * @return GameObjectManager Object.
     */
    public GameObjectManager getManager() {
        return manager;
    }
    /**
     * Loads the saved game.
     */
    public void load() {
        loadOptions();
        timer.start();
        controller.displayGameScreen();
    }
    /**
     * Starts a new game.
     */
    public void start() {
        player = new Player(damageRate, strength, speed);
        theme = Game.THEME;
        loadOptions();
        manager = new GameObjectManager(quality);
        scenario = new Scenario();
        lifesLeft = 3;
        score = 0;
        scoreTemp = 0;
        createBonus = 0;
        timer.start();
    }
    /**
     * Pauses the game.
     */

    public void pause() {
        timer.stop();
    }

    /**
     * Continues the game.
     */
    public void cont() {
        controller.setPaused(false);
        loadOptions();
        manager.setMatrixBlockSize(quality);
        timer.start();
    }

    /**
     * Prepares the final step for the battle with DeathStar.
     */
    public void finalStep() {
        if (music) {
            theme.stop();
            theme = Game.FINAL;
            theme.loop();
        }
        finalStep = true;
    }

    /**
     * Loads the options from file.
     */
    private void loadOptions() {
        try {
            if (finalStep)
                theme = Game.FINAL;
            else
                theme = Game.THEME;
            Properties prop = new Properties();
            FileInputStream in = new FileInputStream("settings/options.prop");
            prop.load(in);
            if (prop.getProperty("quality").equals("low"))
                quality = 20;
            else if (prop.getProperty("quality").equals("medium"))
                quality = 10;
            else if (prop.getProperty("quality").equals("high"))
                quality = 1;
            if (prop.getProperty("control").equals("key"))
                control = Player.KEYBOARD;
            else
                control = Player.KEY_MOUSE;
            player.setControl(control);
            if (prop.getProperty("sound", "false").equals("true")) {
                GameObject.setSound(true);
                sound = true;
            }
            else {
                GameObject.setSound(false);
                sound = false;
            }
            if (prop.getProperty("music", "false").equals("true"))
                music = true;
            else
                music = false;
            if (music)
                theme.loop();
            else
                theme.stop();
            if (sound)
                Game.SPACESHIP.loop();
            else
                Game.SPACESHIP.stop();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Performs game operations in each cycle
     */
    public void actionPerformed(ActionEvent e) {
        player.move();
        manager.move();
        scenario.move();
        manager.refresh();
        manager.addToMatrix();
        scoreTemp++;
        if (scoreTemp == 10) {
            score += difficulty * 1;
            scoreTemp = 0;
        }
        controller.repaint();
    }

    /**
     * Returns the Player Object.
     * @return Player Object.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Adds a GameObject to the GameObjectManager.
     * @param gameObject GameObject to be added to the GameObjectManager.
     */
    public void addToManager(GameObject gameObject) {
        manager.add(gameObject);
    }

    /**
     * This method is called by the Player when the Player is destroyed.
     */
    public void playerDestroyed() {
        lifesLeft--;
        if (lifesLeft == 0) {
            timer.stop();
            controller.repaint();
        }
        else {
            player = new Player(damageRate, strength, speed);
            player.setControl(control);
        }
    }

    /**
     * This method is called when an EnemyUnit is destroyed.
     * @param enemyType Type of the EnemyUnit.
     * @param xCor X-coordinate.
     * @param yCor Y-coordinate.
     */
    public void enemyDestroyed(int enemyType, int xCor, int yCor) {
        if (enemyType == EnemyUnit.DEATH_STAR) {
            timer.stop();
            playerWon = true;
            controller.repaint();
        }
        else {
            score += (enemyType + 1) * difficulty * 100;
            createBonus += enemyType + 1;
            if (createBonus >= 5) {
                Random generator = new Random();
                int bonusType;
                do {
                    bonusType = generator.nextInt(6);
                } while(bonusType == Bonus.STANDART_WEAPON && player.getStandartWeapon() == 3);
                createBonus -= 5;
                manager.add(new Bonus(bonusType, xCor, yCor));
            }
        }

    }
    /**
     * Returns whether the Player won the game.
     * @return true if player wins the game.
     */
    public boolean isPlayerWon() {
        return playerWon;
    }
    /**
     * Finishes the game.
     */
    public void gameFinished() {
        if (music) {
            Game.SPACESHIP.stop();
            theme.stop();
        }
        timer.stop();
        playerWon = false;
        finalStep = false;
        controller.setPaused(false);
        controller.displayIntro();
    }

    /**
     * Sets the difficulty level of the game.
     * @param difficulty Difficulty level.
     */
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Returns the difficulty level of the game.
     * @return Difficulty level.
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * Sets the specifications of the player's spaceship.
     * @param damageRate Damage rate.
     * @param strength Strength.
     * @param speed Speed.
     */
    public void setPlayerOptions(int damageRate, int strength, int speed) {
        this.damageRate = damageRate;
        this.strength = strength;
        this.speed = speed;
    }

    /**
     * Returns the score of the player.
     * @return Score of the player.
     */
    public int getScore() {
        return score;
    }
    /**
     * Returns the number of lifes left.
     * @return Number of lifes.
     */
    public int getLifesLeft() {
        return lifesLeft;
    }
    /**
     * Increases number of lifes ot the player by one.
     */
    public void addLife() {
        lifesLeft++;
    }

    /**
     * Sets the GameController of the GameEngine.
     * @param controller GameController to use.
     */
    public void setController(GameController controller) {
        this.controller = controller;
    }

    /**
     * Returns the Scenario of the GameEngine.
     * @return Scenario Object.
     */
    public Scenario getScenario() {
        return scenario;
    }
    /**
     * Sets the Scenario of the GameEngine.
     * @param scenario Scenario to use.
     */
    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    /**
     * Sets the Player.
     * @param player Player to use.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Returns the quality of the game.
     * @return Quality level.
     */
    public int getQuality() {
        return quality;
    }

    /**
     * Sets the GameObjectManager of the GameEngine.
     * @param manager GameObjectManager to use.
     */
    public void setManager(GameObjectManager manager) {
        this.manager = manager;
    }

    /**
     * Sets the name of the player.
     * @param name Name of the player.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Returns the name of the player.
     * @return Name of the player.
     */
    public String getName() {
        return name;
    }
    /**
     * Returns whether the game is in the final step.
     * @return true if the game is in the final step.
     */
    public boolean isFinalStep() {
        return finalStep;
    }

}
