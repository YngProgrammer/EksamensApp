package View;
import javax.swing.*;
import controller.EmployeeHandler;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
	private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private EmployeeHandler employeeHandler; // Handler for employee actions

    public Login() {
        employeeHandler = new EmployeeHandler(); // Initialize your EmployeeHandler
        setTitle("Model Perfect");
        setSize(300, 200); // Resize for login view
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        initializeComponents();
        initializeLogo();
    }

    private void initializeComponents() {
        // Navigation bar is not typically needed for a login window, so it can be removed.
        // If you still want to keep it, make sure it's appropriate for your app's context.

        // Login panel with GridLayout for arranging labels and text fields
        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        loginPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        loginPanel.add(usernameField);

        loginPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        loginPanel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginActionListener());
        loginPanel.add(new JLabel("")); // Placeholder for alignment
        loginPanel.add(loginButton);

        add(loginPanel, BorderLayout.CENTER); // Add the login panel to the center of the BorderLayout
    }

    private class LoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            char[] password = passwordField.getPassword();

            if (employeeHandler.verifyLogin(username, new String(password))) {
                JOptionPane.showMessageDialog(Login.this, "Login successful!");
                // TODO: Proceed to the next screen or dispose of the login window
            } else {
                JOptionPane.showMessageDialog(Login.this, "Login failed. Please try again.");
            }
        }
    }

    private void initializeLogo() {
    	 ImageIcon logoIcon = new ImageIcon(getClass().getResource("modelPerfect.png"));
    	    JLabel logoLabel = new JLabel(logoIcon);

    	    // Adjust the size of the image if necessary
    	    Image image = logoIcon.getImage(); // transform it
    	    Image newimg = image.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
    	    logoIcon = new ImageIcon(newimg);  // transform it back

    	    // Add the logo label to the frame
    	    JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	    logoPanel.add(logoLabel);
    	    add(logoPanel, BorderLayout.PAGE_START);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login loginFrame = new Login(); // Renamed for clarity to loginFrame
            loginFrame.setLocationRelativeTo(null); // Center on screen
            loginFrame.setVisible(true);
        });
    }
}

