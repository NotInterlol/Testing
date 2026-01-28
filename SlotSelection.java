package parking;

import javax.swing.*;
import java.awt.*;

public class SlotSelectionPanel extends JPanel {

    public SlotSelectionPanel(MainFrame frame, String type) {

        /* ------------ GUI STYLING ------------ */

        setLayout(null);
        setBounds(0, 0, 960, 720);
        setBackground(Color.cyan);

        JLabel label = new JLabel(type + " Parking Slots", JLabel.CENTER);
        label.setBounds(0, 20, 960, 50);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        label.setForeground(Color.black);
        add(label);

        // Button Positioning
        int rows = 4;
        int columns = 5;
        int buttonWidth = 125;  // Creates the buttons to be in a 4x5 Grid 
        int buttonHeight = 80;
        int hGap = 50;
        int vGap = 50;
        					
        int startX = (960 - (columns * buttonWidth + (columns - 1) * hGap)) / 2;
        int startY = 100;  

        /* ------------ CREATE SLOT BUTTONS ------------ */

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) { // Will create buttons as long as column is less than columns, refer to line 26-27

                int slotNumber = row * columns + col + 1;
                
                // Calls the SlotManager class
            	// Seperates the Regular, Pwd, and Senior slots so that they dont override each other
                SlotManager.registerSlot(type, slotNumber);

                JButton slotButton = new JButton("Slot " + slotNumber);
                slotButton.setBounds(
                        startX + col * (buttonWidth + hGap),
                        startY + row * (buttonHeight + vGap), // Spaces the buttons evenly so that it appears in a 4x5 grid
                        buttonWidth,
                        buttonHeight
                );

                slotButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
                slotButton.setFocusPainted(false);

                // Set initial color correctly
                updateSlotColor(slotButton, type, slotNumber);

                /* ------------ PARKING SLOTS MANAGER ------------ */

                slotButton.addActionListener(e -> {
                	
                    SlotManager.Mode mode = SlotManager.getMode();

                    /* ===== PARK MODE ===== */
                    if (mode == SlotManager.Mode.PARK) {

                        if (!SlotManager.isOccupied(type, slotNumber)) {
                        	
                        	// Applies button color changes on the applied slot
                            SlotManager.occupySlot(type, slotNumber);
                            updateSlotColor(slotButton, type, slotNumber);
                            
                            JOptionPane.showMessageDialog(
                                    slotButton,
                                    type + " Slot " + slotNumber + " occupied.",
                                    "Success",
                                    JOptionPane.INFORMATION_MESSAGE
                            );

                        } else {
                            JOptionPane.showMessageDialog(
                                    slotButton,
                                    "This slot is already occupied.",
                                    "Unavailable",
                                    JOptionPane.WARNING_MESSAGE
                            );
                        }
                    }

                    /* ===== REMOVE MODE ===== */
                    else if (mode == SlotManager.Mode.REMOVE) {

                        if (SlotManager.isOccupied(type, slotNumber)) {

                            int confirm = JOptionPane.showConfirmDialog(
                                    slotButton,
                                    "Remove vehicle from " + type + " Slot " + slotNumber + "?",
                                    "Confirm Removal",
                                    JOptionPane.YES_NO_OPTION
                            );

                            if (confirm == JOptionPane.YES_OPTION) {

                                SlotManager.freeSlot(type, slotNumber);
                                updateSlotColor(slotButton, type, slotNumber);

                                JOptionPane.showMessageDialog(
                                        slotButton,
                                        "Slot successfully freed.",
                                        "Removed",
                                        JOptionPane.INFORMATION_MESSAGE
                                );
                            }

                        } else {
                            JOptionPane.showMessageDialog(
                                    slotButton,
                                    "This slot is already empty.",
                                    "Nothing to Remove",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                        }
                    }
                });

                add(slotButton);
            }
        }

        /* ------------ BACK BUTTON ------------ */

        JButton back = new JButton("Back");
        back.setBounds(360, 600, 240, 55);
        back.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        back.setFocusPainted(false);
        back.addActionListener(e -> frame.showParkSelection());
        add(back);
    }

    /* ------------ HELPER METHOD ------------ */
    
    // Changes the button color when occupied or removed a vehicle
    private void updateSlotColor(JButton button, String type, int slotNumber) {
        if (SlotManager.isOccupied(type, slotNumber)) {
            button.setBackground(Color.red);
            button.setForeground(Color.black);
        } else {
            button.setBackground(Color.green);
            button.setForeground(Color.white);
        }
    }
}
