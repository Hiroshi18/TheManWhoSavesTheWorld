
import javax.swing.*;
import java.awt.*;

/**
 * Mine class is a child class of GameObject class and it is one of the objects in the game. It keeps the specific image file for the mine. All images are placed on a matrix so it has the height and width.
 */
public class Mine extends GameObject {
    // image
    final static ImageIcon MINE = new ImageIcon("images/mine.gif");

    private double rotation = 0;
    /**
     * Creates a Mine with the specified x-coordinate, y-coordinate, angle and difficulty level.
     * @param xCor X-coordinate.
     * @param yCor Y-coordinate.
     * @param angle Angle.
     * @param difficulty Difficulty level.
     */
    public Mine (int xCor, int yCor, double angle, int difficulty) {
        super(GameObject.MINE, 75 * difficulty);
        this.xCor = xCor;
        this.yCor = yCor;
        this.angle = angle;
        imageWidth = 60;
        imageHeight = 60;
        radius = (imageWidth + imageHeight) / 4;
        speed = 4;
    }
    /**
     * Displays Mine.
     */
    public void display(Graphics g) {
        if (!destroyed) {
            Graphics2D g2d = (Graphics2D) g;
            rotation += Math.PI / 15;
            g2d.rotate(rotation, xCor, yCor);
            g.drawImage(MINE.getImage(), xCor - imageWidth/2, yCor - imageHeight/2, imageWidth, imageHeight, null);
            g2d.rotate(-rotation, xCor, yCor);
        }
    }
    /**
     * Moves Mine.
     */
    public void move() {
        yCor -= Math.round(speed * Math.sin(angle));
        xCor += Math.round(speed * Math.cos(angle));
        if (xCor < -imageWidth || yCor < -imageHeight || xCor > Game.WIDTH + imageWidth || yCor > Game.HEIGHT + imageHeight) {
            destroyed = true;
        }
    }
    /**
     * Destroys Mine.
     */
    public void destroy() {
        if (!destroyed) {
            if (sound) {
                Game.EXPLOSION.play();
            }
            Game.ENGINE.enemyDestroyed(0, xCor, yCor);
            Game.ENGINE.addToManager(new DestroyedObject(xCor, yCor, imageWidth, imageHeight));
            destroyed = true;
        }
        destroyed = true;
    }
    /**
     * Gives damage to the EnemyUnit by the specified amount
     * @param amount Damage amount
     */
    public void damage(int amount) {
        if (amount == -1)
            destroy();
        else {
            health -= amount;
            if (health <= 0) {
                destroy();
            }
        }
    }
}