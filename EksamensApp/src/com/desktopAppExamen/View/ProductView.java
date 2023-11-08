package com.desktopAppExamen.View;

import javax.swing.*;

import com.desktopAppExamen.ModelPack.Products.Products;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductView {
    private JFrame frame;
    private DefaultListModel<Products> productListModel;
    private JList<Products> productList;
    private JButton addButton;
    private JButton removeButton;

    public ProductView() {
        frame = new JFrame("Product Management");
        productListModel = new DefaultListModel<>();
        productList = new JList<>(productListModel);
        addButton = new JButton("Add Product");
        removeButton = new JButton("Remove Product");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement the logic to add a product to the list
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement the logic to remove a product from the list
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(productList, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    public void addProduct(Products product) {
        productListModel.addElement(product);
    }

    public void removeProduct(Products product) {
        productListModel.removeElement(product);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProductView();
            }
        });
    }
}
