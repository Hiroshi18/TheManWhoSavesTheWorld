
import javax.swing.*;
import java.awt.*;

/**
 * EnemyUnit class is a child class of GameObject class and it is one of the objects in the game. It keeps an image icon for space ships to change the image according to different space ships. The enemy ships are derived from this class and that’s why it keeps the enemy types.
 */
abstract public class EnemyUnit extends GameObject {
    // Enemy Types
    static final int XWING = 0;
    static final int SOLAR = 1;
    static final int SHADOW = 2;
    static final int DEATH_STAR = 3;
    
    // variables
    private boolean superWeaponProtect;
    private int protect;

    protected int FIRE_SPEED;
    protected int fire;
    protected int difficulty;

    protected int enemyType;
    protected ImageIcon spaceShip;

    /**
     * Creates an EnemyUnit with the specified enemy type, full health, difficulty and super weapon protection.
     * @param enemyType Type of the EnemyUnit.
     * @param fullHealth Full health.
     * @param difficulty Difficulty level.
     * @param superWeaponProtect true if the EnemyUnit is super weapon protectable.
     */
    public EnemyUnit(int enemyType, int fullHealth, int difficulty, boolean superWeaponProtect) {
        super(GameObject.ENEMY, fullHealth);
        this.superWeaponProtect = superWeaponProtect;
        this.enemyType = enemyType;
        this.difficulty = difficulty;
        protect = 0;

    }
    /**
     * Displays EnemyUnit.
     */
    public void display(Graphics g) {
        if (!isDestroyed()) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.rotate(Math.PI/2 - angle , xCor, yCor);
            g2d.drawImage(spaceShip.getImage(), xCor - imageWidth/2, yCor - imageHeight/2, imageWidth, imageHeight, null);
            g2d.rotate(- Math.PI/2 + angle, xCor, yCor);
        }
        if (protect > 0)
            protect--;

    }
    /**
     * Returns the enemy type.
     * @return Enemy Type.
     */
    public int getEnemyType() {
        return enemyType;
    }
    /**
     * Moves EnemyUnit.
     */
    abstract public void move();

    /**
     * Destroys EnemyUnit.
     */
    public void destroy() {
        if (!destroyed && !invulnerable) {
            if (sound) {
                Game.EXPLOSION.play();
            }
            Game.ENGINE.enemyDestroyed(getEnemyType(), xCor, yCor);
            Game.ENGINE.addToManager(new DestroyedObject(xCor, yCor, imageWidth, imageHeight));
            destroyed = true;
        }
    }
    /**
     * Gives damage to the EnemyUnit by the specified amount.
     * @param amount Damage amount.
     */
    public void damage(int amount) {
        if (amount == -1) {
            if (superWeaponProtect) {
                if (protect == 0) {
                    health -= 300;
                    if (health <= 0) {
                        destroy();
                    }
                }
                protect = 2;
            }
            else
                destroy();
        }
        else {
            health -= amount;
            if (health <= 0) {
                invulnerable = false;
                destroy();
            }
        }
    }
}
