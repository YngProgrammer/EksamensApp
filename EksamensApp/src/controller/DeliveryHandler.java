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


@SuppressWarnings("unused")
public class DeliveryHandler {

    private OrderHandler orderHandler; 

 
    public DeliveryHandler() {
        this.orderHandler = new OrderHandler(); 
    }

  
    public boolean createDelivery(Order order, Date shippedDate) {
        if (order != null && !"Shipped".equals(order.getStatus())) {
            order.setShippedDate(shippedDate);
            order.setStatus("Shipped");
            
            boolean isOrderUpdated = orderHandler.editOrder(order);
            if (isOrderUpdated) {
                
                return true;
            } else {
               
                System.out.println("Failed to update the order's shipment details in the database.");
                return false;
            }
        }
       
        return false;
    }

   
    public String getStatus(Order order) {
        if (order != null) {
            return order.getStatus();
        }
        return "Order not found"; 
}
    }
