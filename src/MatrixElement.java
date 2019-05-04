
import java.util.ArrayList;

/**
 * MatrixElement refers to a block in the Matrix. Detect collisions operation is performed in the MatrixElement. If any collision occurs then it calls the destroy method of the objects. It also holds the added GameObjects and it can find the closest enemy unit.
 */
public class MatrixElement {

    ArrayList list;
    /**
     * Creates a new MatrixElement.
     */
    public MatrixElement() {
        list = new ArrayList();
    }

    /**
     * Adds GameObject to the list and detects collusions.
     * @param object - GameObject to be added.
     */
    public void addAndCheck(GameObject object) {
        int type = object.getType();
        GameObject current;

        for (int i = 0; i < list.size(); i++) {
            current = (GameObject)list.get(i);
            int curType = current.getType();

            // Meteor crashes another GameObject
            if (type == GameObject.METEOR && curType != GameObject.BONUS) {
                current.destroy();
            }
            else if (curType == GameObject.METEOR && type != GameObject.BONUS) {
                object.destroy();
            }
            // Enemy rocket damaged by player weapon
            else if (curType == GameObject.WEAPON && type == GameObject.WEAPON) {
                Weapon curWep = (Weapon) current;
                Weapon wep = (Weapon) object;
                if (curWep.isRocket() && curWep.getWeaponType() == Weapon.ENEMY && wep.getWeaponType() == Weapon.PLAYER) {

                    ((Rocket)(curWep)).damage(wep.getDamageAmount());
                    wep.destroy();
                }
                else if (wep.isRocket() && wep.getWeaponType() == Weapon.ENEMY && curWep.getWeaponType() == Weapon.PLAYER) {
                    ((Rocket)(wep)).damage(curWep.getDamageAmount());
                    curWep.destroy();
                }
            }
            // two GameObjects crashes
            else if ((type == GameObject.ENEMY || type == GameObject.PLAYER || type == GameObject.MINE || type == GameObject.METEOR)
            && (curType == GameObject.ENEMY || curType == GameObject.PLAYER || curType == GameObject.MINE || curType ==GameObject.METEOR)) {
                object.destroy();
                current.destroy();
            }


            // Player mine, or an enemy unit gets damage
            else if ((type == GameObject.ENEMY || type == GameObject.PLAYER || type == GameObject.MINE) && curType == GameObject.WEAPON) {
                if (type == GameObject.PLAYER && ((Weapon)current).getWeaponType() == Weapon.ENEMY) {
                    ((Player)object).damage(((Weapon)current).getDamageAmount());
                    current.destroy();
                }
                else if ((type == GameObject.ENEMY || type == GameObject.MINE) && ((Weapon)current).getWeaponType() == Weapon.PLAYER) {
                    if (type == GameObject.ENEMY)
                        ((EnemyUnit)object).damage(((Weapon)current).getDamageAmount());
                    else
                        ((Mine)object).damage(((Weapon)current).getDamageAmount());
                    current.destroy();
                }

            }

            else if ((curType == GameObject.ENEMY || curType == GameObject.PLAYER || curType == GameObject.MINE) && type == GameObject.WEAPON) {
                if (curType == GameObject.PLAYER && ((Weapon)object).getWeaponType() == Weapon.ENEMY) {
                    ((Player)current).damage(((Weapon)object).getDamageAmount());
                    object.destroy();
                }
                else if ((curType == GameObject.ENEMY || curType == GameObject.MINE) && ((Weapon)object).getWeaponType() == Weapon.PLAYER) {
                    if (curType == GameObject.ENEMY)
                        ((EnemyUnit)current).damage(((Weapon)object).getDamageAmount());
                    else
                        ((Mine)current).damage(((Weapon)object).getDamageAmount());
                    object.destroy();
                }
            }

            // Player gets bonus
            if (curType == GameObject.PLAYER && type == GameObject.BONUS) {
                if (!object.isDestroyed()) {
                    ((Player)current).bonusReceived(((Bonus)object).getBonusType());
                    object.destroy();
                }
            }
            if (curType == GameObject.WEAPON && type == GameObject.WEAPON) {
                if (((Weapon)current).getDamageAmount() == -1 && ((Weapon)object).getWeaponType() == Weapon.ENEMY)
                    object.destroy();
                else if (((Weapon)object).getDamageAmount() == -1 && ((Weapon)current).getWeaponType() == Weapon.ENEMY)
                    current.destroy();
            }


        }
        list.add(object);
    }

    /**
     * Finds the closest target in the list and compares it with distance.
     * @param rocket Rocket which tries to find a target.
     * @param distance Distance to be compared.
     * @return Target Object.
     */
    public EnemyUnit findClosestEnemy(Rocket rocket, int distance) {
        EnemyUnit closest = null;
        for (int i = 0; i < list.size(); i++) {
            if (((GameObject)list.get(i)).getType() == GameObject.ENEMY) {
                EnemyUnit enemy = (EnemyUnit)list.get(i);
                int tempDist = (int)Math.sqrt(Math.pow(rocket.getX() - enemy.getX(),2) + Math.pow(rocket.getY() - enemy.getY(),2));
                if (distance == -1 || distance > tempDist) {
                    closest = enemy;
                }
            }
        }
        return closest;
    }
}
