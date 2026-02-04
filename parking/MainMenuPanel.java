package parking;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {
	
	/* ------------GUI STYLING------------ */

	public MainMenuPanel(MainFrame frame) {
        setLayout(null);
        setBounds(0, 0, 960, 720);
        setBackground(new Color(0,0,128));

        Font btnFont = new Font(Font.SANS_SERIF, Font.BOLD, 20);
        Color btnColor = new Color(128, 128, 255);
        
        JButton parkButton = new JButton("Park Vehicle");
        JButton unparkButton = new JButton("Unpark My Vehicle");
        JButton removeButton = new JButton("Remove Vehicle");
        JButton logoutButton = new JButton("Log out");
        

        // Button Positioning
        int buttonWidth = 240;
        int buttonHeight = 55;
        int centerX = (960 - buttonWidth) / 2;
        int startY = 275;
        int gap = 20;

        JLabel title = new JLabel("INPROLA PARKING SYSTEM", JLabel.CENTER);
        title.setBounds(0, 125, 960, 100);
        title.setForeground(new Color(207, 181, 59));
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        add(title);
        
        /* ------------NAVIGATION BUTTONS------------ */
        
        // Park Vehicle
        parkButton.setBounds(centerX, startY, buttonWidth, buttonHeight);
        styleButton(parkButton, btnFont, btnColor);
        parkButton.addActionListener(e -> {
            SlotManager.setMode(SlotManager.Mode.PARK);
            frame.showParkSelection();
        });
        add(parkButton);
        
        // Check if user is admin
        // If user is admin allow removing vehicles of other users else unpark button for normal users
        if (UserSession.isAdmin()) {
            removeButton.setBounds(centerX, startY + buttonHeight + gap, buttonWidth, buttonHeight);
            styleButton(removeButton, btnFont, btnColor);
            removeButton.addActionListener(e -> {
                SlotManager.setMode(SlotManager.Mode.REMOVE);
                frame.showParkSelection();
            });
            add(removeButton);
        } else {
            unparkButton.setBounds(centerX, startY + buttonHeight + gap, buttonWidth, buttonHeight);
            styleButton(unparkButton, btnFont, btnColor);
            unparkButton.addActionListener(e -> {
                SlotManager.setMode(SlotManager.Mode.REMOVE);
                frame.showParkSelection();
            });
            add(unparkButton);
        }
     
        // Logout Button
        logoutButton.setBounds(centerX, 600, buttonWidth, buttonHeight);
        styleButton(logoutButton, btnFont, btnColor);
        logoutButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to log out?",
                    "Confirm Logout",
                    JOptionPane.YES_NO_OPTION
            );
            
            // Sends user back to login page
            if (confirm == JOptionPane.YES_OPTION) {
                UserSession.logout();
                frame.dispose();
                new LoginFrame();
            }
        });
        add(logoutButton);
        
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