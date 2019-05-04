
import java.awt.*;

/**
 * LineOfDeath is the most powerful weapon in the Game. It is one of the SuperWeapons of the Player.
 */
public class LineOfDeath extends Weapon {
    // variables
    private boolean draw;
    private int left;
    private int right;

    /**
     * Creates a new LineOfDeath Object with the specified angle, x-coordinate, y-coordinate.
     * @param angle Angle.
     * @param xCor X-coordinate.
     * @param yCor Y-coordinate.
     * @param left Left size of the LineOfDeath.
     * @param right Right size of the LineOfDeath.
     * @param draw true if the LineOfDeath is displayed.
     */
    public LineOfDeath(double angle, int xCor, int yCor, int left, int right, boolean draw) {
        this(angle, xCor, yCor, draw);
        this.left = left;
        this.right = right;
    }
    /**
     * Creates a new LineOfDeath Object with the specified angle, x-coordinate, y-coordinate.
     * @param angle Angle.
     * @param xCor X-coordinate.
     * @param yCor Y-coordinate.
     * @param draw true if the LineOfDeath is displayed.
     */
    public LineOfDeath(double angle, int xCor, int yCor, boolean draw) {
        super(Weapon.PLAYER, -1, -1, false);
        this.draw = draw;
        this.angle = angle;
        this.xCor = xCor;
        this.yCor = yCor;
        invulnerable = true;
        speed = 5;
        radius = 5;
    }
    /**
     * Displays LineOfDeath.
     */
    public void display(Graphics g) {
        if (!destroyed && draw) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.rotate(Math.PI/2 - angle, xCor, yCor);
            g.setColor(Color.blue);
            for (int i = 0; i < 10; i++)
                g.drawLine(xCor - 20 * left, yCor - i, xCor + 20 * right, yCor- i);
            g2d.rotate(- Math.PI/2 + angle , xCor, yCor);
        }
    }
    /**
     * Moves LineOfDeath.
     */
    public void move() {
        yCor -= Math.round(speed * Math.sin(angle));
        xCor += Math.round(speed * Math.cos(angle));
        if (xCor < 0 || yCor < 0 || xCor > Game.WIDTH || yCor > Game.HEIGHT) {
            invulnerable = false;   
            destroy();
        }
    }
}
