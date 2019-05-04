import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Properties;
import java.io.*;

/**
 * OptionsPanel enables user to change game settings.
 */

public class OptionsPanel extends JPanel implements ActionListener {
    // variables
    GameController controller;

	JLabel options;
    JLabel quality;
    JLabel controlOptions;
    JLabel audioOptions;

    JRadioButton low;
    JRadioButton medium;
    JRadioButton high;
    JRadioButton keyboardMouse;
    JRadioButton keyboard;

    JTextField forward;
    JTextField backward;
    JTextField stepLeft;
    JTextField stepRight;
    JTextField fire;
    JTextField superFire;

    JCheckBox sound;
    JCheckBox music;
    JButton done;


    /**
     * Creates a new OptionsPanel.
     * @param controller GameController to use.
     */
    public OptionsPanel(GameController controller) {
        this.controller = controller;
    	String controlNames[] = {"Forward", "Backward", "Step Left", "Step Right", "Fire", "Superfire", "Turn Left", "Turn Right"};
        String controlKeys[] = {"W", "S", "A", "D", "Mouse Left", "Mouse Right", "Mouse Move", "Mouse Move"};

        options = new JLabel("OPTIONS", JLabel.CENTER);
        options.setForeground(Game.COLOR);
        options.setFont(Game.TITLE);
        options.setLocation(0, 25);
        options.setSize(600, 40);

        for (int i = 0; i < 8; i++)
        {
        	JLabel controlName = new JLabel(controlNames[i]);
        	controlName.setForeground(Game.COLOR);
        	controlName.setFont(Game.TEXT);
            if (i > 3)
                controlName.setLocation(315, 355 + (i-4) * 30);
            else
        	    controlName.setLocation(50, 355+i*30);
        	controlName.setSize(250, 30);
        	add(controlName);

            JTextField controlKey = new JTextField();
            controlKey.setSize(80, 20);
            if (i > 3)
                controlKey.setLocation(450, 360 + (i-4) * 30);
            else
		        controlKey.setLocation(190, 360+i*30);
            controlKey.setText(controlKeys[i]);
            controlKey.setEditable(false);
            controlKey.setHorizontalAlignment(JTextField.CENTER);
            add(controlKey);
        }

        setLayout(null);
        setBackground(Color.black);
        setPreferredSize(new Dimension(600, 600));

        quality = new JLabel("Quality", JLabel.CENTER);
        quality.setForeground(Game.COLOR);
        quality.setFont(Game.SUB_TITLE);
        quality.setLocation(125, 85);
        quality.setSize(350, 30);

        audioOptions = new JLabel("Audio Options", JLabel.CENTER);
        audioOptions.setForeground(Game.COLOR);
        audioOptions.setFont(Game.SUB_TITLE);
        audioOptions.setLocation(175, 165);
        audioOptions.setSize(250, 50);

        controlOptions = new JLabel("Control Options", JLabel.CENTER);
        controlOptions.setForeground(Game.COLOR);
        controlOptions.setFont(Game.SUB_TITLE);
        controlOptions.setLocation(175, 265);
        controlOptions.setSize(250, 50);

        ButtonGroup controlGroup = new ButtonGroup();
        keyboardMouse = new JRadioButton("Keyboard + Mouse");
        keyboardMouse.setForeground(Color.red);
        keyboardMouse.setBackground(Color.black);
        keyboardMouse.setFont(Game.TEXT);
        keyboardMouse.setLocation(45, 315);
        keyboardMouse.setSize(250, 30);
        keyboardMouse.addActionListener(this);
        controlGroup.add(keyboardMouse);

        keyboard = new JRadioButton("Keyboard");
        keyboard.setForeground(Color.red);
        keyboard.setBackground(Color.black);
        keyboard.setFont(Game.TEXT);
        keyboard.setLocation(400, 315);
        keyboard.setSize(150, 30);
        keyboard.addActionListener(this);
        controlGroup.add(keyboard);

		ButtonGroup qualityGroup = new ButtonGroup();

		low = new JRadioButton("Low");
        low.setForeground(Color.red);
        low.setBackground(Color.black);
        low.setFont(Game.TEXT);
        low.setLocation(75, 125);
        low.setSize(100, 30);
        qualityGroup.add(low);

        medium = new JRadioButton("Medium");
        medium.setForeground(Color.red);
        medium.setBackground(Color.black);
        medium.setFont(Game.TEXT);
        medium.setLocation(250, 125);
        medium.setSize(120, 30);
        qualityGroup.add(medium);

        high = new JRadioButton("High");
        high.setForeground(Color.red);
        high.setBackground(Color.black);
        high.setFont(Game.TEXT);
        high.setLocation(425, 125);
        high.setSize(100, 30);
        high.setSelected(true);
        qualityGroup.add(high);

        sound = new JCheckBox("Sound");
        sound.setForeground(Game.COLOR);
        sound.setBackground(Color.black);
        sound.setFont(Game.TEXT);
        sound.setLocation(130, 225);
        sound.setSize(105, 30);
        sound.setSelected(false);

        music = new JCheckBox("Music");
        music.setForeground(Game.COLOR);
        music.setBackground(Color.black);
        music.setFont(Game.TEXT);
        music.setLocation(365, 225);
        music.setSize(105, 30);
        music.setSelected(false);

        done = new JButton("Done");
        done.setForeground(Game.COLOR);
        done.setBackground(Color.black);
        done.setFont(Game.BUTTON);
        done.setLocation(400, 500);
        done.setSize(150, 50);
        done.addActionListener(this);

        try {
            Properties prop = new Properties();
            FileInputStream in = new FileInputStream("settings/options.prop");
            prop.load(in);
            if (prop.getProperty("quality", "medium").equals("low")) {
                low.setSelected(true);
            }
            else if (prop.getProperty("quality", "medium").equals("medium")) {
                medium.setSelected(true);
            }
            else {
                high.setSelected(true);
            }
            if (prop.getProperty("music", "false").equals("true"))
                music.setSelected(true);
            if (prop.getProperty("sound", "false").equals("true"))
                sound.setSelected(true);
            if (prop.getProperty("control", "key+mouse").equals("key+mouse")) {
                keyboardMouse.setSelected(true);
            }
            else {
                keyboard.setSelected(true);
                ((JTextField)getComponent(9)).setText("Up");
                ((JTextField)getComponent(11)).setText("Down");
                ((JTextField)getComponent(13)).setText("Left");
                ((JTextField)getComponent(15)).setText("Right");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        add(options);
        add(quality);
        add(low);
        add(medium);
        add(high);
        add(audioOptions);
        add(sound);
        add(music);
        add(controlOptions);
        add(keyboardMouse);
        add(keyboard);
        add(done);
    }
    /**
     * Invoked when an action occurs.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == keyboardMouse){
            ((JTextField)getComponent(9)).setText("Mouse Left");
            ((JTextField)getComponent(11)).setText("Mouse Right");
            ((JTextField)getComponent(13)).setText("Mouse Move");
            ((JTextField)getComponent(15)).setText("Mouse Move");
        }
        else if (e.getSource() == keyboard) {
            ((JTextField)getComponent(9)).setText("Up");
            ((JTextField)getComponent(11)).setText("Down");
            ((JTextField)getComponent(13)).setText("Left");
            ((JTextField)getComponent(15)).setText("Right");
        }
        else if (e.getSource() == done) {
            Properties prop = new Properties();
            if (low.isSelected()) {
                prop.setProperty("quality", "low");
            }
            else if (medium.isSelected()) {
                prop.setProperty("quality", "medium");
            }
            else {
                prop.setProperty("quality", "high");
            }
            if (sound.isSelected())
                prop.setProperty("sound", "true");
            if (music.isSelected())
                prop.setProperty("music", "true");
            if (keyboardMouse.isSelected()) {
                prop.setProperty("control", "key+mouse");
            }
            else {
                prop.setProperty("control", "key");
            }
            try {
                FileOutputStream out = new FileOutputStream("settings/options.prop");
                prop.store(out, "Options");
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            controller.optionsDone();
        }
    }
}

