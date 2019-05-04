
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;

/**
 * This panel contains basic help documentation for the software.
 */
public class HelpPanel extends JPanel implements ActionListener {
    // variables
    private GameController controller;

    JButton back;
    /**
     * Creates a new HelpPanel.
     * @param controller GameController to be used.
     */
    public HelpPanel(GameController controller) {
        this.controller = controller;
        setLayout(null);
		setBackground(Color.black);
        setPreferredSize(new Dimension(600, 600));

        JLabel help = new JLabel("Help", JLabel.CENTER);
        help.setForeground(Game.COLOR);
        help.setFont(Game.TITLE);
        help.setLocation(0, 50);
        help.setSize(600, 30);
        add(help);

        JTextArea content = new JTextArea();
        content.setBackground(Color.black);
        content.setForeground(Game.COLOR);
        content.setLineWrap(true);

        FileReader fr;
        try {
            fr = new FileReader("settings/help.txt");
            BufferedReader inFile = new BufferedReader(fr);
            String line = inFile.readLine();
            while(line != null) {
                content.append("\n" + line);
                line = inFile.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JScrollPane sp = new JScrollPane(content);
        sp.setForeground(Game.COLOR);
        sp.setWheelScrollingEnabled(true);
        sp.setLocation(25, 100);
        sp.setSize(550, 375);
        sp.setBorder(null);
        add(sp);

        back = new JButton("Back");
        back.setForeground(Game.COLOR);
        back.setBackground(Color.black);
        back.setFont(Game.BUTTON);
        back.setLocation(50, 500);
        back.setSize(150, 50);
        back.addActionListener(this);
        add(back);
    }
    /**
     * Invoked when an action occurs.
     */
    public void actionPerformed(ActionEvent e) {
        controller.displayIntro();
    }
}
