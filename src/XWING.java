import javax.swing.*;

/**
 * This ship is the most basic enemy ship of the game, and it can be destroyed easily. It has low damage rate and it is a fast ship compared to other ships in the game. It cannot take aim to its target, just shoots straight forward and one at a time. This ship takes place in all phases of the game.
 */
public class XWING extends EnemyUnit {
    // image
    final static ImageIcon XWING = new ImageIcon("images/XWING.gif");
    /**
     * Creates a new XWING with the specified x-coordinate and difficulty.
     * @param xCor X-coordinate.
     * @param difficulty Difficulty level.
     */
    public XWING(int xCor, int difficulty) {
        super (EnemyUnit.XWING, 50 * difficulty, difficulty, false);
        this.xCor = xCor;
        this.yCor = -100;
        spaceShip = XWING;
        imageHeight = 80;
        imageWidth = 74;
        radius = (imageHeight + imageWidth) / 4;
        angle = 3 * Math.PI / 2;
        FIRE_SPEED = 100 / difficulty;
        speed = 2;
        fire = 0;
    }
    /**
     * Moves XWING.
     */
    public void move() {
        fire++;
        if (fire == FIRE_SPEED) {
            int x = (int)Math.round(xCor + (imageHeight / 2) * Math.cos(angle));
            int y = (int)Math.round(yCor - (imageHeight / 2) * Math.sin(angle));
            Laser l = new Laser(Weapon.ENEMY, angle, x, y, difficulty * 20);
            Game.ENGINE.addToManager(l);
            fire = 0;
        }
        yCor += speed;
        if (yCor > Game.HEIGHT + imageHeight)
            destroyed = true;

    }
}
