package com.desktopAppExamen.handler;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.desktopAppExamen.Order;
import com.desktopAppExamen.connection.DataBaseConnection;

public class OrderHandlerController {

    public boolean addOrder(Order order) {
        String insertOrderSQL = "INSERT INTO orders (orderDate, status) VALUES (?, ?)";
        
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertOrderSQL)) {
            
            pstmt.setDate(1, new Date(order.getOrderDate().getTime()));
            pstmt.setString(2, order.getStatus());
            pstmt.setInt(3, order.getOrderNr());

            int affectedRows = pstmt.executeUpdate();
            
            return affectedRows > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editOrder(Order order) {
        String updateOrderSQL = "UPDATE orders SET orderDate = ?, status = ? WHERE orderId = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateOrderSQL)) {

            pstmt.setDate(1, new Date(order.getOrderDate().getTime()));
            pstmt.setString(2, order.getStatus());
            pstmt.setInt(3, order.getOrderNr());

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

    public Order getOrder(int orderId) {
        String selectOrderSQL = "SELECT * FROM orders WHERE orderId = ?";
        Order order = null;
        
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(selectOrderSQL)) {
            
            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Date orderDate = rs.getDate("orderDate");
                String status = rs.getString("status");
                // You should retrieve all the other fields of the order
                
                order = new Order(orderNr, orderDate, status);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return order;
    }
}