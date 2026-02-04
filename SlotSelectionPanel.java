package parking;

import javax.swing.*;
import java.awt.*;

public class SlotSelectionPanel extends JPanel {

    public SlotSelectionPanel(MainFrame frame, String type) {

        /* ------------ GUI STYLING ------------ */

        setLayout(null);
        setBounds(0, 0, 960, 720);
        setBackground(new Color(0,0,128));

        JLabel label = new JLabel(type + " Parking Slots", JLabel.CENTER);
        label.setBounds(0, 20, 960, 50);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        label.setForeground(new Color(207, 181, 59));
        add(label);

        // Button Positioning
        int columns = 4;
        int rows = 5;
        int buttonWidth = 125;  // Creates the buttons to be in a 4x5 Grid 
        int buttonHeight = 80;
        int hGap = 50;
        int vGap = 50;
        					
        int startX = (960 - ( rows * buttonWidth + (rows - 1) * hGap)) / 2;
        int startY = 100;  

        /* ------------ CREATE SLOT BUTTONS ------------ */

        for (int col = 0; col < columns; col++) {
            for (int row = 0; row <  rows; row++) { // Will create buttons as long as column is less than columns, refer to line 23-24

                int slotNumber = col  * rows + row + 1;
                
                // Calls the SlotManager class
            	// Seperates the Regular, Pwd, and Senior slots so that they dont override each other
                SlotManager.registerSlot(type, slotNumber);

                JButton slotButton = new JButton("Slot " + slotNumber);
                slotButton.setBounds(
                        startX + row * (buttonWidth + hGap),
                        startY + col * (buttonHeight + vGap), // Spaces the buttons evenly so that it appears in a 4x5 grid
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

                    // Parking Vehicles
                    if (mode == SlotManager.Mode.PARK) {
                    	
                    	boolean paid;

                        if (!SlotManager.isOccupied(type, slotNumber)) {
                        	// Checks if user is Admin
                        	if (UserSession.isAdmin()) {
                        		paid = true;
                        	} else {
                        		// Payment process for regular users
                           	 	paid = ParkingFee.processPayment(UserSession.getUser(), ParkingFee.getFee(type)); // Calls parking fee to check if the payment is processed
                        	}
                         	// Confirms if the payment was successful or not
                         	if (paid) {
                                 // Occupy slot after payment
                                 SlotManager.occupySlot(type, slotNumber, UserSession.getUser());
                                 updateSlotColor(slotButton, type, slotNumber);
                                 
                                 // Gives out ticket after successful payment
                                 JOptionPane.showMessageDialog(
                                         slotButton,
                                         "You have received a parking ticket. Please keep it for reference.",
                                         "Parking Ticket",
                                         JOptionPane.INFORMATION_MESSAGE
                                 );
                        	}                        	
                        } else {
                            JOptionPane.showMessageDialog(
                                    slotButton,
                                    "This slot is already occupied.",
                                    "Unavailable",
                                    JOptionPane.WARNING_MESSAGE
                            );
                        }
                    }

                    // Remove Vehicles
                    else if (mode == SlotManager.Mode.REMOVE) {
                        if (SlotManager.isOccupied(type, slotNumber)) {
                        	 ParkingSlot slot = SlotManager.getSlot(type, slotNumber);
                             if (!UserSession.isAdmin() && !slot.getOwner().equals(UserSession.getUser())) {
                                 JOptionPane.showMessageDialog(
                                     slotButton,
                                     "You cannot remove another user's vehicle!",
                                     "Access Denied",
                                     JOptionPane.WARNING_MESSAGE
                                 );
                                 return;
                             }
                        	
                        	// Action Confirmation on removing vehicles
                            int confirm = JOptionPane.showConfirmDialog(
                                    slotButton,
                                    "Remove vehicle from " + type + " Slot " + slotNumber + "?",
                                    "Confirm Removal",
                                    JOptionPane.YES_NO_OPTION
                            );
                            
                            // removes vehicle from the slot if chosen the option "Yes" refer to line 100
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
                            
                            // Tells the user that the parking slot is already empty
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
        back.setForeground(Color.black);
        back.setBackground(Color.white);
        back.setFocusPainted(false);
        back.addActionListener(e -> frame.showParkSelection());
        add(back);
    }

    /* ------------ COLOR CHANGING BUTTONS ------------ */
    
    // Changes the button color when occupied or removed a vehicle
    private void updateSlotColor(JButton button, String type, int slotNumber) {
    	// Turns red if occupied
        if (SlotManager.isOccupied(type, slotNumber)) {
            button.setBackground(Color.red);
            button.setForeground(Color.black);
            // Turns Green if unoccupied
        } else {
            button.setBackground(Color.green);
            button.setForeground(Color.white);
        }
    }
}