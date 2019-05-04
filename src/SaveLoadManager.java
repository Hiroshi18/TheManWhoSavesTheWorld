
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.Timer;

/**
 * Function of this class is to save the current game or load an existing game.
 */
public class SaveLoadManager {
    /**
     * Saves the current game to a .sgf file
     * @param controller GameController used for saving process
     */
    public static void save(GameController controller) {
        FileOutputStream file = null;
        ObjectOutputStream outStream = null;
        Calendar calendar = Calendar.getInstance();
        String curDate = calendar.get(Calendar.MONTH) + "." + calendar.get(Calendar.DAY_OF_WEEK) + "." + calendar.get(Calendar.YEAR);
        String curTime = calendar.get(Calendar.HOUR_OF_DAY) + "." + calendar.get(Calendar.MINUTE) + "." + calendar.get(Calendar.SECOND);
        try {
            file = new FileOutputStream ("settings/" + Game.ENGINE.getName() + "_" + curDate + "_" + curTime + ".sgf");
            outStream = new ObjectOutputStream (file);
            GameEngine engine = Game.ENGINE;
            outStream.writeObject(engine.getPlayer());
            outStream.writeObject(engine.getScenario());
            outStream.writeObject(engine);
            outStream.writeObject(controller.getGameScreen());
            ArrayList list = engine.getManager().getList();
            for (int i = 0; i < list.size(); i++) {
                outStream.writeObject(list.get(i));
            }
            outStream.writeObject(null);
            outStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Loads a game from specified file
     * @param controller GameController used for loading process
     * @param fileName Name of the file which is loaded
     */
    public static void load(GameController controller, String fileName) {
        try {
            FileInputStream file = new FileInputStream (fileName);
	      	ObjectInputStream inStream = new ObjectInputStream (file);
            Player player = (Player) inStream.readObject();
            Scenario scenario = (Scenario) inStream.readObject();
            GameEngine engine = (GameEngine) inStream.readObject();
            GameScreen gameScreen = (GameScreen) inStream.readObject();
            controller.setGameScreen(gameScreen);
            engine.setPlayer(player);
            engine.setScenario(scenario);
            engine.setController(controller);
            GameObjectManager manager = new GameObjectManager(engine.getQuality());
            GameObject object = (GameObject) inStream.readObject();
            while (object != null) {
                manager.add(object);
                object = (GameObject) inStream.readObject();
            }
            inStream.close();
            engine.setManager(manager);
            Game.ENGINE = engine;
            engine.load();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
