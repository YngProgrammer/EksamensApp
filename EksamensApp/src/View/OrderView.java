package View;

import java.util.Scanner;

import controller.OrderHandler;
import model.Order;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class OrderView {

    private OrderHandler orderHandlerController;
    private Scanner scanner;

    public OrderView() {
        orderHandlerController = new OrderHandler();
        scanner = new Scanner(System.in);
    }
    
    public void showOrder(int orderNr) {
        Order order = orderHandlerController.getOrder(orderNr);
        if (order != null) {
            System.out.println("Order ID: " + order.getOrderNr());
            System.out.println("Order Date: " + order.getOrderDate());
            System.out.println("Required Date: " + order.getRequiredDate());
            System.out.println("Shipped Date: " + order.getShippedDate());
            System.out.println("Status: " + order.getStatus());
            System.out.println("Comments: " + order.getComments());
        } else {
            System.out.println("No order found with ID: " + orderNr);
        }
    }

    public void addOrder() {
        System.out.println("Enter the unique order number (5 digits):");
        int orderNr = scanner.nextInt(); // Assume that the order number is entered correctly

        System.out.println("Enter order date (yyyy-MM-dd):");
        String date = scanner.next();
        
        // Since required date and shipped date are not asked from the user,
        // you might want to assume they are null or set a default value.
        Date requiredDate = null; // or some default date if needed
        Date shippedDate = null;  // or some default date if needed

        System.out.println("Enter order status:");
        String status = scanner.next();
        
        // Comments are not asked from the user, assuming empty for now
        String comments = ""; 

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date orderDate = formatter.parse(date);
            
            // Use the constructor that matches the parameters you have
            Order newOrder = new Order(orderNr, orderDate, requiredDate, shippedDate, status, comments);
            
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
        int orderNr = scanner.nextInt();
        Order order = orderHandlerController.getOrder(orderNr);

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

        boolean isUpdated = orderHandlerController.editOrder(order);

        if (isUpdated) {
            System.out.println("Order updated successfully.");
        } else {
            System.out.println("Failed to update order.");
        }
    }

    public void deleteOrder() {
        System.out.println("Enter the ID of the order you want to delete:");
        int orderNr = scanner.nextInt();

        boolean isDeleted = orderHandlerController.deleteOrder(orderNr);

        if (isDeleted) {
            System.out.println("Order deleted successfully.");
        } else {
            System.out.println("Failed to delete order.");
        }
    }
    
    // It is good practice to add a close method to properly close the scanner when the view is disposed of
    public void close() {
        if(scanner != null) {
            scanner.close();
        }
    }
}
