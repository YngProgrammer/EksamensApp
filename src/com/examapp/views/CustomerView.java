package com.examapp.views;

import javax.swing.*;

import com.examapp.controllers.CustomerHandler; // Import the customer controller
import com.examapp.models.Customer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerView {
    private JFrame frame;
    private DefaultListModel<Customer> customerListModel;
    private JList<Customer> customerList;
    private JButton addButton;
    private JButton removeButton;
    private JButton updateButton;
    private CustomerHandler customerHandler; // Add a reference to the customer controller

    public CustomerView() {
        frame = new JFrame("Customer Management");
        customerListModel = new DefaultListModel<>();
        customerList = new JList<>(customerListModel);
        addButton = new JButton("Add Customer");
        removeButton = new JButton("Remove Customer");
        updateButton = new JButton("Update Customer");
        customerHandler = new CustomerHandler(); // Initialize the customer controller

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement the logic to add a customer to the list
                addCustomer();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement the logic to remove a customer from the list
                removeCustomer();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement the logic to update a customer in the list
                updateCustomer();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(customerList, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(updateButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    public void addCustomer(Customer customer) {
        customerListModel.addElement(customer);
    }

    public void removeCustomer() {
        // Get the selected customer from the list
        Customer selectedCustomer = customerList.getSelectedValue();
        
        if (selectedCustomer != null) {
            // Remove the selected customer from the list
            customerListModel.removeElement(selectedCustomer);
            
            // Also remove the customer from the controller
            customerHandler.deleteCustomer(selectedCustomer.getcustomerNr()); // Assuming you have a method in the controller to delete a customer
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a customer to remove.");
        }
    }

    private void updateCustomer() {
        // Get the selected customer from the list
        Customer selectedCustomer = customerList.getSelectedValue();
        
        if (selectedCustomer != null) {
            // Implement the logic to update the selected customer
            // You might open a dialog to allow the user to edit customer details and then update the list and controller accordingly
            // For simplicity, I'll just print a message for now
            JOptionPane.showMessageDialog(frame, "Updating customer: " + selectedCustomer.getcustomerNr());
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a customer to update.");
        }
    }

    private void addCustomer() {
        // Implement the logic to add a customer to the list
        // You might open a dialog to allow the user to enter customer details and then update the list and controller accordingly
        // For simplicity, I'll just print a message for now
        JOptionPane.showMessageDialog(frame, "Adding a new customer.");
    }

  
    
}
