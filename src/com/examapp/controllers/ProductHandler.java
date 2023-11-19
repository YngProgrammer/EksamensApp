/**
 * File: ProductHandler.java
 * Description: This class serves as the controller for managing products in the application. It provides methods for creating, updating, and deleting products in the database, as well as searching for products and adding them to orders.
 * The class interacts with the model (Product) and the database to handle product-related operations.
 * @author Ole
 * @version 08.11.2023
 */

package com.examapp.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.examapp.DataBaseConnection;
import com.examapp.views.ProductView;
import com.examapp.models.Products;
public class ProductHandler {

    // CRUD Methods

    public boolean createProduct(Products product) {
        try (Connection connection = DataBaseConnection.getConnection()) {
            String sql = "INSERT INTO products (productCode, productName, productScale, productVendor, productDescription, quantityInStock, buyPrice, MSRP) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getProductCode());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setString(3, product.getProductScale());
            preparedStatement.setString(4, product.getProductVendor());
            preparedStatement.setString(5, product.getProductDescription());
            preparedStatement.setInt(6, product.getQuantityInStock());
            preparedStatement.setDouble(7, product.getBuyPrice());
            preparedStatement.setDouble(8, product.getMsrp());
            int affectedRows = preparedStatement.executeUpdate();
            
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProduct(Products product) {
        try (Connection connection = DataBaseConnection.getConnection()) {
            String sql = "UPDATE products SET productName = ?, productScale = ?, productVendor = ?, productDescription = ?, quantityInStock = ?, buyPrice = ?, MSRP = ? WHERE productCode = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getProductScale());
            preparedStatement.setString(3, product.getProductVendor());
            preparedStatement.setString(4, product.getProductDescription());
            preparedStatement.setInt(5, product.getQuantityInStock());
            preparedStatement.setDouble(6, product.getBuyPrice());
            preparedStatement.setDouble(7, product.getMsrp());
            preparedStatement.setString(8, product.getProductCode());
            int affectedRows = preparedStatement.executeUpdate();
            
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteProduct(String productCode) {
        try (Connection connection = DataBaseConnection.getConnection()) {
            String sql = "DELETE FROM products WHERE productCode = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productCode);
            int affectedRows = preparedStatement.executeUpdate();
            
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // Business Methods

    public boolean addToOrder(Products product, int orderId, int quantity) {
        try (Connection connection = DataBaseConnection.getConnection()) {
            String sql = "INSERT INTO order_details (orderNumber, productCode, quantityOrdered) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, orderId);
            preparedStatement.setString(2, product.getProductCode());
            preparedStatement.setInt(3, quantity);
            int affectedRows = preparedStatement.executeUpdate();
            
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

	public void setView(ProductView view) {
		// TODO Auto-generated method stub      // productVuew.java ville ha denne generert pga setview(view) finn ut hvorfor
		
	}
}
