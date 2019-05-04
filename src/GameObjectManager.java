
import java.util.ArrayList;
import java.awt.*;
import java.io.Serializable;

/**
 * GameObjectManager class is like an array. It holds all the active GameObject in the game except the Player. GameEngine manages to interact with all the objects by the GameObjectManager.
 */
public class GameObjectManager implements Serializable {

    // variables
    private ArrayList objectList;
    transient private Matrix matrix;

    /**
     * Creates a new GameObjectManager.
     * @param blockSize Size of a block in the Matrix.
     */
    public GameObjectManager(int blockSize) {
        matrix = new Matrix(blockSize);
        objectList = new ArrayList();
    }

    /**
     * Changes the size of a block in the the Matirx.
     * @param blockSize Size of a block in the Matrix.
     */
    public void setMatrixBlockSize(int blockSize) {
        matrix = new Matrix(blockSize);
    }

    /**
     * Adds GameObject to list.
     * @param gameObject GameObject to be added.
     */
    public void add(GameObject gameObject) {
        objectList.add(gameObject);
    }

    /**
     * Adds GameObjects in the list to the Matrix.
     */
    public void addToMatrix() {
        matrix.clear();
        matrix.add(Game.ENGINE.getPlayer());
        for (int i = 0; i < objectList.size(); i++) {
            if (((GameObject) objectList.get(i)).getType() != GameObject.DESTROYED)
                matrix.add((GameObject) objectList.get(i));
        }
    }
    /**
     * Moves all GameObjects in the list.
     */
    public void move() {
        for (int i = 0; i < objectList.size(); i++) {
            ((GameObject)objectList.get(i)).move();
        }
    }

    /**
     * Removes destroyed GameObjects from the list.
     */
    public void refresh() {
        for (int i = 0; i < objectList.size(); i++)
            if (((GameObject)objectList.get(i)).isDestroyed()) {
                objectList.remove(i);
            }
    }
    /**
     * Display all GameObjects in the list.
     */
    public void display(Graphics g) {
        for (int i = objectList.size() - 1; i >= 0; i--) {
            ((GameObject)objectList.get(i)).display(g);
        }
    }
    /**
     * Returns target GameObject which is closest to the rocket.
     * @param rocket - Rocket which tries to find a target.
     * @return Target GameObject.
     */
    public EnemyUnit findClosestEnemy(Rocket rocket) {
        return matrix.findClosestEnemy(rocket);
    }

    /**
     * Return the list of the GameObjects.
     * @return List of the GameObjects.
     */
    public ArrayList getList() {
        return objectList;
    }


}
