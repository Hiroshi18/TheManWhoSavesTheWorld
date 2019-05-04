
import javax.swing.*;
import java.awt.*;

/**
 * Player class stores the information about the player and fulfills player's responsibilities
 */
public class Player extends GameObject {
    // Control Options
    final static boolean KEY_MOUSE = true;
    final static boolean KEYBOARD = false;
    // images
    private ImageIcon rocketIcon;
    private ImageIcon lineIcon;
    // variables
    private GameEngine engine;
    private ImageIcon spaceShip;
    private Point cursor;
    private int numberOfLasers;
    private double angle;
    private int fireSpeed;
    private boolean fired;
    private boolean stepLeft;
    private boolean stepRight;
    private boolean forward;
    private boolean backward;
    private boolean fire;
    private boolean superFire;
    private boolean turnRight;
    private boolean turnLeft;
    private int damageRate;
    private int strength;
    private int shield;
    private boolean control;
    private int invulnerableTime;
    private int lineOfDeath;
    private int rockets;
    /**
     * Creates a new Player with the specified damage rate, strength and speed.
     * @param damageRate Damage rate of the Player.
     * @param strength Strength of the Player.
     * @param speed Speed of the Player.
     */
    public Player(int damageRate, int strength, int speed) {
        super(GameObject.PLAYER, 100 * strength);
        engine = Game.ENGINE;
        this.damageRate = damageRate;
        this.strength = strength;
        this.speed = 2 * speed;
        spaceShip = new ImageIcon("images/Kafa1500.gif");
        rocketIcon = new ImageIcon("images/rocketIcon.gif");
        lineIcon = new ImageIcon("images/lineIcon.gif");
        imageHeight = 60;
        imageWidth = 60;
        radius = (imageHeight + imageWidth) / 4;
        xCor = 300;
        yCor = 500;
        shield = 0;
        lineOfDeath = 1;
        invulnerable = true;
        invulnerableTime = 30;
        fireSpeed = 0;
        fired = false;
        stepRight = stepLeft = forward = backward = fire = turnRight = turnLeft = false;
        numberOfLasers = 1;
        cursor = new Point(0, 0);
        angle = Math.PI / 2;
        rockets = 0;
        lineOfDeath = 0;
    }
    /**
     * Displays Player.
     */
    public void display(Graphics g) {
        if (!isDestroyed()) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.rotate(Math.PI/2 - angle , xCor, yCor);
            g2d.drawImage(spaceShip.getImage(), xCor - imageWidth/2, yCor - imageHeight/2, imageWidth, imageHeight, null);
            g2d.rotate(- Math.PI/2 + angle , xCor, yCor);
            g2d.setColor(Color.red);
            // draw invulnerable circle
            if (invulnerable) {
                g2d.drawOval(xCor - radius - 4, yCor - radius - 4, 2 * radius + 8, 2 * radius + 8);
                g2d.drawOval(xCor - radius - 5, yCor - radius - 5, 2 * radius + 10, 2 * radius + 10);
                g2d.drawOval(xCor - radius - 6, yCor - radius - 6, 2 * radius + 12, 2 * radius + 12);
            }
            // draw health
            int healthSquares = (int)Math.round((double)health / (5 * strength));
            for (int i = 0; i < healthSquares; i++) {
                if (i < 4)
                    g.setColor(Color.red);
                else if (i < 9)
                    g.setColor(Color.yellow);
                else
                    g.setColor(Color.green);
                g.draw3DRect(Game.WIDTH - (i * 11) - 20, 10, 10, 10, true);
            }
            // draw shield
            if (shield > 0) {
                int shieldSquares = (int)Math.round((double)shield / (10));
                g.setColor(Color.DARK_GRAY);
                for (int i = 0; i < shieldSquares; i++) {
                    g.draw3DRect(Game.WIDTH - (i * 11) - 20, 21, 10, 10, true);
                }
            }
            g.setFont(Game.BUTTON);
            g.setColor(Game.COLOR);
            // draw number of rockets
            if (rockets > 0) {
                g.drawImage(rocketIcon.getImage(), 10, Game.HEIGHT - 30, null);
                g.drawString(String.valueOf(rockets), 48, Game.HEIGHT - 12);
            }
            // draw number of LineOfDeaths
            else if (lineOfDeath > 0) {
                g.drawImage(lineIcon.getImage(), 10, Game.HEIGHT - 30, null);
                g.drawString(String.valueOf(lineOfDeath), 38, Game.HEIGHT - 12);
            }
        }
    }
    /**
     * Moves Player.
     */
    public void move() {
        if (invulnerable) {
            invulnerableTime--;
            if (invulnerableTime == 0) {
                invulnerable = false;
            }

        }
        // Precondition: Control is Keyboard + Mouse
        // rotate spaceship to the direction of cursor
        if (control == KEY_MOUSE) {
            angle = (double)(yCor - cursor.getY()) / (double)(cursor.getX()- xCor);
            angle = Math.atan(Math.abs(angle));
            if (xCor > cursor.getX() && yCor > cursor.getY())
                angle = Math.PI - angle;
            else if (xCor > cursor.getX() && yCor < cursor.getY())
                angle = Math.PI + angle;
            else if (xCor < cursor.getX() && yCor < cursor.getY())
                angle = 2 * Math.PI - angle;
        }
        // Precondition: Control is Keyboard
        // rotate spaceship to the left or right
        else {
            if (turnLeft) {
                angle += Math.PI / 30;

            }
            else if (turnRight) {
                angle -= Math.PI / 30;
            }
        }
        int tempXcor = xCor;
        int tempYcor = yCor;
        // move spaceship
        if (forward) {
            yCor -= Math.round(speed * Math.sin(angle));
            xCor += Math.round(speed * Math.cos(angle));
        }
        else if (backward) {
            yCor += Math.round(speed * Math.sin(angle));
            xCor -= Math.round(speed * Math.cos(angle));
        }
        if (stepLeft) {
            yCor -= Math.round(speed * Math.sin(angle + Math.PI/2));
            xCor += Math.round(speed * Math.cos(angle + Math.PI/2));
        }
        else if (stepRight) {
            yCor += Math.round(speed * Math.sin(angle + Math.PI/2));
            xCor -= Math.round(speed * Math.cos(angle + Math.PI/2));
        }
        if (xCor <= imageWidth / 2 || xCor >= Game.WIDTH - imageWidth / 2)
            xCor = tempXcor;
        if (yCor <= imageHeight / 2 || yCor >= Game.HEIGHT - imageHeight / 2)
            yCor = tempYcor;
        // fire laser
        if (fire) {
            if (!fired) {
                if (sound)
                    Game.LASER.play();
                // # of lasers = 1
                if (numberOfLasers == 1) {
                    int x = (int)Math.round(xCor + (imageHeight / 2) * Math.cos(angle));
                    int y = (int)Math.round(yCor - (imageHeight / 2) * Math.sin(angle));
                    Laser l = new Laser(Weapon.PLAYER, angle, x, y, 15 * damageRate);
                    engine.addToManager(l);

                }
                // # of lasers = 2
                else if (numberOfLasers == 2) {
                    int x = (int)Math.round(xCor - 10 * Math.sin(angle) + (imageHeight / 2) * Math.cos(angle));
                    int y = (int)Math.round(yCor - 10 * Math.cos(angle) - (imageHeight / 2) * Math.sin(angle));
                    Laser l = new Laser(Weapon.PLAYER, angle, x, y, 15 * damageRate);
                    engine.addToManager(l);
                    x = (int)Math.round(xCor + 10 * Math.sin(angle) + (imageHeight / 2) * Math.cos(angle));
                    y = (int)Math.round(yCor + 10 * Math.cos(angle) - (imageHeight / 2) * Math.sin(angle));
                    l = new Laser(Weapon.PLAYER, angle, x, y, 15 * damageRate);
                    engine.addToManager(l);
                }
                // # of lasers = 3
                else if (numberOfLasers == 3) {
                    int x = (int)Math.round(xCor - 15 * Math.sin(angle) + (imageHeight / 2) * Math.cos(angle));
                    int y = (int)Math.round(yCor - 15 * Math.cos(angle) - (imageHeight / 2) * Math.sin(angle));
                    Laser l = new Laser(Weapon.PLAYER, angle, x, y, 15 * damageRate);
                    engine.addToManager(l);
                    x = (int)Math.round(xCor + (imageHeight / 2) * Math.cos(angle));
                    y = (int)Math.round(yCor - (imageHeight / 2) * Math.sin(angle));
                    l = new Laser(Weapon.PLAYER, angle, x, y, 15 * damageRate);
                    engine.addToManager(l);
                    x = (int)Math.round(xCor + 15 * Math.sin(angle) + (imageHeight / 2) * Math.cos(angle));
                    y = (int)Math.round(yCor + 15 * Math.cos(angle) - (imageHeight / 2) * Math.sin(angle));
                    l = new Laser(Weapon.PLAYER, angle, x, y, 15 * damageRate);
                    engine.addToManager(l);
                }
                fired = true;
            }
        }
        // fire super weapon
        if (superFire) {
            superFire = false;
            // fire LineOfDeath
            if (lineOfDeath > 0) {
                int left = 0;
                int x;
                int y;
                do {
                    left++;
                    x = (int)Math.round(xCor - (left * 20) * Math.sin(angle) + (imageHeight / 2) * Math.cos(angle));
                    y = (int)Math.round(yCor - (left * 20) * Math.cos(angle) - (imageHeight / 2) * Math.sin(angle));
                    engine.addToManager(new LineOfDeath(angle, x, y, false));
                } while (x > 0 && y > 0 && x < Game.HEIGHT && y < Game.HEIGHT);
                int right = 0;
                do {
                    right++;
                    x = (int)Math.round(xCor + (right * 20) * Math.sin(angle) + (imageHeight / 2) * Math.cos(angle));
                    y = (int)Math.round(yCor + (right * 20) * Math.cos(angle) - (imageHeight / 2) * Math.sin(angle));
                    engine.addToManager(new LineOfDeath(angle, x, y, false));
                } while (x > 0 && y > 0 && x < Game.HEIGHT && y < Game.HEIGHT);
                x = (int)Math.round(xCor + (imageHeight / 2) * Math.cos(angle));
                y = (int)Math.round(yCor - (imageHeight / 2) * Math.sin(angle));
                engine.addToManager(new LineOfDeath(angle, x, y, left, right, true));
                lineOfDeath--;

            }
            // fire Rocket
            else if (rockets > 0) {
                int x = (int)Math.round(xCor + (imageHeight / 2) * Math.cos(angle));
                int y = (int)Math.round(yCor - (imageHeight / 2) * Math.sin(angle));
                Rocket l = new Rocket(Weapon.PLAYER, -1, angle, x, y);
                engine.addToManager(l);
                rockets--;
            }
        }
        if (fired) {
            if (fireSpeed == 10) {
                fired = false;
                fireSpeed = 0;
            }
            else
                fireSpeed++;
        }

        if (xCor < - (imageWidth/2) || xCor > Game.WIDTH + (imageWidth/2) || yCor < - (imageHeight / 2) || yCor > Game.HEIGHT + (imageHeight / 2)) {
            invulnerable = false;
            destroy();
        }
    }
    /**
     * Sets the position of the cursor.
     * @param cursor Poisition of the cursor.
     */
    public void setCursor(Point cursor) {
        this.cursor = cursor;
    }
    /**
     * Destroys Player
     */
    public void destroy() {
        if (!destroyed && !invulnerable) {
            if (sound)
                Game.EXPLOSION.play();
            engine.getManager().add(new DestroyedObject(xCor, yCor, imageWidth, imageHeight));
            engine.playerDestroyed();
            destroyed = true;
        }
    }
    /**
     * Gives damage to the EnemyUnit by the specified amount.
     * @param amount Damage amount.
     */
    public void damage(int amount) {
        if (!invulnerable) {
            if (shield > 0) {
                if (shield >= amount)
                    shield -= amount;
                else {
                    health -= (amount - shield);
                    shield = 0;
                }
            }
            else
                health -= amount;
        }
        if (health <= 0)
            destroy();
    }
    /**
     * Sets whether the Player moves right.
     * @param stepRight true if the player moves right.
     */
    public void moveRight(boolean stepRight) {
        this.stepRight = stepRight;
    }
    /**
     * Sets whether the Player moves left.
     * @param stepLeft true if the player moves left.
     */
    public void moveLeft(boolean stepLeft) {
        this.stepLeft = stepLeft;
    }

    /**
     * Sets whether the Player moves forward.
     * @param forward true if the player moves forward.
     */
    public void moveForward(boolean forward) {
        this.forward = forward;
    }

    /**
     * Sets whether the Player moves backward.
     * @param backward true if the player moves backward.
     */
    public void moveBackward(boolean backward) {
        this.backward = backward;
    }

    /**
     * Sets whether the Player fires standart weapon when the control is both by keyboard and mouse.
     * @param fire true if the player presses left mouse button.
     */
    public void leftMouseButtonPressed(boolean fire) {
        if (control == KEY_MOUSE)
            this.fire = fire;
    }
    /**
     * Sets whether the Player fires super weapon when the control is both by keyboard and mouse.
     */
    public void rightMouseButtonPressed() {
        superFire = true;
    }
    /**
     * Sets whether Player fires standart weapon when the control is only by keyboard.
     * @param fire true if the player presses up key.
     */
    public void upKeyPressed(boolean fire) {
        if (control == KEYBOARD)
            this.fire = fire;
    }
    /**
     * Sets whether the Player fires super weapon when the control is only by keyboard.
     * @param superFire true if the player presses down key.
     */
    public void downKeyPressed(boolean superFire) {
        if (control == KEYBOARD)
            this.superFire = superFire;
    }
    /**
     * Rotates Player to the left if the control is only the keyboard.
     * @param turnLeft true if the player presses left key.
     */
    public void leftKeyPressed(boolean turnLeft) {
        this.turnLeft = turnLeft;

    }
    /**
     * Rotates Player to the right if the control is only the keyboard.
     * @param turnRight true if the player presses right key.
     */
    public void rightKeyPressed(boolean turnRight) {
        this.turnRight = turnRight;
    }
    public void setControl(boolean control) {
        this.control = control;
    }
    /**
     * Returns the standart weapon of the Player.
     * @return Number of lasers.
     */
    public int getStandartWeapon() {
        return numberOfLasers;
    }
    /**
     * Player receives a bonus with the specified type.
     * @param bonusType Type of the bonus.
     */
    public void bonusReceived(int bonusType) {
        switch (bonusType) {
            case Bonus.STANDART_WEAPON:
                if (numberOfLasers < 3)
                    numberOfLasers++;
                break;
            case Bonus.ROCKET:
                rockets += 3;
                lineOfDeath = 0;
                break;
            case Bonus.LINE_OF_DEATH:
                lineOfDeath++;
                rockets = 0;
                break;
            case Bonus.SHIELD:
                shield = 200;
                break;
            case Bonus.REPAIR:
                health = getFullHealth();
                break;
            case Bonus.LIFE:
                engine.addLife();
                break;
        }
    }
    /**
     * Sets whether the player is invulnerable.
     * @param invulnerable true if the player is invulnerable.
     */
    public void setInvulnerable(boolean invulnerable) {
        this.invulnerable = invulnerable;
    }
    /**
     * Returns whether the player is invulnerable.
     * @return true if the player is invulnerable.
     */
    public boolean isInvulnerable() {
        return invulnerable;
    }
}
