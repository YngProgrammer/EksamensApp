package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.DataBaseConnection;
import modelPack.Order;

public class OrderHandler {

    // CRUD-methods

    // Create
    public boolean addOrder(Order order) {
        String insertOrderSQL = "INSERT INTO orders (orderDate, status) VALUES (?, ?)";
        
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertOrderSQL)) {
            
            pstmt.setDate(1, new Date(order.getOrderDate().getTime()));
            pstmt.setString(2, order.getStatus());

            int affectedRows = pstmt.executeUpdate();
            
            return affectedRows > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

 // Update Albert
    public boolean editOrder(Order order) {
        String updateOrderSQL = "UPDATE orders SET orderDate = ?, requiredDate = ?, shippedDate = ?, status = ?, comments = ? WHERE orderId = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateOrderSQL)) {

            pstmt.setDate(1, new java.sql.Date(order.getOrderDate().getTime()));
            pstmt.setDate(2, order.getRequiredDate() != null ? new java.sql.Date(order.getRequiredDate().getTime()) : null);
            pstmt.setDate(3, order.getShippedDate() != null ? new java.sql.Date(order.getShippedDate().getTime()) : null);
            pstmt.setString(4, order.getStatus());
            pstmt.setString(5, order.getComments());
            pstmt.setInt(6, order.getOrderNr());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                System.out.println("Update failed, no rows affected.");
                return false;
            }

            return true;

        } catch (SQLException e) {
            System.out.println("Update failed due to SQL exception: " + e.getMessage());
            return false;
        }
    }


    // Delete
    public boolean deleteOrder(int orderId) {
        String deleteOrderSQL = "DELETE FROM orders WHERE orderId = ?";
        
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(deleteOrderSQL)) {
            
            pstmt.setInt(1, orderId);
            
            int affectedRows = pstmt.executeUpdate();
            
            return affectedRows > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Read
    public Order getOrder(int orderId) {
        String selectOrderSQL = "SELECT * FROM orders WHERE orderId = ?";
        Order order = null;
        
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(selectOrderSQL)) {
            
            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                int orderNr = rs.getInt("orderNr");
                Date orderDate = rs.getDate("orderDate");
                Date requiredDate = rs.getDate("requiredDate");
                Date shippedDate = rs.getDate("shippedDate");
                String status = rs.getString("status");
                String comments = rs.getString("comments");
                
                order = new Order(orderNr, orderDate, requiredDate, shippedDate, status, comments);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return order;
    }


}
