package parking;

import javax.swing.*;
import java.awt.*;

public class ParkSelectionPanel extends JPanel {

    public ParkSelectionPanel(MainFrame frame,
    		SlotSelectionPanel regularSlots, 
            SlotSelectionPanel pwdSlots, 
            SlotSelectionPanel seniorSlots) {
    	
    	/* ------------GUI STYLING------------ */
    	
        setLayout(null);
        setBounds(0, 0, 960, 720);
        setBackground(new Color(0,0,128));

        Font btnFont = new Font(Font.SANS_SERIF, Font.BOLD, 20);
        Color btnColor = new Color(255, 255, 255);
        
        // Button Positioning
        int buttonWidth = 240;
        int buttonHeight = 55;
        int centerX = (960 - buttonWidth) / 2; // Lets the buttons be stacked
        int startY = 250;
        int gap = 20;

        JLabel label = new JLabel("Select your type of Parking", JLabel.CENTER);
        label.setBounds(0, 100, 960, 50);
        label.setForeground(new Color(207, 181, 59));
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        add(label);
        
        /* ------------USER BUTTON INPUTS------------ */
        
        // Button Assignment
        JButton regular = new JButton("Regular");
        JButton senior = new JButton("Senior");
        JButton pwd = new JButton("PWD");
        
        // Panel Switching
        regular.addActionListener(e -> frame.showSlotSelection(regularSlots));
        pwd.addActionListener(e -> frame.showSlotSelection(pwdSlots));
        senior.addActionListener(e -> frame.showSlotSelection(seniorSlots));
        
        // Stacks the buttons
        regular.setBounds(centerX, startY, buttonWidth, buttonHeight); // Regular Vehicles
        senior.setBounds(centerX, startY + buttonHeight + gap, buttonWidth, buttonHeight); // Senior Vehicles
        pwd.setBounds(centerX, startY + 2 * (buttonHeight + gap), buttonWidth, buttonHeight); // PWD Vehicles
        
        // Applies button styling
        styleButton(regular, btnFont, btnColor);
        styleButton(senior, btnFont, btnColor);
        styleButton(pwd, btnFont, btnColor);
        
        // Back Button
        JButton back = new JButton("Back to Main Menu");
        back.setBounds(centerX, 600, buttonWidth, buttonHeight);
        styleButton(back, btnFont, btnColor);
        back.addActionListener(e -> frame.showMainMenu());
        
        // Show Visibility
        add(regular);
        add(senior);
        add(pwd);
        add(back);
    }
    	// Styles the buttons
    private void styleButton(JButton btn, Font font, Color color) {
        btn.setFont(font);
        btn.setForeground(Color.black);
        btn.setBackground(color);
        btn.setFocusPainted(false);
        btn.setOpaque(true);
    }
}