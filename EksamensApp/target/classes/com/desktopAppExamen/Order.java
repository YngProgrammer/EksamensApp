/**
 * 
 * File: Order.java
 * Description: This is an entity class for "order" and encapsulates the basic information of an order.
 * This class contains information about delivery status as well. 
 * @author Kim
 * @version 06.11.2023
*/ 

package package com.desktopAppExamen.Order;

import java.util.Date;

public class Order implements Entity {
   private int orderNr;
   private Date orderDate;
   private Date requiredDate;
   private Date shippedDate;
   private String status;
   private String comments;

   public Order(int orderNr) {
      this.orderNr = orderNr;
   }

   public int getOrderNumber() {
      return this.orderNumber;
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

}
