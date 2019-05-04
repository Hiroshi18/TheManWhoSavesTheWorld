
import javax.swing.*;
import java.awt.*;

/**
 * Meteor class is a child class of GameObject class and it is one of the objects in the game. It keeps the specific image file for the meteor. All images are placed on a matrix so it has the height and width.
 */
public class Meteor extends GameObject {
    // image
    final static ImageIcon METEOR = new ImageIcon("images/meteor.gif");
    /**
     * Creates a Meteor with the specified x-coordinate, y-coordinate and angle.
     * @param xCor X-coordinate.
     * @param yCor Y-coordinate.
     * @param angle Angle.
     */
    public Meteor(int xCor, int yCor, double angle) {
        super(GameObject.METEOR, -1);
        this.xCor = xCor;
        this.yCor = yCor;
        this.angle = angle;
        imageWidth = 80;
        imageHeight = 67;
        radius = (imageWidth + imageHeight) / 4;
        speed = 3;
        invulnerable = true;
    }
    /**
     * Displays Meteor.
     */
    public void display(Graphics g) {
        if (!destroyed)
            g.drawImage(METEOR.getImage(), xCor - imageWidth/2, yCor - imageHeight/2, imageWidth, imageHeight, null);
    }
    /**
     * Moves Meteor.
     */
    public void move() {
        yCor -= Math.round(speed * Math.sin(angle));
        xCor += Math.round(speed * Math.cos(angle));
        if (xCor < -imageWidth || yCor < -imageHeight || xCor > Game.WIDTH + imageWidth || yCor > Game.HEIGHT + imageHeight) {
            invulnerable = false;
            destroy();
        }
    }
    /**
     * Destroys Meteor.
     */
    public void destroy() {
        if (!invulnerable) {
            destroyed = true;
        }
    }

    public void damage(int amount) {

    }
}
