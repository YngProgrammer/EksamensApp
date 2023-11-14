package view;

import model.Products;
import controller.ProductController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductView extends JFrame {
    private DefaultListModel<Products> productListModel;
    private JList<Products> productList;
    private JButton addButton;
    private JButton updateButton;
    private JButton removeButton;
    private ProductController productController;

    public ProductView(ProductController controller) {
        this.productController = controller;
        productListModel = new DefaultListModel<>();
        productList = new JList<>(productListModel);
        addButton = new JButton("Add Product");
        updateButton = new JButton("Update Product");
        removeButton = new JButton("Remove Product");

        // ActionListener for the "Add Product" button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddProductDialog();
            }
        });

        // ActionListener for the "Update Product" button
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = productList.getSelectedIndex();
                if (selectedIndex != -1) {
                    // Get the selected product and show the update dialog
                    Products selectedProduct = productListModel.getElementAt(selectedIndex);
                    showUpdateProductDialog(selectedProduct);
                }
            }
        });

        // ActionListener for the "Remove Product" button
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = productList.getSelectedIndex();
                if (selectedIndex != -1) {
                    // Get the selected product, remove it, and update the display
                    Products selectedProduct = productListModel.getElementAt(selectedIndex);
                    productController.removeProduct(selectedProduct);
                    updateProductDisplay();
                }
            }
        });

        // Setting up the main panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JScrollPane(productList), BorderLayout.CENTER);

        // Setting up the button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(removeButton);

        // Adding button panel to the main panel
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Setting up the JFrame
        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // Adjusts the size of the frame based on its components
        setLocationRelativeTo(null); // Centers the frame on the screen
        setVisible(true);
    }

    // Method to show the "Add Product" dialog
    private void showAddProductDialog() {
        AddProductDialog dialog = new AddProductDialog(this, productController);
        dialog.setVisible(true);
        updateProductDisplay(); // Update the display after adding a product
    }

    // Method to show the "Update Product" dialog
    private void showUpdateProductDialog(Products product) {
        UpdateProductDialog dialog = new UpdateProductDialog(this, productController, product);
        dialog.setVisible(true);
        updateProductDisplay(); // Update the display after updating a product
    }

    // Method to update the view based on the model
    public void updateProductDisplay() {
        productListModel.clear();
        // Get the latest list of products from the controller and update the display
        for (Products product : productController.getProducts()) {
            productListModel.addElement(product);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Initialize the controller and view
                ProductController controller = new ProductController();
                ProductView view = new ProductView(controller);
                // Set the view in the controller to enable communication
                controller.setView(view);
                // Update the initial product display
                view.updateProductDisplay();
            }
        });
    }
}
