/**
 * 
 * File: Order.java
 * Description: This is an entity class for orders and encapsulates the basic information of an order.
 * This class contains information about delivery status as well.
 * This class contains a calculateOrderTotal() method that returns the total amount of the order. 
 * @author Kim
 * @version 07.11.2023
*/ 

package modelPack;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order extends Entity {

   //Declaration of private data fields 

   private final int orderNr;
   private Date orderDate;
   private Date requiredDate;
   private Date shippedDate;
   private String status;
   private String comments;
   private List<OrderDetails> orderDetailsList;

   /**
    * Constructor for the Order class that sets all fields.
    * @param orderNr The unique serial number consisting of 5 digits.
    * @param orderDate The date when the order was placed.
    * @param requiredDate The date when the order is required to be delivered.
    * @param shippedDate The date when the order was actually shipped.
    * @param comments Any comments related to the order.
    */
   
   public Order(int orderNr, Date orderDate, Date requiredDate, Date shippedDate, String status, String comments) {
	   this.orderNr = orderNr;
	   this.orderDate = orderDate;
	   this.requiredDate = requiredDate;
	   this.shippedDate = shippedDate;
	   this.status = status;
	   this.comments = comments;
	   this.orderDetailsList = new ArrayList<>();
	}


   /**
   * Getter methods for following variables.
   * @return order number, order date, required date, shipped date, delivery status, and comments.
   */

   public int getOrderNumber() {
      return this.orderNr;
   }
   
   public int getOrderNr() {
       return this.orderNr;
   }

   public Date getOrderDate() {
      return this.orderDate;
   }

   public Date getRequiredDate() {
      return this.requiredDate;
   }

   public Date getShippedDate() {
      return this.shippedDate;
   }

   public String getStatus() {
      return this.status;
   }

   public String getComments() {
      return this.comments;
   }

   /**
   * Setter methods for following parameters.
   * setting @param orderDate, @param requiredDate, @param shippedDate, @param status, @param comments   
   */

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setRequiredDate(Date requiredDate) {
        this.requiredDate = requiredDate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
    * Add 'OrderDetails' object to the list of order details associated with this order
    */
    public void addOrderDetail(OrderDetails orderDetail){
        orderDetailsList.add(orderDetail);
    }

    /**
    * Calculation the thotal cost of the entire order by summing up the subtotals of each order details
    * @return order total     
    */

    public double calculateOrderTotal() {
    double total = 0.0;

    for (OrderDetails orderDetail : orderDetailsList) {
        double subtotal = orderDetail.calculateSubTotal();
        total += subtotal;
    }

    return total;
    }
}
