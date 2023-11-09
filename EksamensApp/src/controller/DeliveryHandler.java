/**
 * File: DeliveryHandler.java
 * Description:  
 * DeliveryHandler class manages the delivery status updates and queries for orders within a CMS, interfacing with OrderHandler for database interactions.
 * It provides methods to mark orders as shipped and to retrieve the current shipping status.
 * @author Albert
 * @version 09.11.2023
 */


package controller;

import modelPack.Order;
import java.util.Date;
import controller.OrderHandler; 

/**
 * DeliveryHandler class that simulates the process of handling deliveries.
 * It relies on third-party services for actual delivery tracking and updates.
 * It updates order details related to the delivery in the database.
 */
@SuppressWarnings("unused")
public class DeliveryHandler {

    private OrderHandler orderHandler; // Instance of OrderHandler for database operations

    /**
     * Constructor for DeliveryHandler that sets up an OrderHandler for database interactions.
     */
    public DeliveryHandler() {
        this.orderHandler = new OrderHandler(); // Initialize OrderHandler here
    }

    /**
     * Updates the shipment details of an existing order in the database.
     * It marks the order as shipped and records the shipped date.
     *
     * @param order       The order to update with shipment details.
     * @param shippedDate The date the order was shipped.
     * @return            True if the operation is successful, false otherwise.
     */
    public boolean createDelivery(Order order, Date shippedDate) {
        if (order != null && !"Shipped".equals(order.getStatus())) {
            order.setShippedDate(shippedDate);
            order.setStatus("Shipped");
            // Update the order using OrderHandler to persist changes to the database
            boolean isOrderUpdated = orderHandler.editOrder(order);
            if (isOrderUpdated) {
                // Order update was successful, perform any additional actions if necessary
                return true;
            } else {
                // Handle the case where the order update failed
                System.out.println("Failed to update the order's shipment details in the database.");
                return false;
            }
        }
        // If order is null or already shipped, indicate that no update was necessary
        return false;
    }

    /**
     * Retrieves the current status of an order's delivery.
     *
     * @param order The order for which the status is queried.
     * @return The current status of the order.
     */
    public String getStatus(Order order) {
        if (order != null) {
            return order.getStatus();
        }
        return "Order not found"; 
}
    }
