
import javax.swing.*;
import java.awt.*;

/**
 * Laser is basic weapon used by both Player and EnemyUnits
 */
public class Laser extends Weapon {
    // variables
    ImageIcon blueLaser;
    ImageIcon redLaser;
    int imageHeight;
    int imageWidth;
    /**
     * Creates a new Laser with the specified weapon type, angle, x-coordinate, y-coordinate and damage rate.
     * @param weaponType Type of the Laser.
     * @param angle Angle.
     * @param xCor X-coordinate.
     * @param yCor Y-coordinate.
     * @param damageRate Amaount of damage that Laser gives.
     */
    public Laser(int weaponType, double angle, int xCor, int yCor, int damageRate) {
        super(weaponType, damageRate, -1, false);
        this.angle = angle;
        this.xCor = xCor;
        this.yCor = yCor;
        finalSpeed = 20;
        speed = 0;
        blueLaser = new ImageIcon("images/bluelaser.gif");
        redLaser = new ImageIcon("images/redlaser.gif");
        imageHeight = 12;
        imageWidth = 4;
        radius = imageHeight / 2;
    }
    /**
     * Creates a new Laser with the specified weapon type, angle, x-coordinate, y-coordinate.
     * @param angle Angle.
     * @param xCor X-coordinate.
     * @param yCor Y-coordinate.
     */
    public Laser(int weaponType, double angle, int xCor, int yCor) {
        super(weaponType, 20, -1, false);
        this.angle = angle;
        this.xCor = xCor;
        this.yCor = yCor;
        blueLaser = new ImageIcon("images/bluelaser.gif");
        redLaser = new ImageIcon("images/redlaser.gif");
        imageHeight = 12;
        imageWidth = 4;
        radius = imageHeight / 2;
    }
    /**
     * Displays Laser.
     */
    public void display(Graphics g) {
        if (!isDestroyed()) {

            Graphics2D g2d = (Graphics2D) g;
            g2d.rotate(Math.PI/2 - angle, xCor, yCor);
            if (weaponType == Weapon.PLAYER)
                g2d.drawImage(blueLaser.getImage(), xCor - imageWidth/2, yCor - imageHeight/2, imageWidth, imageHeight, null);
            else
                g2d.drawImage(redLaser.getImage(), xCor - imageWidth/2, yCor - imageHeight/2, imageWidth, imageHeight, null);
            g2d.rotate(- Math.PI/2 + angle , xCor, yCor);
        }
    }
    /**
     * Moves Laser.
     */
    public void move() {
        if (speed < finalSpeed)
            speed += 0.5;
        xCor += speed * Math.cos(angle);
        yCor -= speed * Math.sin(angle);
        if (xCor < 0 || yCor < 0 || xCor > Game.WIDTH -1 || yCor > Game.HEIGHT - 1)
            destroy();
    }
}
