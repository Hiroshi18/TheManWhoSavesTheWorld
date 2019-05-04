
import javax.swing.*;

/**
 * This is the medium enemy ship of the game. It is slower than X-Wing but it can take aim to its targets, so it shoots in multi directions. It also shoots twice at a shot.
 */
public class Solar extends EnemyUnit {
    // image
    final static ImageIcon SOLAR = new ImageIcon("images/Solar.gif");
    // Directions
    static final int LEFT = -1;
    static final int RIGHT = 1;
    // variables
    private int direction;
    private int horizontalSpeed;

    /**
     * Creates a new Shadow with the specified x-coordinate, direction and difficulty.
     * @param xCor X-coordinate.
     * @param direction Direction (LEFT or RIGHT).
     * @param difficulty Difficulty level.
     */
    public Solar(int xCor, int direction, int difficulty) {
        super(EnemyUnit.SOLAR, 100 * difficulty, difficulty, false);
        this.direction = direction;
        horizontalSpeed = difficulty;
        speed = 2;
        this.xCor = xCor;
        this.yCor = -100;
        spaceShip = SOLAR;
        imageHeight = 80;
        imageWidth = 80;
        radius = (imageHeight + imageWidth) / 4;
        angle = 3 * Math.PI / 2;
        FIRE_SPEED = 100 / difficulty;
        fire = 0;
    }

    /**
     * Moves Solar
     */
    public void move() {
        fire++;
        if (fire == FIRE_SPEED) {
            int x = (int)Math.round(xCor - 10 * Math.sin(angle) + (imageHeight / 2) * Math.cos(angle));
            int y = (int)Math.round(yCor - 10 * Math.cos(angle) - (imageHeight / 2) * Math.sin(angle));
            Laser l = new Laser(Weapon.ENEMY, angle, x, y, difficulty * 20);
            Game.ENGINE.addToManager(l);
            x = (int)Math.round(xCor + 10 * Math.sin(angle) + (imageHeight / 2) * Math.cos(angle));
            y = (int)Math.round(yCor + 10 * Math.cos(angle) - (imageHeight / 2) * Math.sin(angle));
            l = new Laser(Weapon.ENEMY, angle, x, y, difficulty * 20);
            Game.ENGINE.addToManager(l);
            fire = 0;
        }
        yCor += speed;
        if (xCor >= Game.WIDTH - imageWidth / 2)
            direction = LEFT;
        else if (xCor <= imageWidth / 2)
            direction = RIGHT;
        xCor += (direction * horizontalSpeed);
        if (yCor > Game.HEIGHT + imageHeight)
            destroyed = true;

    }

}
