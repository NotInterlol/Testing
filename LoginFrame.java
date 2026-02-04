package parking;

import javax.swing.*;

public class LoginFrame extends JFrame {
	
	// Login GUI //
    public LoginFrame() {
        setTitle("Login");
        setSize(960, 720);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        add(new LoginPanel(this));

        setVisible(true);
    }
}