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
        employeeHandler = new EmployeeHandler(); // Initialize EmployeeHandler
        setTitle("Model Perfect - Login");
        setSize(400, 600); // Adjust the size 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10,10));
        initializeComponents();
        initializeLogo();
        getContentPane().setBackground(Color.WHITE); // Set background color of the window
    }

    private void initializeComponents() {
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0);

        // Adjust the labels and text fields as per the image
        JLabel userLabel = new JLabel("Employee Nr (ID)");
        userLabel.setFont(new Font("Arial", Font.BOLD, 12));
        usernameField = new JTextField(15);
        JLabel passLabel = new JLabel("password");
        passLabel.setFont(new Font("Arial", Font.BOLD, 12));
        passwordField = new JPasswordField(15);

        loginButton = new JButton("Sign in");
        loginButton.setFont(new Font("Arial", Font.BOLD, 12));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(new Color(128, 0, 128)); // A purple color
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        loginButton.addActionListener(new LoginActionListener());

        centerPanel.add(userLabel, gbc);
        centerPanel.add(usernameField, gbc);
        centerPanel.add(passLabel, gbc);
        centerPanel.add(passwordField, gbc);
        centerPanel.add(loginButton, gbc);

        add(centerPanel, BorderLayout.CENTER);
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

    
    
}


