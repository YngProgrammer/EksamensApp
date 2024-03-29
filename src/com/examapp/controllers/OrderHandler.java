/**
 * File: OrderHandler.java
 * Description:
 * Manages order operations (add, update, delete, retrieve) within a CMS, ensuring proper handling and status tracking of orders.
 * @author Hussein
 * @version 09.11.2023
 */


package com.examapp.controllers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.examapp.DataBaseConnection;
import com.examapp.models.Order;


public class OrderHandler {


    // CRUD-methods

    // Create
	public boolean addOrder(Order order) {
	    String insertOrderSQL = "INSERT INTO orders (orderNumber, orderDate, requiredDate, shippedDate, status, comments, customerNumber) VALUES (?, ?, ?, ?, ?, ?, ?)";
	    
	    try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertOrderSQL)) {
	        pstmt.setInt(1, order.getOrderNr());
	        pstmt.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
	        pstmt.setDate(3, order.getRequiredDate() != null ? new java.sql.Date(order.getRequiredDate().getTime()) : null);
	        pstmt.setDate(4, order.getShippedDate() != null ? new java.sql.Date(order.getShippedDate().getTime()) : null);
	        pstmt.setString(5, order.getStatus());
	        pstmt.setString(6, order.getComments());
	        // Make sure to set the customerNumber or any other missing fields
	        pstmt.setString(7, order.getCustomerNumber()); // Assuming getCustomerNumber() is a method in your Order class

	        int affectedRows = pstmt.executeUpdate();
	        
	        return affectedRows > 0;
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

    // Update
    public boolean editOrder(Order order) {
        String updateOrderSQL = "UPDATE orders SET orderDate = ?, requiredDate = ?, shippedDate = ?, status = ?, comments = ?, customerNumber WHERE orderNumber = ?";
        
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateOrderSQL)) {
            
            pstmt.setDate(1, new java.sql.Date(order.getOrderDate().getTime()));
            pstmt.setDate(2, order.getRequiredDate() != null ? new java.sql.Date(order.getRequiredDate().getTime()) : null);
            pstmt.setDate(3, order.getShippedDate() != null ? new java.sql.Date(order.getShippedDate().getTime()) : null);
            pstmt.setString(4, order.getStatus());
            pstmt.setString(5, order.getComments());
            pstmt.setInt(6, order.getOrderNr());

            int affectedRows = pstmt.executeUpdate();

            return affectedRows > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete
    public boolean deleteOrder(int orderNr) {
        String deleteOrderSQL = "DELETE FROM orders WHERE orderNumber = ?";
        
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(deleteOrderSQL)) {
            
            pstmt.setInt(1, orderNr);
            
            int affectedRows = pstmt.executeUpdate();
            
            return affectedRows > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Read
    public Order getOrder(int orderNr) {
        String selectOrderSQL = "SELECT * FROM orders WHERE orderNumber = ?";
        Order order = null;

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(selectOrderSQL)) {

            pstmt.setInt(1, orderNr);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Date orderDate = rs.getDate("orderDate");
                Date requiredDate = rs.getDate("requiredDate");
                Date shippedDate = rs.getDate("shippedDate");
                String status = rs.getString("status");
                String comments = rs.getString("comments");
                String customerNumber = rs.getString("customerNumber");

                order = new Order(orderNr, orderDate, requiredDate, shippedDate, status, comments, customerNumber);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }



}
