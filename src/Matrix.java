
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * Matrix class divides screen into square blocks according to game quality. As quality increases, size of the blocks decrease. However, number of blocks increases and it reduces performance.
 */
public class Matrix {
    // variables
    private int blockSize;
    private int colSize;
    private int rowSize;
    private MatrixElement[][] matrix;
    /**
     * Creates a new Matrix with the specified block size.
     * @param blockSize Size of a block in the Matrix.
     */
    public Matrix(int blockSize) {
        this.blockSize = blockSize;
        rowSize = Game.HEIGHT / blockSize;
        colSize = Game.WIDTH / blockSize;
        matrix = new MatrixElement[colSize][rowSize];
        for (int i = 0; i < colSize; i++)
            for (int j = 0; j < rowSize; j++) {
                matrix[i][j] = new MatrixElement();
            }
    }
    /**
     * Locates the location of the GameObject and adds it to the MatrixElements in that location.
     * @param object GameObject to be added.
     */
    public void add(GameObject object) {
        int x = object.getX();
        int y = object.getY();
        int radius = object.getRadius();

        for (int i = (x / blockSize) -  radius / blockSize - 1; i <= (x / blockSize) +  radius / blockSize + 1; i++) {
            for (int j = (y / blockSize) -  radius / blockSize - 1; j <= (y / blockSize) +  radius / blockSize + 1; j++) {
                // add Weapons
                if (object.getType() == GameObject.WEAPON) {
                    if (Math.pow(((double)(i * blockSize)  - x),2) + Math.pow(((double)(j * blockSize) - y),2) <= radius * radius
                    | Math.pow(((double)(i * blockSize) + blockSize - x),2) + Math.pow(((double)(j * blockSize) - y),2) <= radius * radius
                    | Math.pow(((double)(i * blockSize) + blockSize - x),2) + Math.pow(((double)(j * blockSize) + blockSize - y),2) <= radius * radius
                    | Math.pow(((double)(i * blockSize) - x),2) + Math.pow(((double)(j * blockSize) + blockSize - y),2) <= radius * radius) {
                        try {
                            matrix[i][j].addAndCheck(object);
                        }
                        catch (ArrayIndexOutOfBoundsException e) {

                        }
                    }

                }
                // add other GameObjects
                else if (Math.pow(((double)(i * blockSize) + blockSize / 2 - x),2) + Math.pow(((double)(j * blockSize) + blockSize / 2 - y),2) <= radius * radius) {
                    try {
                        matrix[i][j].addAndCheck(object);
                    }
                    catch (ArrayIndexOutOfBoundsException e) {

                    }
                }
            }
        }
    }
    /**
     * Remove all GameObjects from the MatrixElements.
     */
    public void clear() {
        for (int i = 0; i < colSize; i++)
            for (int j = 0; j < rowSize; j++)
                    matrix[i][j] = new MatrixElement();
    }
    /**
     * Returns target GameObject which is closest to the rocket.
     * @param rocket - Rocket which tries to find a target.
     * @return Target GameObject.
     */
    public EnemyUnit findClosestEnemy(Rocket rocket) {
        EnemyUnit closest = null;
        int distance = -1;
        for (int i = 0; i < colSize; i++)
            for (int j = 0; j < rowSize; j++) {
                EnemyUnit enemy = matrix[i][j].findClosestEnemy(rocket, distance);
                if (enemy != null)
                    closest = enemy;
            }
        return closest;
    }
}
