
import javax.swing.*;

/**
 * Shadow Ship can take aim and shoot 3 at a time. It is a more powerful but slower ship than X-Wing and Solar Fighter. This ship appears at later phases of the game.
 */
public class Shadow extends EnemyUnit {
    // image
    final static ImageIcon SHADOW = new ImageIcon("images/Shadow.gif");
    // variables
    private int fireRocket;
    private boolean stronger;

    static final int LEFT = -1;
    static final int RIGHT = 1;

    private int direction;
    private int horizontalSpeed;
    private int time;
    /**
     * Creates a New Shadow with the specified x-coordinate, difficulty.
     * @param xCor X-coordinate.
     * @param difficulty Difficulty level.
     * @param stronger true if the Shadow is stronger.
     */
    public Shadow(int xCor, int difficulty, boolean stronger) {
        super(EnemyUnit.SHADOW, stronger ? 1500 : 400, difficulty, stronger);
        this.stronger = stronger;
        spaceShip = SHADOW;
        this.xCor = xCor;
        this.yCor = -100;
        imageHeight = 100;
        imageWidth = 100;
        radius = (imageHeight + imageWidth) / 4;
        angle = 3 * Math.PI / 2;
        speed = 2;
        fire = 0;
        time = 0;
        fireRocket = 300;
        horizontalSpeed = difficulty;
        direction = LEFT;
        FIRE_SPEED = 100 / difficulty;
    }
    /**
     * Moves Shadow.
     */
    public void move() {
        int playerX;
        int playerY;
        double destAngle;
        double aim;

        playerX = Game.ENGINE.getPlayer().getX();
        playerY = Game.ENGINE.getPlayer().getY();

        aim = (double)(yCor - playerY) / (double)(playerX - xCor);
        aim = Math.atan(Math.abs(aim));
        if (xCor > playerX && yCor > playerY)
            aim = Math.PI - aim;
        else if (xCor > playerX && yCor < playerY)
            aim = Math.PI + aim;
        else if (xCor < playerX && yCor < playerY)
            aim = 2 * Math.PI - aim;

        boolean fireLaser = false;
        if ((fire >= FIRE_SPEED || stronger) && yCor >= 100)
            destAngle = aim;
        else
            destAngle = 3 * Math.PI / 2;

        if (angle < 0) {
                angle = Math.PI * 2 + angle;
            }
        angle = angle % (Math.PI * 2);
        destAngle = destAngle % (Math.PI * 2);
        if (Math.abs(destAngle - angle) < Math.PI/180)
            angle = destAngle;
        else if (destAngle - angle > Math.PI/180) {
            if (destAngle - angle < Math.PI * 2 - (destAngle - angle))
                angle += Math.PI/180;
            else
                angle -= Math.PI/180;
            }
        else if (angle - destAngle > Math.PI/180) {
            if (angle - destAngle < Math.PI * 2 - (angle - destAngle))
                angle -= Math.PI/180;
            else
                angle += Math.PI/36;
        }

        if (fire > FIRE_SPEED && angle == destAngle)
            fireLaser = true;

        if (fireRocket == 400) {
            Game.ENGINE.addToManager(new Rocket(Weapon.ENEMY, 30 * difficulty, angle, xCor, yCor));
            if (!stronger)
                fireRocket = 0;
        }
        if (fireLaser) {
            int x = (int)Math.round(xCor - 45 * Math.sin(angle) + (15) * Math.cos(angle));
            int y = (int)Math.round(yCor - 45 * Math.cos(angle) - (15) * Math.sin(angle));
            Laser l = new Laser(Weapon.ENEMY, angle, x, y, difficulty * 20);
            Game.ENGINE.addToManager(l);
            x = (int)Math.round(xCor + (imageHeight / 2 + 5) * Math.cos(angle));
            y = (int)Math.round(yCor - (imageHeight / 2 + 5) * Math.sin(angle));
            l = new Laser(Weapon.ENEMY, angle, x, y, difficulty * 20);
            Game.ENGINE.addToManager(l);
            x = (int)Math.round(xCor + 45 * Math.sin(angle) + (15) * Math.cos(angle));
            y = (int)Math.round(yCor + 45 * Math.cos(angle) - (15) * Math.sin(angle));
            l = new Laser(Weapon.ENEMY, angle, x, y, difficulty * 20);
            Game.ENGINE.addToManager(l);
            fire = 0;
        }
        fire++;
        fireRocket++;

        if (stronger) {
            if (time == 1000) {
                yCor -= speed;
            }
            else if (yCor < 100) {
                yCor += speed;
            }
            else {
                if (xCor >= Game.WIDTH - imageWidth / 2)
                    direction = LEFT;
                else if (xCor <= imageWidth / 2)
                    direction = RIGHT;
                xCor += (direction * horizontalSpeed);
                time++;
            }
        }
        else
            yCor += speed;

        if (yCor > Game.HEIGHT + imageHeight || yCor <= -100)
            destroyed = true;
    }
}
