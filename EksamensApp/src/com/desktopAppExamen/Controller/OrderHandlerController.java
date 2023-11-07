package com.desktopAppExamen.Controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.desktopAppExamen.ModelPack.Order;
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