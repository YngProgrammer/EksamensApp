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
    private JButton removeButton;
    private ProductController productController;

    public ProductView(ProductController controller) {
        this.productController = controller;
        productListModel = new DefaultListModel<>();
        productList = new JList<>(productListModel);
        addButton = new JButton("Add Product");
        removeButton = new JButton("Remove Product");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement the logic to add a product to the list
                // For now, let's add a dummy product
                Products newProduct = new Products("NewProductCode", "NewProductName", "NewScale", "NewVendor", "NewDescription", 0, 0.0, 0.0);
                productController.addProduct(newProduct);
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement the logic to remove a product from the list
                int selectedIndex = productList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Products selectedProduct = productListModel.getElementAt(selectedIndex);
                    productController.removeProduct(selectedProduct);
                }
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

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Method to update the view based on the model
    public void updateProductDisplay() {
        // Get products from the model and update the product list
        productListModel.removeAllElements();
        for (Products product : productController.getProducts()) {
            productListModel.addElement(product);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ProductController controller = new ProductController();
                ProductView view = new ProductView(controller);
                controller.setView(view); // Set the view in the controller for updates
                view.updateProductDisplay(); // Update the view with existing products from the model
            }
        });
    }
}
