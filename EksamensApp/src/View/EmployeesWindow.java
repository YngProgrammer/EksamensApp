/**
 * File: EmployeesWindow.java
 * Description:
 * Provides a graphical user interface to interact with employee records in a database.
 * It enables the user to add, edit, search, and delete employee information via a form-based interface.
 * The class extends DataBaseConnection to handle database operations and updates the display dynamically.
 * Author: Hussein
 * Version: 09.11.2023
 */

package View;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import connection.DataBaseConnection;

import javax.swing.event.*;

public class EmployeesWindow extends DataBaseConnection {
    private JFrame frame;
    private JTable table;
    private JTextArea textArea;
    private DefaultTableModel tableModel;
    private int selectedRowIndex = -1;


    public void searchEmployees(String searchText) {
        try {
            Connection con = getConnection(); // Use the getConnection method from the superclass
            String query = "SELECT * FROM employees WHERE firstName LIKE ? OR lastName LIKE ? OR employeeNumber LIKE ? OR extension LIKE ? OR email LIKE ? OR officepassword LIKE ? OR reportsTo LIKE ? OR jobTitle LIKE ? OR Role LIKE ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            for (int i = 1; i <= 8; i++) {
                pstmt.setString(i, "%" + searchText + "%");
            }

            ResultSet rs = pstmt.executeQuery();
            tableModel.setRowCount(0);

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= tableModel.getColumnCount() - 1; i++) {
                    row.add(rs.getObject(i));
                }
                row.add(""); // Initialize the notes column with an empty string
                tableModel.addRow(row);
            }

            rs.close();
            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getEmployeeData() {
        try {
            Connection con = getConnection(); // Use the getConnection method from the superclass
            Statement st = con.createStatement();
            String query = "SELECT * FROM employees";
            ResultSet rs = st.executeQuery(query);

            tableModel = new DefaultTableModel() {
                private static final long serialVersionUID = 1L; // Added serialVersionUID

                @Override
                public boolean isCellEditable(int row, int column) {
                    // Make the notes column editable
                    return column == getColumnCount() - 1;
                }
            };

            table.setModel(tableModel);

            int cols = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= cols; i++) {
                tableModel.addColumn(rs.getMetaData().getColumnName(i));
            }

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= cols; i++) {
                    row.add(rs.getObject(i));
                }
                row.add(""); // Initialize the notes column with an empty string
                tableModel.addRow(row);
            }

            rs.close();
            st.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public EmployeesWindow() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Information Finder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.getContentPane().setLayout(new BorderLayout());

        // Set the selection background color
        UIManager.put("Table.selectionBackground", new Color(0x3ec1a5));

        // Create a custom renderer with padding and row color changes
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                ((JComponent) component).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adjust padding here

                // Change background color when row is selected
                if (isSelected) {
                    component.setBackground(new Color(0x3ec1a5)); // Selected row color
                } else {
                    // Change background color when hovering
                    if (table.isRowSelected(row)) {
                        component.setBackground(new Color(0x3ec1a5)); // Hovered row color
                    } else {
                        component.setBackground(Color.WHITE); // Default row color
                    }
                }

                return component;
            }
        };

        // Initialize the table with the custom renderer
        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow selecting only one row
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedRowIndex = table.getSelectedRow();
            }
        });

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Create a panel for the table
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(new Color(0x034e3a));

        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Create a panel for control buttons and text input
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT )); 
        controlPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
        controlPanel.setBackground(new Color(0x3ec1a5));
        textArea = new JTextArea(1, 20);
        JButton btnAdd = new JButton("Add");
        JButton btnEdit = new JButton("Edit");
        JButton btnDelete = new JButton("Delete");
        btnAdd.setBackground(new Color(0x2ecc71));
        btnAdd.setForeground(Color.BLACK);
        btnEdit.setBackground(new Color(0xf1c40f));
        btnEdit.setForeground(Color.BLACK);
        btnDelete.setBackground(new Color(0xe74c3c));
        btnDelete.setForeground(Color.BLACK);
        controlPanel.add(textArea);
        
                JButton btnSearch = new JButton("Search");
                btnSearch.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
					}
                });
                
                        // Set button background color and text color
                        btnSearch.setBackground(new Color(0x2ecc71));
                        btnSearch.setForeground(Color.BLACK);
                        
                                controlPanel.add(btnSearch);
                                
                                        // Action listeners for buttons
                                        btnSearch.addActionListener(e -> searchEmployees(textArea.getText()));
        controlPanel.add(btnAdd);
        controlPanel.add(btnEdit);
        controlPanel.add(btnDelete);

        // Create a panel for the entire content
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(tablePanel, BorderLayout.CENTER);
        contentPanel.add(controlPanel, BorderLayout.NORTH);

        frame.getContentPane().add(contentPanel);
        getEmployeeData();
        btnAdd.addActionListener(e -> openAddDialog());
        btnEdit.addActionListener(e -> openEditDialog());
        btnDelete.addActionListener(e -> deleteSelectedRow());

        // Save notes when table cell editing is stopped
        table.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int row = e.getFirstRow();
                    int col = e.getColumn();
                    if (col == table.getColumnCount() - 1) {
                        String notes = (String) tableModel.getValueAt(row, col);
                        // Get the employee ID or another unique identifier from your table, assuming it's in the first column
                        int employeeID = (int) tableModel.getValueAt(row, 0);

                        // Update the database with the new notes
                        try {
                            Connection con = DriverManager.getConnection(dbUrl, user, password);
                            String updateQuery = "UPDATE employees SET notes = ? WHERE employeeID = ?";
                            PreparedStatement pstmt = con.prepareStatement(updateQuery);
                            pstmt.setString(1, notes);
                            pstmt.setInt(2, employeeID);
                            pstmt.executeUpdate();
                            pstmt.close();
                            con.close();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    private void deleteSelectedRow() {
        if (selectedRowIndex != -1) {
            int employeeID = (int) tableModel.getValueAt(selectedRowIndex, 0);
            deleteData(employeeID);
            tableModel.removeRow(selectedRowIndex);
            selectedRowIndex = -1;
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a row to delete.");
        }
    }

    private void openEditDialog() {
        if (selectedRowIndex != -1) {
            // Create a dialog to edit the data
            JPanel panel = new JPanel(new GridLayout(0, 2)); // 2 columns

            // Define the labels for each field
            String[] columnNames = {
                "First Name", "Last Name", "Employee Number",
                "Extension", "Email", "Office password", "Reports To", "Job Title", "Role"
            };

            // Create an array to store edited values
            String[] editedValues = new String[columnNames.length];

            for (int i = 0; i < columnNames.length; i++) {
                String columnName = columnNames[i];
                Object originalValue = tableModel.getValueAt(selectedRowIndex, i);
                JTextField editedField = new JTextField(originalValue.toString());

                JLabel label = new JLabel(columnName + ":");
                panel.add(label); // Corrected line
                panel.add(editedField);

                // Store the edited value in the array
                editedValues[i] = editedField.getText();
            }

            int result = JOptionPane.showConfirmDialog(frame, panel, "Edit Employee", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                // Update the data in the JTable
                for (int i = 0; i < columnNames.length; i++) {
                    tableModel.setValueAt(editedValues[i], selectedRowIndex, i);
                }

                // You will need to write password here to update the data in your database
                int employeeID = (int) tableModel.getValueAt(selectedRowIndex, 0);
                updateData(employeeID, editedValues);
            }

            // Request focus to prevent double-click behavior
            panel.requestFocusInWindow();
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a row to edit.");
        }
    }


    private void openAddDialog() {
        // Create a dialog to input new data
        JPanel panel = new JPanel(new GridLayout(0, 2)); // 2 columns

        // Define the labels for each field
        String[] columnNames = {
            "First Name", "Last Name", "Employee Number",
            "Extension", "Email", "Office password", "Reports To", "Job Title", "Role"
        };

        JTextField[] inputFields = new JTextField[columnNames.length]; // Store the input fields

        for (int i = 0; i < columnNames.length; i++) {
            String columnName = columnNames[i];
            JLabel label = new JLabel(columnName);
            JTextField textField = new JTextField();
            panel.add(label);
            panel.add(textField);
            inputFields[i] = textField; // Store the input field
        }

        int result = JOptionPane.showConfirmDialog(frame, panel, "Add Employee", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            // Add the new data to the JTable
            Vector<Object> newRow = new Vector<>();
            for (JTextField textField : inputFields) {
                newRow.add(textField.getText());
            }
            newRow.add(""); // Initialize the notes column with an empty string

            tableModel.addRow(newRow);

            // You will need to write password here to add the data to your database
            insertData(newRow);
        }

        // Request focus to prevent double-click behavior
        panel.requestFocusInWindow();
    }

    private void deleteData(int employeeID) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DriverManager.getConnection(dbUrl, user, password);
            String deleteQuery = "DELETE FROM employees WHERE employeeID = ?";
            pstmt = con.prepareStatement(deleteQuery);
            pstmt.setInt(1, employeeID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateData(int employeeID, String[] editedValues) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DriverManager.getConnection(dbUrl, user, password);
            String updateQuery = "UPDATE employees SET firstName = ?, lastName = ?, employeeNumber = ?, extension = ?, email = ?, officepassword = ?, reportsTo = ?, jobTitle = ?, Role = ? WHERE employeeID = ?";
            pstmt = con.prepareStatement(updateQuery);
            for (int i = 0; i < editedValues.length; i++) {
                pstmt.setString(i + 1, editedValues[i]);
            }
            pstmt.setInt(editedValues.length + 1, employeeID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void insertData(Vector<Object> newRow) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DriverManager.getConnection(dbUrl, user, password);
            String insertQuery = "INSERT INTO employees (firstName, lastName, employeeNumber, extension, email, officepassword, reportsTo, jobTitle, Role, notes) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(insertQuery);

            // Set values for the prepared statement
            pstmt.setString(1, newRow.get(0).toString()); // First Name
            pstmt.setString(2, newRow.get(1).toString()); // Last Name
            pstmt.setString(3, newRow.get(2).toString()); // Employee Number
            pstmt.setString(4, newRow.get(3).toString()); // Extension
            pstmt.setString(5, newRow.get(4).toString()); // Email
            pstmt.setString(6, newRow.get(5).toString()); // Office code
            pstmt.setString(7, newRow.get(6).toString()); // Reports To
            pstmt.setString(8, newRow.get(7).toString()); // Job Title
            pstmt.setString(9, newRow.get(8).toString()); // Role
            pstmt.setString(10, newRow.get(9).toString()); // Notes

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
