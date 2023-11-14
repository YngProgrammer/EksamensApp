package View;

import javax.swing.*;
import modelPack.Customers.Customer;

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

    public CustomerView() {
        frame = new JFrame("Customer Management");
        customerListModel = new DefaultListModel<>();
        customerList = new JList<>(customerListModel);
        addButton = new JButton("Add Customer");
        removeButton = new JButton("Remove Customer");
        updateButton = new JButton("Update Customer");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement the logic to add a customer to the list
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement the logic to remove a customer from the list
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

    public void removeCustomer(Customer customer) {
        customerListModel.removeElement(customer);
    }

    private void updateCustomer() {
        // Implement the logic to update a customer in the list
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CustomerView();
            }
        });
    }
}
