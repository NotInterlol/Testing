package parking;

import javax.swing.*;

public class ParkingFee {
	
	/* --------- PAYMENT CALCULATIONS --------- */
	
    private static final double PARKING_FEE = 50.00;

    public static boolean processPayment(User user, double amount) {
    	
    	while (true) {
            String input = JOptionPane.showInputDialog(
                    null,
                    "Parking fee: PHP " + String.format("%.2f", amount) + "\nEnter payment amount:",
                    "Payment",
                    JOptionPane.PLAIN_MESSAGE
            );

            if (input == null) {
                // User clicked cancel
                JOptionPane.showMessageDialog(null,
                        "Payment cancelled. Slot remains free.",
                        "Payment Cancelled",
                        JOptionPane.WARNING_MESSAGE);
                return false;
            }

            double enteredAmount;
            
            // Try and except functions to catch possible errors
            try {
                enteredAmount = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Invalid input. Please enter a valid number.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
                continue; // Ask again
            }
            
            if (enteredAmount < amount) {
                JOptionPane.showMessageDialog(null,
                        "Insufficient payment. Please enter at least PHP " + amount,
                        "Payment Error",
                        JOptionPane.ERROR_MESSAGE);
                continue; // Ask again
            }
            
            double change = enteredAmount - amount;
            
            // Calculations for extra payment values
            if (change > 0) {
                JOptionPane.showMessageDialog(null,
                        "Payment successful! Your change: PHP " + String.format("%.2f", change),
                        "Payment Complete",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Payment successful!",
                        "Payment Complete",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            
            return true;
    	}
    }	
    
    public static double getFee(String slotType) {
    	// Provide 10% discount for PWD and Senior slots
    	switch (slotType.toUpperCase()) {
    		case "PWD":
    		case "SENIOR":
    			return PARKING_FEE * 0.9;
    		default:
    			return PARKING_FEE;
    	}
    }
}


