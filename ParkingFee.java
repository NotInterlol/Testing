package parking;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.Duration;

public class ParkingFee {
	
	/* --------- PAYMENT CALCULATIONS --------- */
	
    private static final double PARKING_FEE = 50.00;
    private static final long PENALTY_INTERVAL_SECONDS = 10; // Every 10 seconds past allowed time
    private static final double PENALTY_RATE = 20.0; // ₱20 per interval

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
    
    /* --------- PENALTY CALCULATIONS --------- */
    
    public static void collectPenalty(User user, LocalDateTime parkedAt, long allowedSeconds, JButton slotButton) {

        if (user.isAdmin()) return; // Admins don't pay penalties

        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(parkedAt, now);
        long totalSeconds = duration.getSeconds();

        if (totalSeconds <= allowedSeconds) return; // No penalty

        // Calculate penalty based on intervals
        long excessSeconds = totalSeconds - allowedSeconds;
        long intervals = (long) Math.ceil((double) excessSeconds / PENALTY_INTERVAL_SECONDS);
        double penalty = intervals * PENALTY_RATE;

        // Manual payment input loop
        while (true) {
            String input = JOptionPane.showInputDialog(
                    slotButton,
                    "Your penalty for exceeding time is PHP " + String.format("%.2f", penalty) +
                            "\nPlease enter the amount to pay:",
                    "Penalty Payment",
                    JOptionPane.PLAIN_MESSAGE
            );

            if (input == null) {
                JOptionPane.showMessageDialog(
                        slotButton,
                        "You must pay the penalty to unpark your vehicle.",
                        "Payment Required",
                        JOptionPane.WARNING_MESSAGE
                );
                continue;
            }
            try {
            	
            	// Force double data type on inputs           	
                double paidAmount = Double.parseDouble(input);
                if (paidAmount >= penalty) {
                    double change = paidAmount - penalty; // Calculation for Change
                    String message = "Payment of PHP " + String.format("%.2f", paidAmount) + " accepted.";
                    if (change > 0) {
                        message += "\nYour change: PHP " + String.format("%.2f", change);
                    }
                    JOptionPane.showMessageDialog(
                            slotButton,
                            message,
                            "Payment Successful",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    break; // Exit loop after successful payment
                } else {
                	// Force users to pay unpark vehicle
                    JOptionPane.showMessageDialog(
                            slotButton,
                            "The amount entered is less than the penalty. Please enter at least PHP " + String.format("%.2f", penalty),
                            "Insufficient Payment",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
              // Error handler for incorrect inputs
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        slotButton,
                        "Invalid input. Please enter a number.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE
                );
            }
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
