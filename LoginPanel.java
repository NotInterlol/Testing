package parking;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

	public LoginPanel(LoginFrame loginFrame) {
		
		/* --------- SWING LIBRARY ASSIGNMENT --------- */
		
		JLabel title = new JLabel("Parking System Login", JLabel.CENTER);
		JLabel userLabel = new JLabel("Username:");
		JLabel passLabel = new JLabel("Password:");
		JTextField usernameField = new JTextField();
		JPasswordField passwordField = new JPasswordField();
		JButton loginButton = new JButton("Login");
		
		/* --------- GUI STYLING--------- */
		
        setLayout(null);
        setBounds(0, 0, 960, 720);
        setBackground(new Color(0,0,128));
        
        /* --------- USER INPUTS --------- */
        
        // Title Label
        title.setForeground(new Color(207, 181, 59)); 
        title.setBounds(0, 150, 960, 40);
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 28));
        add(title);
        
        // User labels
        userLabel.setBounds(330, 250, 100, 30); // 
        userLabel.setForeground(new Color(207, 181, 59));
        add(userLabel);
        
        // User Input Field
        usernameField.setBounds(430, 250, 200, 30);
        add(usernameField);
       
        // Password Label
        passLabel.setBounds(330, 300, 100, 30);
        passLabel.setForeground(new Color(207, 181, 59));
        add(passLabel);
        
        // Password Input Field
        passwordField.setBounds(430, 300, 200, 30);
        add(passwordField);
        
        // Login Button
        loginButton.setBounds(430, 360, 200, 40);
        loginButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        loginButton.setForeground(Color.black);
        loginButton.setBackground(Color.white);
        loginButton.setFocusPainted(false);
        add(loginButton);
        
        // Actions when login button is clicked
        loginButton.addActionListener(e -> {

            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            User user = AccountManager.login(username, password);
            
            // Successful Logins
            if (user != null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Login successful. \nRole: " + user.getRole()
                );
                UserSession.setUser(user);
                loginFrame.dispose();
                new MainFrame();
                
                // Incorrect Inputs
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Invalid username or password.",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
}


