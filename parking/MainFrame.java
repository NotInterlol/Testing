package parking;

import javax.swing.*;

public class MainFrame extends JFrame {
	
	/* ------------CLASS ASSIGNMENT------------ */
	
    private MainMenuPanel mainMenuPanel;
    private ParkSelectionPanel parkSelectionPanel;
    private SlotSelectionPanel regularSlots;
    private SlotSelectionPanel pwdSlots;
    private SlotSelectionPanel seniorSlots;
    
    /* ------------GUI DESIGN------------ */

    public MainFrame() {
        setTitle("Parking System");
        setSize(960, 720);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        // Initialize the Parking Selection
        regularSlots = new SlotSelectionPanel(this, "Regular");
        pwdSlots = new SlotSelectionPanel(this, "PWD");
        seniorSlots = new SlotSelectionPanel(this, "Senior");
        
        // Initialize the panels
        mainMenuPanel = new MainMenuPanel(this);
        parkSelectionPanel = new ParkSelectionPanel(this, regularSlots, pwdSlots, seniorSlots);
        
        // Show visibility
        add(mainMenuPanel);
        setVisible(true);
    }
    
    /* ------------SWITCH PANELS------------ */

    public void showMainMenu() {
        getContentPane().removeAll();  
        add(mainMenuPanel);
        revalidate();
        repaint();
    }

    public void showParkSelection() {
        getContentPane().removeAll();  
        add(parkSelectionPanel);
        revalidate();
        repaint();
    }

    public void showSlotSelection(SlotSelectionPanel panel) {
        getContentPane().removeAll();
        add(panel);
        revalidate();
        repaint();
    }
}