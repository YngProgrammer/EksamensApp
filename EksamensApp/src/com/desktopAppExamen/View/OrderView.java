package com.desktopAppExamen.View;

import com.desktopAppExamen.Controller.OrderHandlerController;
import com.desktopAppExamen.ModelPack.Order;
import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class OrderView {

    private OrderHandlerController orderHandlerController;
    private Scanner scanner;

    public OrderView() {
        orderHandlerController = new OrderHandlerController();
        scanner = new Scanner(System.in);
    }
    
    public void showOrder(int orderId) {
        Order order = orderHandlerController.getOrder(orderId);
        if (order != null) {
            System.out.println("Order ID: " + order.getOrderNr());
            System.out.println("Order Date: " + order.getOrderDate());
            System.out.println("Required Date: " + order.getRequiredDate());
            System.out.println("Shipped Date: " + order.getShippedDate());
            System.out.println("Status: " + order.getStatus());
            System.out.println("Comments: " + order.getComments());
        } else {
            System.out.println("No order found with ID: " + orderId);
        }
    }

    public void addOrder() {
        System.out.println("Enter order date (yyyy-MM-dd):");
        String date = scanner.next();
        System.out.println("Enter order status:");
        String status = scanner.next();
        
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date orderDate = formatter.parse(date);
            
            Order newOrder = new Order();  // Assuming there is a default constructor
            newOrder.setOrderDate(orderDate);
            newOrder.setStatus(status);
            
            boolean isSuccess = orderHandlerController.addOrder(newOrder);
            if (isSuccess) {
                System.out.println("Order added successfully.");
            } else {
                System.out.println("Failed to add order.");
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format.");
        }
    }
    
    public void editOrder() {
        System.out.println("Enter the ID of the order you want to edit:");
        int orderId = scanner.nextInt();
        Order order = orderHandlerController.getOrder(orderId);

        if (order == null) {
            System.out.println("Order not found!");
            return;
        }

        System.out.println("Editing Order: " + order.getOrderNr());
        System.out.println("Current Status: " + order.getStatus());
        System.out.println("Enter new status (leave blank to keep current):");
        scanner.nextLine(); // Consume the leftover newline
        String status = scanner.nextLine();

        if (!status.isEmpty()) {
            order.setStatus(status);
        }

        System.out.println("Enter new comments (leave blank to keep current):");
        String comments = scanner.nextLine();

        if (!comments.isEmpty()) {
            order.setComments(comments);
        }

        // Assuming setters for date fields and a method to parse String to Date in Order class
        System.out.println("Enter new order date (yyyy-MM-dd) (leave blank to keep current):");
        String newOrderDate = scanner.nextLine();

        if (!newOrderDate.isEmpty()) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date orderDate = formatter.parse(newOrderDate);
                order.setOrderDate(orderDate);
            } catch (ParseException e) {
                System.out.println("Invalid date format.");
                return;
            }
        }

        // Perform the update operation
        boolean isUpdated = orderHandlerController.editOrder(order);

        if (isUpdated) {
            System.out.println("Order updated successfully.");
        } else {
            System.out.println("Failed to update order.");
        }
    }

    public void deleteOrder() {
        System.out.println("Enter the ID of the order you want to delete:");
        int orderId = scanner.nextInt();

        boolean isDeleted = orderHandlerController.deleteOrder(orderId);

        if (isDeleted) {
            System.out.println("Order deleted successfully.");
        } else {
            System.out.println("Failed to delete order.");
        }
    }
}