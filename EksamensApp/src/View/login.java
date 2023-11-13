package View;
import javax.swing.*;

import controller.EmployeeHandler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login extends JFrame {

    private JTextField searchField;
    private JList<String> contentList;
    private DefaultListModel<String> listModel;
    private EmployeeHandler employeeHandler;

    public login() {
        setTitle("Model Perfect");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        initializeComponents();
        initializeLogo();
    }

    private void initializeComponents() {
        // Navigation bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);


        // Search panel
        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new SearchActionListener());
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        // Content list
        listModel = new DefaultListModel<>();
        contentList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(contentList);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void addContent(String content) {
        listModel.addElement(content);
    }

    private class SearchActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Implement search logic here using the searchField.getText() to filter contentList
            String searchText = searchField.getText();
            // Mock function to demonstrate search - replace with actual search in listModel
            searchInContent(searchText);
        }
    }

    private void searchInContent(String searchText) {
        // This is where you would implement the search logic
        // For now, let's just print out the search term
        System.out.println("Searching for: " + searchText);
    }

    private void initializeLogo() {
        // Replace 'path/to/logo.png' with your actual image path
        ImageIcon logoIcon = new ImageIcon("modelPerfect.png");
        JLabel logoLabel = new JLabel(logoIcon);

        // Adjust the size of the image if necessary
        Image image = logoIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(80, 80,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        logoIcon = new ImageIcon(newimg);  // transform it back

        // Add the logo label to the frame
        JPanel logoPanel = new JPanel();
        logoPanel.add(logoLabel);
        add(logoPanel, BorderLayout.PAGE_START); // Add it to the top of the frame
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            login login = new login();
            login.setVisible(true);
        });
    }
}
