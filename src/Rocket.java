
import javax.swing.*;
import java.awt.*;

/**
 * Rockets are targetted. It can be used by both the Player and EnemyUnits.
 */
public class Rocket extends Weapon {

    final static ImageIcon ROCKET = new ImageIcon("images/missile.gif");
    private GameObject destination;
    private int start;
    /**
     * Creates a new Rocket with the specified weapon type, damage amount, angle, x-coordinate and y-coordinate.
     * @param weaponType Weapon type.
     * @param damageAmount Damage amount.
     * @param angle Angle.
     * @param xCor X-coordinate.
     * @param yCor Y-Coordinate.
     */
    public Rocket(int weaponType, int damageAmount, double angle, int xCor, int yCor) {
        super(weaponType, damageAmount, 10, true);
        this.angle = angle;
        this.xCor = xCor;
        this.yCor = yCor;
        imageHeight = 75;
        imageWidth = 10;
        if (weaponType == Weapon.PLAYER)
            speed = 10;
        else
            speed = 6;
        start = 0;
        radius = 5;
        destination = null;
    }
    /**
     * Displays Rocket.
     */
    public void display(Graphics g) {
        if (!destroyed) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.rotate(Math.PI/2 - angle , xCor, yCor);
            g.drawImage(ROCKET.getImage(), xCor - imageWidth/2, yCor - imageHeight/2 + 20, imageWidth, imageHeight, null);
            g2d.rotate(-Math.PI/2 + angle , xCor, yCor);
        }
    }
    /**
     * Moves Rocket.
     */
    public void move() {
        // if target is enemy
        if (weaponType == Weapon.PLAYER && (destination == null || destination.isDestroyed())) {
            destination = Game.ENGINE.getManager().findClosestEnemy(this);
        }
        // if target is player
        else if (weaponType == Weapon.ENEMY && (destination == null || destination.isDestroyed()))
            destination = Game.ENGINE.getPlayer();
        if (start < 5)
            start++;
        // aim rocket to destination
        if (destination != null && start == 5) {
            double destAngle = (double)(yCor - destination.getY()) / (double)(destination.getX()- xCor);
            destAngle = Math.atan(Math.abs(destAngle));
            if (xCor > destination.getX() && yCor > destination.getY())
                destAngle = Math.PI - destAngle;
            else if (xCor > destination.getX() && yCor < destination.getY())
                destAngle = Math.PI + destAngle;
            else if (xCor < destination.getX() && yCor < destination.getY())
                destAngle = 2 * Math.PI - destAngle;
            if (angle < 0) {
                angle = Math.PI * 2 + angle;
            }
            angle = angle % (Math.PI * 2);
            destAngle = destAngle % (Math.PI * 2);
            if (Math.abs(destAngle - angle) < Math.PI/36)
                angle = destAngle;
            else if (destAngle - angle > Math.PI/36) {
                if (destAngle - angle < Math.PI * 2 - (destAngle - angle))
                    angle += Math.PI/36;
                else
                    angle -= Math.PI/36;
            }
            else if (angle - destAngle > Math.PI/36) {
                if (angle - destAngle < Math.PI * 2 - (angle - destAngle))
                    angle -= Math.PI/36;
                else
                    angle += Math.PI/36;
            }



        }

        yCor -= Math.round(speed * Math.sin(angle));
        xCor += Math.round(speed * Math.cos(angle));
        if (xCor < -imageWidth || yCor < -imageHeight || xCor > Game.WIDTH + imageWidth || yCor > Game.HEIGHT + imageHeight) {
            destroyed = true;
        }
    }

    /**
     * Gives damage to the EnemyUnit by the specified amount.
     * @param amount Damage amount.
     */
    public void damage(int amount) {
        if (weaponType != Weapon.PLAYER) {
            health -= amount;
            if (health <= 0) {
                destroyed = true;
                Game.ENGINE.addToManager(new DestroyedObject(xCor, yCor, imageWidth, imageWidth));
            }
        }
    }


}
