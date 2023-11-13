/**
 * File: OrderView.java
 * Description:
 * Provides a user interface in the console for managing orders. 
 * It allows users to add, edit, display, and delete orders through a command-line interface, leveraging the OrderHandler for processing.
 * Author: Hussein
 * Version: 09.11.2023
 */

package View;

import controller.OrderHandler;
import modelPack.Order;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderView implements AutoCloseable {
    private OrderHandler orderHandler;
    private Scanner scanner;

    public OrderView() {
        this.orderHandler = new OrderHandler();
        this.scanner = new Scanner(System.in);
    }

    public void displayMainMenu() {
        boolean quit = false;
        while (!quit) {
            System.out.println("Please choose an option:");
            System.out.println("1. Add Order");
            System.out.println("2. Edit Order");
            System.out.println("3. Delete Order");
            System.out.println("4. Show Order");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addOrder();
                    break;
                case 2:
                    editOrder();
                    break;
                case 3:
                    deleteOrder();
                    break;
                case 4:
                    showOrderPrompt();
                    break;
                case 5:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please enter a number between 1 and 5.");
            }
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
            
            Order newOrder = new Order(orderNr, orderDate, requiredDate, shippedDate, status, comments);
            
            boolean isSuccess = orderHandler.addOrder(newOrder);
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
        Order order = orderHandler.getOrder(orderNr);

        if (order == null) {
            System.out.println("Order not found!");
            return;
        }

        System.out.println("Editing Order: " + order.getOrderNr());
        System.out.println("Current Status: " + order.getStatus());
        System.out.println("Enter new status (leave blank to keep current):");
        scanner.nextLine(); // Consume the leftover newline from nextInt()
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
        String newOrderDateStr = scanner.nextLine();
        if (!newOrderDateStr.isEmpty()) {
            try {
                Date newOrderDate = new SimpleDateFormat("yyyy-MM-dd").parse(newOrderDateStr);
                order.setOrderDate(newOrderDate);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                return;
            }
        }

        // Assuming similar patterns and data entry are required for requiredDate and shippedDate
        // You would repeat the above logic for those dates as well

        boolean isUpdated = orderHandler.editOrder(order);
        if (isUpdated) {
            System.out.println("Order updated successfully.");
        } else {
            System.out.println("Failed to update order.");
        }
    }
    
    public void deleteOrder() {
        System.out.println("Enter the ID of the order you want to delete:");
        int orderNr = scanner.nextInt();

        boolean isDeleted = orderHandler.deleteOrder(orderNr);
        if (isDeleted) {
            System.out.println("Order deleted successfully.");
        } else {
            System.out.println("Failed to delete order.");
        }
    }

    public void showOrderPrompt() {
        System.out.println("Enter the ID of the order you want to display:");
        int orderNr = scanner.nextInt();
        Order order = orderHandler.getOrder(orderNr);

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

    // It is good practice to add a close method to properly close the scanner when the view is disposed of
    // Implement the close method from AutoCloseable
    @Override
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }


    public static void main(String[] args) {
        try (OrderView view = new OrderView()) {
            view.displayMainMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
