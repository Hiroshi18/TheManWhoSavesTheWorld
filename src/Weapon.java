/**
 * Weapon class is a child class of GameObject class and it is one of the objects in the game. Weapons are derived from this class and that’s why it keeps the weapon types.
 */
public abstract class Weapon extends GameObject {
    // Weapon Types
    final static int ENEMY = 0;
    final static int PLAYER = 1;
    // variables
    private boolean isRocket;

    protected int weaponType;
    protected int damageAmount;

    protected double angle;
    protected int finalSpeed;
    protected double speed;
    /**
     * Creates a new Weapon with the specified weapon type, damage amount, full health
     * @param weaponType Weapon type.
     * @param damageAmount Damage amount.
     * @param fullHealth Full health.
     * @param isRocket true if the Weapon is a Rocket.
     */
    public Weapon(int weaponType, int damageAmount, int fullHealth, boolean isRocket) {
        super(GameObject.WEAPON, fullHealth);
        this.weaponType = weaponType;
        this.damageAmount = damageAmount;
        this.isRocket = isRocket;
    }
    /**
     * Destroys Weapon.
     */
    public void destroy() {
        if (!invulnerable) {
            destroyed = true;
        }
    }
    /**
     * Returns damage amount.
     * @return Damage amount.
     */
    public int getDamageAmount() {
        if (!isDestroyed()) {
            return damageAmount;
        }
        else
            return 0;
    }
    /**
     * Returns weapon type.
     * @return Weapon type.
     */
    public int getWeaponType() {
        return weaponType;
    }

    /**
     * Returns whether the Weapon is a Rocket.
     * @return true if the Weapon is a Rocket.
     */
    public boolean isRocket() {
        return isRocket;
    }
}
