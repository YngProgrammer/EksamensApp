package com.examapp.views;

// Essential imports for GUI, events, and database operations
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class OrderView extends JPanel {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField textField;

    // Database connection details
    private final String dbURL = "jdbc:mysql://127.0.0.1:3306/classicmodels";
    private final String user = "student";
    private final String password = "student";

    public OrderView() {
        initializeUI();
        fetchAndDisplayOrders();
    }
    /**
	 * @wbp.parser.entryPoint
	 */
    // Method to initialize the user interface
    private void initializeUI() {
        frame = new JFrame("Order Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setSize(1000, 600);

        // Create and add the table to the frame
        setupTable();
        // Create and add control panel with buttons
        setupControlPanel();
        // Create and add bottom panel for reports
        setupBottomPanel();

        frame.setVisible(true);
    }

    // Method to set up the table
    private void setupTable() {
        String[] columnNames = {"Order Number", "Order Date", "Status", "Customer Number"};
        tableModel = new DefaultTableModel(null, columnNames) {
            private static final long serialVersionUID = 1L; // serialVersionUID added here

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        customizeTableAppearance();
        JScrollPane scrollPane = new JScrollPane(table);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    // Method to customize table appearance
    private void customizeTableAppearance() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            private static final long serialVersionUID = 1L; // serialVersionUID added here

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                ((JComponent) component).setBorder(new EmptyBorder(5, 10, 5, 10)); // Adjust padding

                if (isSelected) {
                    component.setBackground(new Color(0x5f0c8e)); // Selected row color
                } else {
                    component.setBackground(row % 2 == 0 ? new Color(0xFFFFFF) : new Color(0xF0F0F0)); // Zebra stripes
                }
                return component;
            }
        };

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(true);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    // Method to set up control panel with buttons
    private void setupControlPanel() {
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controlPanel.setBorder(new EmptyBorder(15, 25, 15, 25));
        controlPanel.setBackground(new Color(90, 23, 139));

        // Add buttons to control panel
        String[] buttonTitles = {"Search", "Add", "Edit", "Delete"};
        for (String title : buttonTitles) {
            JButton button = new JButton(title);
            button.addActionListener(this::onButtonClick); // Simplified action listener
            controlPanel.add(button);
        }

        textField = new JTextField(10);
        textField.setBackground(new Color(246, 248, 250));
        controlPanel.add(textField);

        frame.getContentPane().add(controlPanel, BorderLayout.NORTH);
    }

 

    // Method to set up bottom panel for reports
    private void setupBottomPanel() {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        bottomPanel.setBackground(new Color(90, 23, 139));

        String[] reportTitles = {"Monthly Report", "Yearly Report"};
        for (String title : reportTitles) {
            JButton button = new JButton(title);
            bottomPanel.add(button);
        }

        frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
    }
    // Method to handle button clicks
    private void onButtonClick(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Search":
                searchOrders();
                break;
            case "Add":
                addOrder();
                break;
            case "Edit":
                editOrder();
                break;
            case "Delete":
                deleteOrder();
                break;
            case "Monthly Report":
                generateMonthlyReport();
                break;
            case "Yearly Report":
                generateYearlyReport();
                break;
            default:
                JOptionPane.showMessageDialog(frame, "Unknown action: " + command);
                break;
        }
    }

    private void searchOrders() {
        String searchText = textField.getText();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        if (searchText.trim().length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter(searchText));
        }
    }

    private void addOrder() {
        // Example: Using JOptionPane for input (replace with a more complex dialog as needed)
        String newOrder = JOptionPane.showInputDialog(frame, "Enter new order details:");
        if (newOrder != null && !newOrder.trim().isEmpty()) {
            // Assuming newOrder is a comma-separated string of order details
            String[] details = newOrder.split(",");
            tableModel.addRow(details); // Add new row to the table
            // TODO: Add logic to insert the new order into the database
        }
    }


    private void editOrder() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            // Example: Edit the status of the order
            String newStatus = JOptionPane.showInputDialog(frame, "Enter new status:");
            if (newStatus != null && !newStatus.trim().isEmpty()) {
                tableModel.setValueAt(newStatus, selectedRow, 2); // Assuming status is in column 2
                // TODO: Update the order in the database
            }
        } else {
            JOptionPane.showMessageDialog(frame, "No order selected.");
        }
    }


    private void deleteOrder() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            // Confirm before deleting
            int confirm = JOptionPane.showConfirmDialog(frame, "Delete this order?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                tableModel.removeRow(selectedRow);
                // TODO: Delete the order from the database
            }
        } else {
            JOptionPane.showMessageDialog(frame, "No order selected.");
        }
    }

    private void generateMonthlyReport() {
        // TODO: Implement logic to generate and display a monthly report
        JOptionPane.showMessageDialog(frame, "Monthly Report generated (implement logic).");
    }


    private void generateYearlyReport() {
        // TODO: Implement logic to generate and display a yearly report
        JOptionPane.showMessageDialog(frame, "Yearly Report generated (implement logic).");
    }



        // Method to fetch and display orders from the database
        private void fetchAndDisplayOrders() {
            try (Connection conn = DriverManager.getConnection(dbURL, user, password);
                 Statement statement = conn.createStatement()) {

                String sql = "SELECT orderNumber, orderDate, status, customerNumber FROM orders";
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    Object[] row = {
                        resultSet.getString("orderNumber"),
                        resultSet.getString("orderDate"),
                        resultSet.getString("status"),
                        resultSet.getString("customerNumber")
                    };
                    tableModel.addRow(row);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
    
    


