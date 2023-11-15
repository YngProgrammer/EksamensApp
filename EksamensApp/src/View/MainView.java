package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private static final long serialVersionUID = 1L;

	public void MainScreen() {
        initializeUI();
    }

    private void initializeUI() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException
                | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        ImageIcon homeIcon = new ImageIcon("Home_icon.png");
        ImageIcon ordersIcon = new ImageIcon("Order_icon.png");
        ImageIcon customersIcon = new ImageIcon("Customer_icon.png");
        ImageIcon deliveryIcon = new ImageIcon("delivery.png");
        ImageIcon teamIcon = new ImageIcon("Team_icon.png");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        setIconImage(getIconImage());

        // Create five panels with different colors and layouts
        JPanel menuPanel = createMenuPanel(homeIcon, ordersIcon, customersIcon, deliveryIcon, teamIcon);
        JPanel leftTopPanel = createButtonsPanel();
        JPanel rightTopPanel = createSearchPanel();
        JPanel centerPanel = createViewWindowPanel();
        JPanel bottomPanel = createBottomPanel();
        JPanel yearlyReportPanel = createYearlyReportPanel();
        JPanel monthlyReportPanel = createMonthlyReportPanel();

        // Set layout manager to null for absolute positioning
        setLayout(null);

        // Set bounds for each panel
        menuPanel.setBounds(50, 20, 200, 500);
        leftTopPanel.setBounds(250, 20, 250, 40);
        rightTopPanel.setBounds(500, 20, 390, 40);
        centerPanel.setBounds(285, 85, 600, 300);
        yearlyReportPanel.setBounds(590, 420, 300, 40);
        monthlyReportPanel.setBounds(590, 480, 300, 40);

        // Add panels to the frame
        add(menuPanel);
        add(leftTopPanel);
        add(rightTopPanel);
        add(centerPanel);
        add(bottomPanel);
        add(yearlyReportPanel);
        add(monthlyReportPanel);

        setVisible(true);
    }

    private JPanel createMenuPanel(ImageIcon homeIcon, ImageIcon ordersIcon, ImageIcon customersIcon,
                                    ImageIcon deliveryIcon, ImageIcon teamIcon) {
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(new Color(33, 4, 51));
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        addMenuItem(menuPanel, "Home", homeIcon);
        addVerticalSpacing(menuPanel, 20);
        addMenuItem(menuPanel, "Orders", ordersIcon);
        addVerticalSpacing(menuPanel, 20);
        addMenuItem(menuPanel, "Customers", customersIcon);
        addVerticalSpacing(menuPanel, 20);
        addMenuItem(menuPanel, "Delivery", deliveryIcon);
        addVerticalSpacing(menuPanel, 20);
        addMenuItem(menuPanel, "Team", teamIcon);

        return menuPanel;
    }

    private void addMenuItem(JPanel menuPanel, String itemName, ImageIcon icon) {
        icon = new ImageIcon(icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));

        JMenuItem menuItem = new JMenuItem(itemName, icon);
        menuItem.setHorizontalTextPosition(JMenuItem.RIGHT);

        Font customFont = new Font("Arial", Font.BOLD, 16);
        menuItem.setFont(customFont);

        menuItem.setForeground(Color.WHITE);
        menuItem.setAlignmentX(Component.CENTER_ALIGNMENT);

        menuPanel.add(menuItem);
    }

    private void addVerticalSpacing(JPanel panel, int height) {
        panel.add(Box.createRigidArea(new Dimension(0, height)));
    }

    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.WHITE);

        JButton newButton = new JButton("New");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");

        // Remove borders from the NewButton
        newButton.setBorderPainted(false);

        // Set smaller size for the buttons
        Dimension buttonSize = new Dimension(60, 30);
        newButton.setPreferredSize(buttonSize);
        updateButton.setPreferredSize(buttonSize);
        deleteButton.setPreferredSize(buttonSize);

        // Set a smaller font for the buttons
        Font buttonFont = new Font("SansSerif", Font.PLAIN, 10);
        newButton.setFont(buttonFont);
        updateButton.setFont(buttonFont);
        deleteButton.setFont(buttonFont);

        buttonsPanel.add(newButton);
        buttonsPanel.add(updateButton);
        buttonsPanel.add(deleteButton);

        return buttonsPanel;
    }

    private JPanel createViewWindowPanel() {
        JPanel viewWindowPanel = new JPanel();
        viewWindowPanel.setBackground(Color.WHITE);
        viewWindowPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        // Add components to the view window panel as needed

        return viewWindowPanel;
    }

    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(Color.WHITE); // Set background color to white
        searchPanel.setLayout(new BorderLayout());
        searchPanel.setPreferredSize(new Dimension(0, 10));

        JTextField searchField = new JTextField();

        ImageIcon magnifyingGlassIcon = new ImageIcon("Forstørrelsesglass.png");
        
        // Skaler bildet for å passe til tekststørrelsen på 20 px
        Image scaledImage = magnifyingGlassIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        magnifyingGlassIcon = new ImageIcon(scaledImage);
       
         JButton searchButton = new JButton(magnifyingGlassIcon);
         searchButton.setContentAreaFilled(false);
         searchButton.setBorderPainted(false);
      

        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        return searchPanel;
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2));

        JPanel bluePanel = new JPanel();
        bluePanel.setBackground(Color.BLUE);

        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.RED);

        bottomPanel.add(bluePanel);
        bottomPanel.add(redPanel);

        return bottomPanel;
    }

    private JPanel createYearlyReportPanel() {
        JPanel yearlyReportPanel = new JPanel();
        yearlyReportPanel.setBackground(Color.WHITE); // White background for yearly report

        JButton generateYearlyButton = new JButton("Generate Yearly Report");
        JTextField yearlyField = new JTextField("yyyy-mm");

        yearlyReportPanel.setLayout(new BorderLayout());
        yearlyReportPanel.add(yearlyField, BorderLayout.CENTER);
        yearlyReportPanel.add(generateYearlyButton, BorderLayout.EAST);

        return yearlyReportPanel;
    }

    private JPanel createMonthlyReportPanel() {
        JPanel monthlyReportPanel = new JPanel();
        monthlyReportPanel.setBackground(Color.WHITE); // White background for monthly report

        JButton generateMonthlyButton = new JButton("Generate Monthly Report");
        JTextField monthlyField = new JTextField("mm");
        monthlyField.setPreferredSize(new Dimension(15, 15));

        monthlyReportPanel.setLayout(new BorderLayout());
        monthlyReportPanel.add(monthlyField, BorderLayout.CENTER);
        monthlyReportPanel.add(generateMonthlyButton, BorderLayout.EAST);

        return monthlyReportPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainView::new);
    }
}