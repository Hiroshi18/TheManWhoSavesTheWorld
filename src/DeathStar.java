
import javax.swing.*;

/**
 * DeathStar is the strongest enemy spaceship in the game. It occurs in the end of the game.
 */
public class DeathStar extends EnemyUnit {

    // image
    final static ImageIcon DEATH_STAR = new ImageIcon("images/Death Star.gif");
    
    // variables
    private int sequence;
    private GameObject destination;
    private boolean start;

    /**
     * Creates a new DeathStart with the specified difficulty.
     * @param difficulty Difficulty level.
     */
    public DeathStar(int difficulty) {
        super(EnemyUnit.DEATH_STAR, 3000, difficulty, true);
        spaceShip = DEATH_STAR;
        xCor = 300;
        yCor = -100;
        imageHeight = 120;
        imageWidth = 171;
        radius = imageHeight / 2;
        angle = 3 * Math.PI / 2;
        speed = 2;
        sequence = 0;
        fire = 0;
        invulnerable = true;
        FIRE_SPEED = 5;
        start = false;
    }
    /**
     * Moves DeathStar.
     */
    public void move() {
        if (!start) {
            if (yCor == 200)
                start = true;
            yCor += speed;
        }
        else {
            sequence++;
        }
        // fire lasers in sequence
        if (sequence >= 100 && sequence < 280) {
            angle += Math.PI / 45;
            if (fire >= FIRE_SPEED) {
                int x = (int)Math.round(xCor - 39 * Math.sin(angle) + (imageHeight / 2 + 5) * Math.cos(angle));
                int y = (int)Math.round(yCor - 39 * Math.cos(angle) - (imageHeight / 2 + 5) * Math.sin(angle));
                Laser l = new Laser(Weapon.ENEMY, angle, x, y, difficulty * 20);
                Game.ENGINE.addToManager(l);
                x = (int)Math.round(xCor + 39 * Math.sin(angle) + (imageHeight / 2 + 5) * Math.cos(angle));
                y = (int)Math.round(yCor + 39 * Math.cos(angle) - (imageHeight / 2 + 5) * Math.sin(angle));
                l = new Laser(Weapon.ENEMY, angle, x, y, difficulty * 20);
                Game.ENGINE.addToManager(l);
                fire = 0;
            }
            fire++;
        }
        // follow the player
        else if (sequence > 300 && sequence < 402) {
            if (destination == null || destination.isDestroyed()) {
                destination = Game.ENGINE.getPlayer();
            }

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
                    angle += Math.PI/180;
                if (fire >= FIRE_SPEED * 3) {
                int x = (int)Math.round(xCor - 39 * Math.sin(angle) + (imageHeight / 2 + 5) * Math.cos(angle));
                int y = (int)Math.round(yCor - 39 * Math.cos(angle) - (imageHeight / 2 + 5) * Math.sin(angle));
                Laser l = new Laser(Weapon.ENEMY, angle, x, y, difficulty * 20);
                Game.ENGINE.addToManager(l);
                x = (int)Math.round(xCor + 39 * Math.sin(angle) + (imageHeight / 2 + 5) * Math.cos(angle));
                y = (int)Math.round(yCor + 39 * Math.cos(angle) - (imageHeight / 2 + 5) * Math.sin(angle));
                l = new Laser(Weapon.ENEMY, angle, x, y, difficulty * 20);
                Game.ENGINE.addToManager(l);
                fire = 0;
            }
            fire++;
            }
            yCor -= Math.round(speed * Math.sin(angle));
            xCor += Math.round(speed * Math.cos(angle));

        }
        // fire rockets
        else if ((sequence % 100) == 1) {
            int x = (int)Math.round(xCor - 55 * Math.sin(angle) + (imageHeight / 2 + 5) * Math.cos(angle));
            int y = (int)Math.round(yCor - 55 * Math.cos(angle) - (imageHeight / 2 + 5) * Math.sin(angle));
            Rocket r = new Rocket(Weapon.ENEMY, 30 * difficulty, angle, x, y);
            Game.ENGINE.addToManager(r);
            x = (int)Math.round(xCor + 55 * Math.sin(angle) + (imageHeight / 2 + 5) * Math.cos(angle));
            y = (int)Math.round(yCor + 55 * Math.cos(angle) - (imageHeight / 2 + 5) * Math.sin(angle));
            r = new Rocket(Weapon.ENEMY, 30 * difficulty, angle, x, y);
            Game.ENGINE.addToManager(r);
        }
        // fire mines
        else if (sequence % 100 == 60) {
            int x = (int)Math.round(xCor + (imageHeight / 2 + 40) * Math.cos(angle));
            int y = (int)Math.round(yCor - (imageHeight / 2 + 40) * Math.sin(angle));
            Mine m = new Mine(x, y, angle, difficulty);
            Game.ENGINE.addToManager(m);
            x = (int)Math.round(xCor - (imageHeight / 2 + 40) * Math.cos(angle));
            y = (int)Math.round(yCor + (imageHeight / 2 + 40) * Math.sin(angle));
            m = new Mine(x, y, angle + Math.PI, difficulty);
            Game.ENGINE.addToManager(m);
            x = (int)Math.round(xCor - (imageWidth / 2 + 30) * Math.sin(angle));
            y = (int)Math.round(yCor - (imageWidth / 2 + 30) * Math.cos(angle));
            m = new Mine(x, y, Math.PI / 2 + angle, difficulty);
            Game.ENGINE.addToManager(m);
            x = (int)Math.round(xCor + (imageWidth / 2 + 30) * Math.sin(angle));
            y = (int)Math.round(yCor + (imageWidth / 2 + 30) * Math.cos(angle));
            m = new Mine(x, y, angle - Math.PI / 2, difficulty);
            Game.ENGINE.addToManager(m);

        }
        // rotate towards the player and fire
        else if (start) {
            if (destination == null || destination.isDestroyed()) {
                destination = Game.ENGINE.getPlayer();
            }

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
                    angle += Math.PI/180;
                if (fire >= FIRE_SPEED * 3) {
                int x = (int)Math.round(xCor - 39 * Math.sin(angle) + (imageHeight / 2 + 5) * Math.cos(angle));
                int y = (int)Math.round(yCor - 39 * Math.cos(angle) - (imageHeight / 2 + 5) * Math.sin(angle));
                Laser l = new Laser(Weapon.ENEMY, angle, x, y, difficulty * 20);
                Game.ENGINE.addToManager(l);
                x = (int)Math.round(xCor + 39 * Math.sin(angle) + (imageHeight / 2 + 5) * Math.cos(angle));
                y = (int)Math.round(yCor + 39 * Math.cos(angle) - (imageHeight / 2 + 5) * Math.sin(angle));
                l = new Laser(Weapon.ENEMY, angle, x, y, difficulty * 20);
                Game.ENGINE.addToManager(l);
                fire = 0;
            }
            fire++;
            }
        }
        // reset sequence
        if (sequence == 600)
            sequence = 0;

    }
}
