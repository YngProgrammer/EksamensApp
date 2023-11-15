package View;

// Import statements (make sure all necessary imports are included)
import controller.OrderHandler;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class OrderView {
    // Class members
    private OrderHandler OrderHandler;
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private int selectedRowIndex = -1;
    private JTextField textField;

    // Database connection details
    private final String dbURL = "jdbc:mysql://127.0.0.1:3306/classicmodels";
    private final String user = "student";
    private final String password = "student";

    public OrderView() {
        this.OrderHandler = new OrderHandler(); // Initialize your order handler
        initialize();
        fetchAndDisplayOrders(); // Call this method to populate the table at startup
    }    // New method for fetching and displaying orders
    private void fetchAndDisplayOrders() {
        try (Connection conn = DriverManager.getConnection(dbURL, user, password);
             Statement statement = conn.createStatement()) {

            String sql = "SELECT orderNumber, orderDate, status, customerNumber FROM orders";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String orderNumber = resultSet.getString("orderNumber");
                String orderDate = resultSet.getString("orderDate");
                String status = resultSet.getString("status");
                String customerNumber = resultSet.getString("customerNumber");
                tableModel.addRow(new Object[]{orderNumber, orderDate, status, customerNumber});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initialize() {
        frame = new JFrame("Order Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setSize(1000, 600);

        UIManager.put("Table.selectionBackground", new Color(0x3ec1a5));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            private static final long serialVersionUID = 1L;

			@Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                ((JComponent) component).setBorder(new EmptyBorder(5, 10, 5, 10)); // Adjust padding

                if (isSelected) {
                    component.setBackground(new Color(0x3ec1a5)); // Selected row color
                } else {
                    component.setBackground(row % 2 == 0 ? new Color(0xFFFFFF) : new Color(0xF0F0F0)); // Zebra stripes
                }
                return component;
            }
        };

        
        String[] columnNames = {"Order Number", "Order Date", "Status", "Customer Number"};
        tableModel = new DefaultTableModel(null, columnNames) {
            private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(true);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedRowIndex = table.getSelectedRow();
            }
        });

        for (int i = 0; i < columnNames.length; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        controlPanel.setBorder(new EmptyBorder(15, 25, 15, 25));
        controlPanel.setBackground(new Color(62, 193, 165));
        JButton searchButton = new JButton("Search");
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        searchButton.setBackground(new Color(0x2ecc71));
        addButton.setBackground(new Color(0x2ecc71));
        editButton.setBackground(new Color(0xf1c40f));
        deleteButton.setBackground(new Color(0xe74c3c));
        
        textField = new JTextField();
        textField.setBackground(new Color(246, 248, 250));
        controlPanel.add(textField);
        textField.setColumns(10);
        controlPanel.add(searchButton);
        controlPanel.add(addButton);
        controlPanel.add(editButton);
        controlPanel.add(deleteButton);

        frame.getContentPane().add(controlPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        bottomPanel.setBackground(new Color(0x3ec1a5));

        JButton monthlyReportButton = new JButton("Monthly Report");
        JButton yearlyReportButton = new JButton("Yearly Report");
        monthlyReportButton.setBackground(new Color(0x3498db));
        yearlyReportButton.setBackground(new Color(0x9b59b6));

        bottomPanel.add(monthlyReportButton);
        bottomPanel.add(yearlyReportButton);

        frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        // Action listeners
        addButton.addActionListener(this::addOrder);
        editButton.addActionListener(this::editOrder);
        deleteButton.addActionListener(this::deleteOrder);
        searchButton.addActionListener(e -> searchOrders());
        monthlyReportButton.addActionListener(this::monthlyReport);
        yearlyReportButton.addActionListener(this::yearlyReport);

        // Set frame visibility at the end of initialization
        frame.setVisible(true);
    }

    // Implement these methods according to your application's logic
    private void addOrder(ActionEvent e) {
        // Implementation for adding an order
    }

    private void editOrder(ActionEvent e) {
        // Implementation for editing an order
    }

    private void deleteOrder(ActionEvent e) {
        // Implementation for deleting an order
    }

    private void searchOrders() {
        // Implementation for searching orders
    }

    private void monthlyReport(ActionEvent e) {
        // Implementation for generating a monthly report
    }

    private void yearlyReport(ActionEvent e) {
        // Implementation for generating a yearly report
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new OrderView();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}