
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

/**
 * Action goes on this panel; user plays the game over this screen. This screen can appear when user starts a new game, loads a game or continues a paused game. GameScreen gets the score, life’s left from the GameEngine and gets the special weapon information from Player class and paints them on the screen. Then sends the paint method to each of the GameObject in the GameObjectManager and to the Player.
 */
public class GameScreen extends JPanel implements KeyListener, MouseListener, MouseMotionListener {
    // background image size
    final int BACKGROUND_HEIGHT = 1533;
    final int BACKGROUND_WIDTH = 600;
    // image
    final static ImageIcon HEART = new ImageIcon("images/heart.gif");
    // variables
    private ImageIcon curBackground;
    private ImageIcon nextBackground;
    private int curLocation;
    private int nextLocation;
    private int imageCount;
    private PausePanel pause;
    private GameController controller;
    private int cheat;

    /**
     * Creates a new GameScreen.
     * @param controller GameController to use.
     */
    public GameScreen(GameController controller) {
        this.controller = controller;
        setLayout(null);
        setBackground(Color.white);
        setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        curBackground = new ImageIcon("images/background1.jpg");
        nextBackground = new ImageIcon("images/background2.jpg");
        curLocation = -BACKGROUND_HEIGHT + Game.HEIGHT;
        nextLocation = -2 * BACKGROUND_HEIGHT + Game.HEIGHT;
        imageCount = 2;
        pause = new PausePanel(controller);
        cheat = 0;
        add(pause);
    }
    /**
     * Draws everything except the GameObjects onto the screen.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setLayout(null);
        g.drawImage(curBackground.getImage(), 0, curLocation, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null);
        g.drawImage(nextBackground.getImage(), 0, nextLocation, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null);
        // draw first background
        if (curLocation == Game.HEIGHT) {
            imageCount++;
            curBackground = new ImageIcon("images/background" + imageCount + ".jpg");
            curLocation = -2 * BACKGROUND_HEIGHT + Game.HEIGHT;
        }
        // draw second background
        if (nextLocation == Game.HEIGHT) {
            imageCount++;
            nextBackground = new ImageIcon("images/background" + imageCount + ".jpg");
            nextLocation = -2 * BACKGROUND_HEIGHT + Game.HEIGHT;
        }
        if (!Game.ENGINE.isFinalStep()) {
            curLocation++;
            nextLocation++;
        }
        // draw objects in the manager
        Game.ENGINE.getManager().display(g);
        // draw player
        Game.ENGINE.getPlayer().display(g);
        g.setFont(Game.BUTTON);
        g.setColor(Game.COLOR);
        // draw score
        DecimalFormat fmt = new DecimalFormat("0,000,000");
        g.drawString(String.valueOf(fmt.format(Game.ENGINE.getScore())), 10, 30);
        g.drawImage(HEART.getImage(), 128, 12, null);
        g.drawString(String.valueOf(Game.ENGINE.getLifesLeft()), 150, 30);
        // draw game over message
        if (Game.ENGINE.getPlayer().isDestroyed()) {
            g.setColor(Color.red);
            g.setFont(Game.TITLE);
            g.drawString("Game Over", 210, 300);
            g.drawString("Press 'C' to continue", 110, 350);
        }
        // draw player wins message
        else if (Game.ENGINE.isPlayerWon()) {
            g.setColor(Color.red);
            g.setFont(Game.TITLE);
            g.drawString("YOU WIN!", 240, 250);
            g.drawString("Score: " + fmt.format(Game.ENGINE.getScore()), 160, 300);
            g.drawString("Press 'C' to continue", 110, 350);
        }

    }
    /**
     * Invoked when a key has been pressed.
     */
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode())
		{
			case KeyEvent.VK_W:
                Game.ENGINE.getPlayer().moveForward(true);
				break;
        	case KeyEvent.VK_S:
                Game.ENGINE.getPlayer().moveBackward(true);
				break;
    	    case KeyEvent.VK_A:
                Game.ENGINE.getPlayer().moveLeft(true);
				break;
	        case KeyEvent.VK_D:
                Game.ENGINE.getPlayer().moveRight(true);
                break;
            case KeyEvent.VK_UP:
                Game.ENGINE.getPlayer().upKeyPressed(true);
                break;
            case KeyEvent.VK_LEFT:
                Game.ENGINE.getPlayer().leftKeyPressed(true);
				break;
            case KeyEvent.VK_RIGHT:
                Game.ENGINE.getPlayer().rightKeyPressed(true);
				break;
            case KeyEvent.VK_P:
                Game.ENGINE.pause();
                pause.setVisible(true);
                pause.requestFocus();
                controller.setPaused(true);
                repaint();
                break;
            case KeyEvent.VK_C:
                if (Game.ENGINE.getPlayer().isDestroyed() || Game.ENGINE.isPlayerWon()) {
                    Game.ENGINE.gameFinished();
                }
                break;
            case KeyEvent.VK_F:
                cheat = 1;
                break;
            case KeyEvent.VK_I:
                if (cheat == 1)
                    cheat = 2;
                else
                    cheat = 0;
                break;
            case KeyEvent.VK_L:
                if (cheat == 2)
                    Game.ENGINE.getPlayer().setInvulnerable(!Game.ENGINE.getPlayer().isInvulnerable());
                cheat = 0;
            default:
                cheat = 0;
                break;
        }
    }
    /**
     * Invoked when a key has been released.
     */
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode())
		{
			case KeyEvent.VK_W:
                Game.ENGINE.getPlayer().moveForward(false);
				break;
        	case KeyEvent.VK_S:
                Game.ENGINE.getPlayer().moveBackward(false);
				break;
    	    case KeyEvent.VK_A:
                Game.ENGINE.getPlayer().moveLeft(false);
				break;
	        case KeyEvent.VK_D:
                Game.ENGINE.getPlayer().moveRight(false);
            case KeyEvent.VK_UP:
                Game.ENGINE.getPlayer().upKeyPressed(false);
				break;
            case KeyEvent.VK_DOWN:
                Game.ENGINE.getPlayer().downKeyPressed(true);
                break;
            case KeyEvent.VK_LEFT:
                Game.ENGINE.getPlayer().leftKeyPressed(false);
				break;
            case KeyEvent.VK_RIGHT:
                Game.ENGINE.getPlayer().rightKeyPressed(false);
				break;
        }
    }
    /**
     * Invoked when a key has been typed.
     */
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Invoked when a mouse button is pressed on a component and then dragged.
     */
    public void mouseDragged(MouseEvent e) {
        Game.ENGINE.getPlayer().setCursor(e.getPoint());

    }
    /**
     * Invoked when the mouse cursor has been moved onto a component but no buttons have been pushed.
     */
    public void mouseMoved(MouseEvent e) {
        Game.ENGINE.getPlayer().setCursor(e.getPoint());
    }
    /**
     * Invoked when the mouse button has been clicked (pressed and released) on a component.
     */
    public void mouseClicked(MouseEvent e) {
    }
    /**
     * Invoked when the mouse enters a component.
     */
    public void mouseEntered(MouseEvent e) {
    }
    /**
     * Invoked when the mouse exits a component.
     */
    public void mouseExited(MouseEvent e) {
    }
    /**
     * Invoked when a mouse button has been pressed on a component.
     */
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
            Game.ENGINE.getPlayer().leftMouseButtonPressed(true);

    }
    /**
     * Invoked when a mouse button has been released on a component.
     */
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
            Game.ENGINE.getPlayer().leftMouseButtonPressed(false);
        else if (e.getButton() == MouseEvent.BUTTON3)
            Game.ENGINE.getPlayer().rightMouseButtonPressed();
    }
    /**
     * Sets the GameController of the GameScreen.
     * @param controller GameController to use.
     */
    public void setController(GameController controller) {
        this.controller = controller;
        remove(pause);
        pause = new PausePanel(controller);
        add(pause);
    }

}

