
import java.io.FileFilter;
import java.io.File;

/**
 * It filters the saved game files in the game
 */
public class SavedGameFilter implements FileFilter {
    /**
     * Tests whether or not the specified abstract pathname should be included in a pathname list.
     * @return true if the File is accepted.
     */
    public boolean accept(File file) {
        String filename = file.getName();
        return filename.endsWith(".sgf");
    }
    public String getDescription() {
        return "Saved Game Files (*.sgf)";
    }
}
