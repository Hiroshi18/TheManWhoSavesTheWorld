import java.awt.*;
import java.io.Serializable;

/**
 * GameObject class is the main frame of all game objects and contains common properties of them. It has constructors that determine the type of the game object whether if it is player, enemy, meteor, mine, bonus or weapon. It also holds the coordinates of the objects on the matrix. All other game objects are derived from this class.
 */
public abstract class GameObject implements Serializable {
    // GameObject Types
    static final int PLAYER = 1;
    static final int ENEMY = 2;
    static final int WEAPON = 3;
    static final int BONUS = 4;
    static final int METEOR = 5;
    static final int MINE = 6;
    static final int DESTROYED = 7;

    // variables
    protected static boolean sound;
    protected int health;
    private int fullHealth;
    protected boolean destroyed;
    protected int xCor;
    protected int yCor;
    protected double angle;
    protected int radius;
    protected int speed;
    protected int imageHeight;
    protected int imageWidth;
    protected boolean invulnerable;
    private int type;
    
    /**
     * Creates a GameObject with the specified type and full health.
     * @param type Type of the GameObject.
     * @param fullHealth Full health.
     */
    public GameObject(int type, int fullHealth) {
        this.type = type;
        this.fullHealth = fullHealth;
        health = fullHealth;
        destroyed = false;
    }

    /**
     * Returns the full health of the GameObject.
     * @return Full health of the GameObject.
     */
    public int getFullHealth() {
        return fullHealth;
    }
    /**
     * Returns current health of the GameObject.
     * @return Current health of the GameObject.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Returns X-coordinate of the GameObject.
     * @return X-coordinate of the GameObject.
     */
    public int getX() {
        return xCor;
    }
    /**
     * Returns Y-coordinate of the GameObject.
     * @return Y-coordinate of the GameObject.
     */
    public int getY() {
        return yCor;
    }
    /**
     * Returns radius of the GameObject.
     * @return Radius of the GameObject.
     */
    public int getRadius() {
        return radius;
    }
    /**
     * Returns type of the GameObject.
     * @return Type of the GameObject.
     */
    public int getType() {
        return type;
    }
    /**
     * Returns true if the GameObject is destroyed.
     * @return true if the GameObject is destroyed.
     */
    public boolean isDestroyed() {
        return destroyed;
    }
    /**
     * Sets whether the sound is on.
     * @param sound if true, enable sound.
     */
    public static void setSound(boolean sound) {
        GameObject.sound = sound;
    }

    /**
     * Displays GameObject.
     */
    abstract public void display(Graphics g);
    /**
     * Moves GameObject.
     */
    abstract public void move();
    /**
     * Destroyes GameObject.
     */
    abstract void destroy();
}
