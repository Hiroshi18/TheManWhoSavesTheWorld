
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This panel will appear if the user clicks the new game button in the IntroPanel. From this panel user makes the adjustments for a new game like Player’s Name, difficulty selection and space ship customization. According to customization, parameters in the Player class updates.
 */

public class NewGamePanel extends JPanel implements ActionListener, AdjustmentListener, KeyListener {
    // variables
    private GameEngine engine;
    private GameController controller;
    private int maxSpecification;
    private int damageRate;
    private int strength;
    private int speed;

 	JLabel newGameLabel;
    JLabel enterNameLabel;
    JLabel shipLabel;
    JTextField nameField;
    JTextField damageRateField;
    JTextField strengthField;
    JTextField speedField;
    JLabel selectDifficultyLabel;
    JRadioButton easyRadio;
    JRadioButton mediumRadio;
    JRadioButton hardRadio;
    Scrollbar damageRateScroll;
    Scrollbar strengthScroll;
    Scrollbar speedScroll;
    JLabel damageRateLabel,strengthLabel,speedLabel;
    JButton back,start;
    /**
     * Creates a new NewGamePanel.
     * @param controller GameController to be used.
     */
    public NewGamePanel(GameController controller) {
        this.controller = controller;
        engine = Game.ENGINE;
        setLayout(null);
		setBackground(Color.black);
        setPreferredSize(new Dimension(600, 600));

        maxSpecification = 9;
        damageRate = 3;
        strength = 3;
        speed = 3;

        newGameLabel = new JLabel("NEW GAME", JLabel.CENTER);
        newGameLabel.setForeground(Game.COLOR);
        newGameLabel.setFont(Game.TITLE);
        newGameLabel.setLocation(0, 50);
        newGameLabel.setSize(600, 30);


        enterNameLabel = new JLabel("Please Enter Your Name", JLabel.CENTER);
        enterNameLabel.setForeground(Game.COLOR);
        enterNameLabel.setFont(Game.SUB_TITLE);
        enterNameLabel.setLocation(125, 125);
        enterNameLabel.setSize(350, 30);

        nameField = new JTextField();
        nameField.setLocation(225, 170);
        nameField.setHorizontalAlignment(JTextField.CENTER);
        nameField.setForeground(Color.red);
        nameField.setSize(150, 30);
        nameField.setFont(new Font("Arial", Font.PLAIN, 25));
        nameField.addKeyListener(this);

        selectDifficultyLabel = new JLabel("Select Difficulty", JLabel.CENTER);
        selectDifficultyLabel.setForeground(Game.COLOR);
        selectDifficultyLabel.setFont(Game.SUB_TITLE);
        selectDifficultyLabel.setLocation(175, 225);
        selectDifficultyLabel.setSize(250, 30);

        ButtonGroup radioGroup = new ButtonGroup();

        easyRadio = new JRadioButton("Easy");
        easyRadio.setFocusable(false);
        easyRadio.setForeground(Color.red);
        easyRadio.setBackground(Color.black);
        easyRadio.setFont(Game.TEXT);
        easyRadio.setLocation(75, 260);
        easyRadio.setSize(100, 30);
        radioGroup.add(easyRadio);

        mediumRadio = new JRadioButton("Medium");
        mediumRadio.setFocusable(false);
        mediumRadio.setForeground(Color.red);
        mediumRadio.setBackground(Color.black);
        mediumRadio.setFont(Game.TEXT);
        mediumRadio.setLocation(250, 260);
        mediumRadio.setSize(120, 30);
        mediumRadio.setSelected(true);
        radioGroup.add(mediumRadio);

        hardRadio = new JRadioButton("Hard");
        hardRadio.setFocusable(false);
        hardRadio.setForeground(Color.red);
        hardRadio.setBackground(Color.black);
        hardRadio.setFont(Game.TEXT);
        hardRadio.setLocation(425, 260);
        hardRadio.setSize(100, 30);
        radioGroup.add(hardRadio);


        shipLabel = new JLabel("Customize Ship", JLabel.CENTER);
        shipLabel.setForeground(Game.COLOR);
        shipLabel.setFont(Game.SUB_TITLE);
        shipLabel.setLocation(0, 300);
        shipLabel.setSize(600, 30);

        damageRateLabel = new JLabel("Damage Rate", JLabel.LEFT);
        damageRateLabel.setForeground(Game.COLOR);
        damageRateLabel.setFont(Game.TEXT);
        damageRateLabel.setLocation(180, 350);
        damageRateLabel.setSize(150, 30);

        strengthLabel = new JLabel("Strength", JLabel.LEFT);
        strengthLabel.setForeground(Game.COLOR);
        strengthLabel.setFont(Game.TEXT);
        strengthLabel.setLocation(180, 400);
        strengthLabel.setSize(150, 30);

        speedLabel = new JLabel("Speed", JLabel.LEFT);
        speedLabel.setForeground(Game.COLOR);
        speedLabel.setFont(Game.TEXT);
        speedLabel.setLocation(180, 450);
        speedLabel.setSize(150, 30);

        damageRateField = new JTextField();
        damageRateField.setHorizontalAlignment(JTextField.CENTER);
        damageRateField.setText(String.valueOf(damageRate));
        damageRateField.setForeground(Color.red);
        damageRateField.setEditable(false);
        damageRateField.setLocation(340, 350);
        damageRateField.setSize(30, 30);
        damageRateField.setFont(Game.TEXT);

        strengthField = new JTextField();
        strengthField.setHorizontalAlignment(JTextField.CENTER);
        strengthField.setText(String.valueOf(strength));
        strengthField.setForeground(Color.red);
        strengthField.setEditable(false);
        strengthField.setLocation(340, 400);
        strengthField.setSize(30, 30);
        strengthField.setFont(Game.TEXT);

        speedField = new JTextField();
        speedField.setHorizontalAlignment(JTextField.CENTER);
        speedField.setText(String.valueOf(speed));
        speedField.setForeground(Color.red);
        speedField.setEditable(false);
        speedField.setLocation(340, 450);
        speedField.setSize(30, 30);
        speedField.setFont(Game.TEXT);


        damageRateScroll = new Scrollbar(Scrollbar.VERTICAL, damageRate - 1, 1, 0, 5);
        damageRateScroll.setBlockIncrement(-1);
        damageRateScroll.setFocusable(false);
        damageRateScroll.setBackground(Color.black);
        damageRateScroll.setLocation(390, 350);
        damageRateScroll.setSize(50, 30);
        damageRateScroll.addAdjustmentListener(this);

        strengthScroll = new Scrollbar(Scrollbar.VERTICAL, strength - 1, 1, 0, 5);
        strengthScroll.setFocusable(false);
        strengthScroll.setBackground(Color.black);
        strengthScroll.setLocation(390, 400);
        strengthScroll.setSize(50, 30);
        strengthScroll.addAdjustmentListener(this);

        speedScroll = new Scrollbar(Scrollbar.VERTICAL, speed - 1, 1, 0, 5);
        speedScroll.setFocusable(false);
        speedScroll.setBackground(Color.black);
        speedScroll.setLocation(390, 450);
        speedScroll.setSize(50, 30);
        speedScroll.addAdjustmentListener(this);

        back = new JButton("Back");
        back.setForeground(Game.COLOR);
        back.setBackground(Color.black);
        back.setFont(Game.BUTTON);
        back.setLocation(50, 500);
        back.setSize(150, 50);
        back.addActionListener(this);

        start = new JButton("Start");
        start.setForeground(Game.COLOR);
        start.setBackground(Color.black);
        start.setFont(Game.BUTTON);
        start.setLocation(400, 500);
        start.setSize(150, 50);
        start.addActionListener(this);
        start.setVisible(false);

        add(enterNameLabel);
        add(nameField);
        add(selectDifficultyLabel);
        add(easyRadio);
        add(mediumRadio);
        add(hardRadio);
        add(newGameLabel);
        add(shipLabel);
        add(damageRateScroll);
        add(strengthScroll);
        add(speedScroll);
        add(damageRateLabel);
        add(strengthLabel);
        add(speedLabel);
        add(damageRateField);
        add(strengthField);
        add(speedField);
        add(back);
        add(start);
    }
    /**
     * Invoked when an action occurs.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            if (easyRadio.isSelected())
                engine.setDifficulty(1);
            else if (mediumRadio.isSelected())
                engine.setDifficulty(2);
            else
                engine.setDifficulty(3);
            engine.setPlayerOptions(damageRate, strength, speed);
            engine.setName(nameField.getText());
            controller.displayGameScreen();
            engine.start();
        }
        else if (e.getSource() == back) {
            controller.displayIntro();
        }
    }
    /**
     * Invoked when the value of the adjustable has changed.
     */
    public void adjustmentValueChanged(AdjustmentEvent e) {
        boolean valid = true;
        if ((5- damageRateScroll.getValue()) + (5 - strengthScroll.getValue()) + (5 - speedScroll.getValue()) > maxSpecification)
            valid = false;
        if (!valid) {
            damageRateScroll.setValue(5 - damageRate);
            strengthScroll.setValue(5 - strength);
            speedScroll.setValue(5 - speed);
        }
        else {
            damageRate = 5 - damageRateScroll.getValue();
            strength = 5 - strengthScroll.getValue();
            speed = 5 - speedScroll.getValue();
            damageRateField.setText(String.valueOf(damageRate));
            strengthField.setText(String.valueOf(strength));
            speedField.setText(String.valueOf(speed));
        }
        repaint();
    }
    /**
     * Invoked when a key has been typed.
     */
    public void keyTyped(KeyEvent e) {
        if (!nameField.getText().equals(""))
            start.setVisible(true);
        else
            start.setVisible(false);
    }
    /**
     * Invoked when a key has been pressed.
     */
    public void keyPressed(KeyEvent e) {
    }
    /**
     * Invoked when a key has been released.
     */
    public void keyReleased(KeyEvent e) {
    }
}