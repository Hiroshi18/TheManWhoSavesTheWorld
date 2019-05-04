
import javax.swing.*;
import java.awt.*;
import java.applet.AudioClip;
import java.applet.Applet;
import java.io.File;
import java.net.MalformedURLException;

/**
 * Main class of the system
 */
public class Game {

    // Screen size
    final static int HEIGHT = 600;
    final static int WIDTH = 600;
    // Game fonts
    final static Font TITLE = new Font("Blade Runner Movie Font 2", Font.PLAIN, 30);
    final static Font SUB_TITLE = new Font("Desoto", Font.PLAIN, 25);
    final static Font BUTTON = new Font("Desoto", Font.PLAIN, 20);
    final static Font TEXT = new Font("Medusa", Font.PLAIN, 20);
    final static Font BONUS = new Font("Arial", Font.BOLD, 25);
    // Game colors
    final static Color COLOR = Color.white;
    // Game audio files
    static AudioClip THEME;
    static AudioClip FINAL;
    static AudioClip EXPLOSION;
    static AudioClip LASER;
    static AudioClip SPACESHIP;       

    // Game engine
    static GameEngine ENGINE;
    /**
     * Main method.
     */
    public static void main (String[] args) {
        try {
            THEME = Applet.newAudioClip(new File("sounds/The Man Who Sold the World.wav").toURL());
            FINAL = Applet.newAudioClip(new File("sounds/Imperial March.wav").toURL());
            EXPLOSION = Applet.newAudioClip(new File("sounds/Explosion.wav").toURL());
            LASER = Applet.newAudioClip(new File("sounds/Laser.wav").toURL());
            SPACESHIP = Applet.newAudioClip(new File("sounds/Spaceship.wav").toURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        // Create game engine
        ENGINE = new GameEngine();
        ENGINE.setController(new GameController());
    }
}
