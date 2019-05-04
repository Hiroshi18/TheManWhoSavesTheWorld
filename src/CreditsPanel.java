
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This panel only gives information about developers.
 */
public class CreditsPanel extends JPanel implements ActionListener {

    // variables
    private GameController controller;

    JLabel credits;
    JLabel info;
    JLabel can;
    JLabel erdal;
    JLabel selim;
    JLabel sertac;
    JLabel berk;
    JButton back;

    /**
     * Creates a new CreditsPanel with the specified GameController.
     * @param controller GameController to use.
     */
    public CreditsPanel(GameController controller) {
        this.controller = controller;
        setLayout(null);
		setBackground(Color.black);
        setPreferredSize(new Dimension(600, 600));

        credits = new JLabel("Credits", JLabel.CENTER);
        credits.setForeground(Game.COLOR);
        credits.setFont(Game.TITLE);
        credits.setLocation(0, 50);
        credits.setSize(600, 30);
        add(credits);

        info = new JLabel("Game Design & Programming", JLabel.CENTER);
        info.setForeground(Game.COLOR);
        info.setFont(Game.SUB_TITLE);
        info.setLocation(0, 150);
        info.setSize(600, 30);
        add(info);

        final String members[] = { "Can Acar", "Selim Akdere", "Erdal Akbas", "Sertac Altikardesler", "Berk Atikoglu" };
        for (int i = 0; i < 5; i++) {
            JLabel member = new JLabel(members[i], JLabel.CENTER);
            member.setForeground(Game.COLOR);
            member.setFont(Game.TEXT);
            member.setLocation(0, 250 + i * 30);
            member.setSize(600, 25);
            add(member);
        }

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
