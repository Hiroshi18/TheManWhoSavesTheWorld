
import javax.swing.*;
import java.awt.*;

/**
 * DestroyedObject appears when a GameObject is destroyed. Its function is to generate graphical explosion.
 */
public class DestroyedObject extends GameObject {

    private ImageIcon destroyedObject;
    private int xCor;
    private int yCor;
    private int imageHeight;
    private int imageWidth;
    private int life;

    /**
     * Creates a new DestroyedObject with the specified x-coordinate, y-coordinate, image width and heigth.
     * @param xCor X-coordinate.
     * @param yCor Y-coordinate.
     * @param imageWidth Image width.
     * @param imageHeight Image height.
     */
    public DestroyedObject(int xCor, int yCor, int imageWidth, int imageHeight) {
        super(GameObject.DESTROYED, 0);
        destroyedObject = new ImageIcon("images/destroyed.gif");
        this.xCor = xCor;
        this.yCor = yCor;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        life = 0;
    }

    /**
     * Displays DestroyedObject.
     */
    public void display(Graphics g) {
        g.drawImage(destroyedObject.getImage(), xCor - imageWidth/2, yCor - imageHeight/2, imageWidth, imageHeight, null);
    }
    /**
     * Moves DestroyedObject.
     */
    public void move() {
        life++;
        yCor ++;
        if (life == 5)
            destroyed = true;
    }
    /**
     * Destroyes DestroyedObject.
     */
    void destroy() {
        destroyed = true;
    }
}
