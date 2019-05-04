import javax.swing.*;
import java.awt.*;

/**
 * Bonus class is a child class of GameObject class and it is one of the objects in the game. It draws the bonuses and it describe their types.
 */
public class Bonus extends GameObject {
    // images
    final static ImageIcon STANDART_ICON = new ImageIcon("images/laser.gif");
    final static ImageIcon LINE_ICON = new ImageIcon("images/line.gif");
    final static ImageIcon ROCKET_ICON = new ImageIcon("images/rocket.gif");
    final static ImageIcon SHIELD_ICON = new ImageIcon("images/shield.gif");
    final static ImageIcon REPAIR_ICON = new ImageIcon("images/repair.gif");
    final static ImageIcon LIFE_ICON = new ImageIcon("images/life.gif");

    // Bonus Types
    final static int STANDART_WEAPON = 0;
    final static int ROCKET = 1;
    final static int LINE_OF_DEATH = 2;
    final static int SHIELD = 3;
    final static int REPAIR = 4;
    final static int LIFE = 5;

    //variables
    private int bonusType;
    private int speed;

    /**
     * Creates a Bonus with specified type x-coordinate and y-coordinate.
     * @param bonusType Type of the bonus.
     * @param xCor X-coordinate.
     * @param yCor Y-coordinate.
     */
    public Bonus(int bonusType, int xCor, int yCor) {
        super(GameObject.BONUS, -1);
        this.bonusType = bonusType;
        this.xCor = xCor;
        this.yCor = yCor;
        radius = 20;
        speed = 1;
    }

    /**
     * Displays Bonus.
     */
    public void display(Graphics g) {
        if (!destroyed) {
            g.setFont(Game.BONUS);
            if (bonusType == STANDART_WEAPON) {
                g.drawImage(STANDART_ICON.getImage(), xCor - radius, yCor - radius, null);
                g.setColor(Color.blue);
                g.setFont(new Font("Arial", Font.PLAIN, 16));
                g.drawString("x" + (Game.ENGINE.getPlayer().getStandartWeapon() + 1), xCor + 8, yCor + 15);
            }
            else if (bonusType == ROCKET) {
                g.drawImage(ROCKET_ICON.getImage(), xCor - radius, yCor - radius, null);
            }
            else if (bonusType == LINE_OF_DEATH) {
                g.drawImage(LINE_ICON.getImage(), xCor - radius, yCor - radius, null);
            }
            else if (bonusType == SHIELD) {
                g.drawImage(SHIELD_ICON.getImage(), xCor - radius, yCor - radius, null);
            }
            else if (bonusType == REPAIR) {
                g.drawImage(REPAIR_ICON.getImage(), xCor - radius, yCor - radius, null);
            }
            else if (bonusType == LIFE) {
                g.drawImage(LIFE_ICON.getImage(), xCor - radius, yCor - radius, null);
            }
        }
    }
    /**
     * Moves Bonus.
     */
    public void move() {
        if (!Game.ENGINE.isFinalStep()) {
            yCor += speed;
            if (yCor > Game.HEIGHT + radius)
                destroy();
        }
    }
    /**
     * Destroys Bonus.
     */
    public void destroy() {
        destroyed = true;
    }
    /**
     * Returns type of the Bonus.
     * @return Type of the Bonus.
     */
    public int getBonusType() {
        return bonusType;
    }
}
