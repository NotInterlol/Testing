package parking;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {
	
	/* ------------GUI STYLING------------ */

	public MainMenuPanel(MainFrame frame) {
        setLayout(null);
        setBounds(0, 0, 960, 720);
        setBackground(Color.cyan);

        Font btnFont = new Font(Font.SANS_SERIF, Font.BOLD, 20);
        Color btnColor = new Color(128, 128, 255);

        // Button Positioning
        int buttonWidth = 240;
        int buttonHeight = 55;
        int centerX = (960 - buttonWidth) / 2;
        int startY = 275;
        int gap = 20;

        JLabel title = new JLabel("INPROLA PARKING SYSTEM", JLabel.CENTER);
        title.setBounds(0, 125, 960, 100);
        title.setForeground(Color.black);
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        add(title);
        
        /* ------------NAVIGATION BUTTONS------------ */

        JButton parkButton = new JButton("Park Vehicle");
        parkButton.setBounds(centerX, startY, buttonWidth, buttonHeight);
        styleButton(parkButton, btnFont, btnColor);
        parkButton.addActionListener(e -> {
            SlotManager.setMode(SlotManager.Mode.PARK);
            frame.showParkSelection();
        });

        JButton removeButton = new JButton("Remove Vehicle");
        removeButton.setBounds(centerX, startY + buttonHeight + gap, buttonWidth, buttonHeight);
        styleButton(removeButton, btnFont, btnColor);
        removeButton.addActionListener(e -> {
            SlotManager.setMode(SlotManager.Mode.REMOVE);
            frame.showParkSelection();
        });
        
        /* ------------SHOW VISIBILITY------------ */
        
        add(parkButton);
        add(removeButton);
    }
		// Styles the buttons
    private void styleButton(JButton btn, Font font, Color color) {
        btn.setFont(font);
        btn.setForeground(Color.black);
        btn.setBackground(Color.white);
        btn.setFocusPainted(false);
        btn.setOpaque(true);
    }
}
